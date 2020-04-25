package com.example.yandexgithub

import android.widget.Button
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.yandexgithub.network.GitProperty
import com.example.yandexgithub.search.GitApiStatus
import com.example.yandexgithub.search.SearchRecyclerAdapter

@BindingAdapter("listData")
fun bindRecyclerView(
    recyclerView: RecyclerView,
    data: List<GitProperty>?
) {
    val adapter = recyclerView.adapter as SearchRecyclerAdapter
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
