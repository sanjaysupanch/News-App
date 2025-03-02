package com.example.news.viewmodal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.data.modal.Article
import com.example.news.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {
    // StateFlow for UI state management
    private val _articles = MutableStateFlow<Result<List<Article>>?>(null)
    val articles: StateFlow<Result<List<Article>>?> = _articles

    // Flow for real-time updates of saved articles
    private val _savedArticles = repository.getSavedArticles()
    val savedArticles: Flow<List<Article>> = _savedArticles

    init {
        fetchNews()
    }

    fun fetchNews() {
        viewModelScope.launch {
            viewModelScope.launch {
                repository.fetchNews().collectLatest { result ->
                    _articles.value = result
                }
            }
        }
    }

    // Save article
    fun saveArticle(article: Article) = viewModelScope.launch {
        repository.saveArticle(article)
    }

    // Delete article
    fun deleteArticle(articleUrl: String) = viewModelScope.launch {
        repository.deleteArticle(articleUrl)
    }
}