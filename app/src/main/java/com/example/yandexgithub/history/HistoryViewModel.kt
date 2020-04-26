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
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val reposData = database.getVisited()


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}