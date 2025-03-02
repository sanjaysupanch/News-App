package com.example.news.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.news.data.modal.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}