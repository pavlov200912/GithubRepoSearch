package com.example.yandexgithub.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [GitRepo::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class GitDatabase : RoomDatabase() {
    abstract val gitDatabaseDao: GitDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: GitDatabase? = null

        fun getInstance(context: Context): GitDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        GitDatabase::class.java,
                        "git_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }

    }
}