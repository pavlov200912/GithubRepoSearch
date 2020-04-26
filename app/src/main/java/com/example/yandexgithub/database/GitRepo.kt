package com.example.yandexgithub.database

import android.text.Html
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.*

@Entity(tableName = "repo_table")
data class GitRepo(
    @PrimaryKey
    val id: Long,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "owner")
    val owner: String,
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "language")
    val language: String,
    @ColumnInfo(name = "created_date")
    val dateOfCreation: Date?,
    @ColumnInfo(name = "visited_date")
    val dateOfVisit: Date?,
    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean = false
) {
    fun getFormattedName(): String {
        return ""
    }
}

