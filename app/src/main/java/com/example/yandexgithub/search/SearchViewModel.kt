package com.example.yandexgithub.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yandexgithub.network.GitApi
import com.example.yandexgithub.network.GitProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class GitApiStatus { LOADING, INTERNET_ERROR, QUERY_ERROR, DONE }
/**
 * The [ViewModel] that is attached to the [SearchFragment].
 */
class SearchViewModel: ViewModel() {
    val queryText = MutableLiveData<String>()


    private val _status = MutableLiveData<GitApiStatus>()

    val status: LiveData<GitApiStatus>
        get() = _status

    // The internal MutableLiveData String that stores the most recent response
    private val _response = MutableLiveData<String>()

    // The external immutable LiveData for the response String
    private val _properties = MutableLiveData<List<GitProperty>>()
    val properties: LiveData<List<GitProperty>>
        get() = _properties

    val response: LiveData<String>
        get() = _response

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )


    init {
        queryText.value = ""
    }

    /**
     * Sets the value of the response LiveData to the Mars API status or the successful number of
     * Mars properties retrieved.
     */
    fun getGitProperties() {
        Log.i("onClick", "CLICKED")
        Log.i("onClick", "Text: ${queryText.value!!}")
        coroutineScope.launch {
            if (queryText.value == null) {
                _status.value = GitApiStatus.QUERY_ERROR
                return@launch
            }

            val getPropertiesDeferred = GitApi.retrofitService.getPropertiesAsync(queryText.value!!)
            try {
                _status.value = GitApiStatus.LOADING
                // Await the completion of our Retrofit request
                val result = getPropertiesDeferred.await()
                Log.i("API_RESPONSE", result.toString())

                _status.value = GitApiStatus.DONE
                _properties.value = result.items
            } catch (e: Exception) {
                Log.i("onClick", "RESPONSE_ERROR + ${e.message}")

                _properties.value = ArrayList()
                _status.value = GitApiStatus.INTERNET_ERROR
            }
        }
    }

    /**
     * When the [ViewModel] is finished, we cancel our coroutine [viewModelJob], which tells the
     * Retrofit service to stop.
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}