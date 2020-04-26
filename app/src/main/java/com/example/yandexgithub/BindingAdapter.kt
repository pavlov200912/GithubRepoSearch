package com.example.yandexgithub

import android.content.res.ColorStateList
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import androidx.databinding.InverseBindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.yandexgithub.database.GitRepo
import com.example.yandexgithub.history.HistoryRecyclerAdapter
import com.example.yandexgithub.network.GitProperty
import com.example.yandexgithub.search.GitApiStatus
import com.example.yandexgithub.search.SearchRecyclerAdapter
import java.text.SimpleDateFormat

@BindingAdapter("searchData")
fun bindSearchRecyclerView(
    recyclerView: RecyclerView,
    data: List<GitProperty>?
) {
    val adapter = recyclerView.adapter as SearchRecyclerAdapter
    adapter.submitList(data)
}

@BindingAdapter("historyData")
fun bindHistoryRecyclerView(
    recyclerView: RecyclerView,
    data: List<GitRepo>?
) {
    val adapter = recyclerView.adapter as HistoryRecyclerAdapter
    adapter.submitList(data)
}

@BindingAdapter("gitApiStatus")
fun bindStatus(statusButton: Button, status: GitApiStatus?) {
    val context = statusButton.context
    when (status) {
        GitApiStatus.LOADING -> {
            statusButton.text = context.getString(R.string.loadButton)
            statusButton.isClickable = false
        }
        GitApiStatus.INTERNET_ERROR -> {
            statusButton.text = context.getString(R.string.errorButton)
            statusButton.isClickable = true
        }
        GitApiStatus.QUERY_ERROR -> {
            statusButton.text = context.getString(R.string.errorButton)
            statusButton.isClickable = true
        }
        GitApiStatus.DONE -> {
            statusButton.text = context.getString(R.string.searchButton)
            statusButton.isClickable = true
        }
    }
}

@BindingAdapter("languageColor")
fun ImageView.setLanguageColor(item: GitRepo) {
    Log.i("COLOR", "i was here")

    imageTintList = ColorStateList.valueOf(languageToColor(item.language))
    //setColorFilter(languageToColor("Scala"))
    //setColorFilter(languageToColor(item.language))
}

@BindingAdapter("dateFormat")
fun TextView.setDateFormat(item: GitRepo) {
    val formatter = SimpleDateFormat("yyyy-MM-dd")
    text = if (item.dateOfCreation == null) {
        ""
    } else {
        "Created: ${formatter.format(item.dateOfCreation)}"
    }
}