package com.example.yandexgithub.network

import com.squareup.moshi.Json

data class GitProperty(val id: Long,
                       val name: String,
                       @Json(name = "html_url") val htmlUrl: String,
                       val description: String?,
                       val owner: Owner?,
                       @Json(name = "created_at") val created: String,
                       val language: String?) {
    fun getReadableHtml(): String {
        return htmlUrl.split("/").takeLast(2).joinToString("/")
    }
}

data class Owner(val login: String)

data class Result (val total_count: Int,
                   val incomplete_results: Boolean,
                   val items: List<GitProperty>)

