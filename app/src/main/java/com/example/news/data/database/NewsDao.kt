package com.example.news.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.news.data.modal.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArticle(article: Article)

    @Query("SELECT * FROM articles")
    fun getSavedArticles(): Flow<List<Article>>

    @Query("DELETE FROM articles WHERE url = :articleUrl")
    suspend fun deleteArticle(articleUrl: String)
}