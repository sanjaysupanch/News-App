package com.example.news.data.modal

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticlesResponse(
    @SerialName("status") val status: String = "",
    @SerialName("articles") val articles: List<Article>
)

@Entity(tableName = "articles")
@Serializable
data class Article(
    @PrimaryKey(autoGenerate = false)
    @SerialName("url")
    val url: String = "",
    @SerialName("title")
    val title: String = "",
    @SerialName("description")
    val description: String = "",
    @SerialName("urlToImage")
    val urlToImage: String = "",
)
