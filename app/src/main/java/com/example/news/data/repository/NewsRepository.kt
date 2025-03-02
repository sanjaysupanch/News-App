package com.example.news.data.repository

import com.example.news.BuildConfig
import com.example.news.data.api.ArticlesAPIService
import com.example.news.data.database.NewsDao
import com.example.news.data.modal.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val api: ArticlesAPIService,
    private val dao: NewsDao
) {
    /**
     * Fetches news articles from the API and emits the result as a Flow.
     *
     * @return A [Flow] emitting [Result] with a list of [Article] on success or an error on failure.
     */
    suspend fun fetchNews(): Flow<Result<List<Article>>> = flow {
        try {
            val response = api.getArticles(BuildConfig.NEWS_API_KEY)
            emit(Result.success(response.articles))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    fun getSavedArticles(): Flow<List<Article>> = dao.getSavedArticles()

    suspend fun saveArticle(article: Article) = dao.saveArticle(article)

    suspend fun deleteArticle(articleUrl: String) = dao.deleteArticle(articleUrl)
}