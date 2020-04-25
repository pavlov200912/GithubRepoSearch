package com.example.yandexgithub.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface GitDatabaseDao {
    @Insert
    fun insert(repo: GitRepo)

    @Update
    fun update(repo: GitRepo)

    @Query("SELECT * from repo_table WHERE id = :key")
    fun get(key: Long): GitRepo?

    @Query("DELETE FROM repo_table")
    fun clear()

    @Query("SELECT * FROM repo_table ORDER BY visited_date DESC")
    fun getVisited(): LiveData<List<GitRepo>>
}