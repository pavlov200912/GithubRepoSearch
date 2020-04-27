package com.example.yandexgithub.search

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yandexgithub.database.GitDatabaseDao
import com.example.yandexgithub.database.GitRepo
import com.example.yandexgithub.network.GitApi
import com.example.yandexgithub.network.GitProperty
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


enum class GitApiStatus { LOADING, INTERNET_ERROR, QUERY_ERROR, DONE }
/**
 * The [ViewModel] that is attached to the [SearchFragment].
 */
class SearchViewModel(
    private val database: GitDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    // Text of query, observes editText field
    val queryText = MutableLiveData<String>()

    private val _status = MutableLiveData<GitApiStatus>()
    val status: LiveData<GitApiStatus>
        get() = _status

    // The external immutable LiveData for the response String
    private val _properties = MutableLiveData<List<GitProperty>>()
    val properties: LiveData<List<GitProperty>>
        get() = _properties


    init {
        queryText.value = ""
    }


    fun getGitProperties() {
        uiScope.launch {
            if (queryText.value == null || queryText.value == "") {
                _status.value = GitApiStatus.QUERY_ERROR
                return@launch
            }

            try {
                _status.value = GitApiStatus.LOADING

                // Await the completion of Retrofit request
                val result = withContext(Dispatchers.IO) {
                    GitApi.retrofitService.getPropertiesAsync(queryText.value!!)
                }.await()

                _status.value = GitApiStatus.DONE
                _properties.value = result.items
            } catch (e: Exception) {
                _properties.value = ArrayList()
                _status.value = GitApiStatus.INTERNET_ERROR
            }
        }
    }

    fun saveClickedRepo(gitProperty: GitProperty) {
        uiScope.launch {
            saveGitRepo(gitProperty)
        }
    }

    private suspend fun saveGitRepo(gitProperty: GitProperty) {
        withContext(Dispatchers.IO) {
            val format = SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US
            )
            format.timeZone = TimeZone.getTimeZone("UTC")

            val creationDate: Date? = format.parse(gitProperty.created)
            val visitDate: Date = Calendar.getInstance().time

            val repo =  GitRepo(
                id = gitProperty.id,
                name = gitProperty.name,
                owner = gitProperty.owner?.login ?: "",
                url = gitProperty.htmlUrl,
                description = gitProperty.description ?: "",
                language = gitProperty.language ?: "",
                dateOfCreation = creationDate,
                dateOfVisit = visitDate
            )
            // insert or update database
            val storedRepo = database.get(gitProperty.id)
            if (storedRepo == null) {
                database.insert(repo)
            } else {
                repo.isFavorite = storedRepo.isFavorite
                database.update(repo)
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