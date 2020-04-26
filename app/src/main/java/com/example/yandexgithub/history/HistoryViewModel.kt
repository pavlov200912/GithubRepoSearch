package com.example.yandexgithub.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.yandexgithub.database.GitDatabaseDao
import com.example.yandexgithub.database.GitRepo
import kotlinx.coroutines.*


class HistoryViewModel(
    private val database: GitDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private val _reposData = MutableLiveData<List<GitRepo>>()
    val reposData: LiveData<List<GitRepo>>
        get() = _reposData

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        updateHistory()
    }

    fun updateHistory(showAll: Boolean = true) {
        uiScope.launch {
            _reposData.value = getVisited(showAll)
        }
    }

    private suspend fun getVisited(showAll: Boolean): List<GitRepo> {
        return withContext(Dispatchers.IO) {
            if (showAll) {
                database.getVisited()
            } else {
                database.getFavorite()
            }
        }
     }

    fun changeFavorite(gitRepo: GitRepo) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                gitRepo.isFavorite = !gitRepo.isFavorite
                database.update(gitRepo)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}