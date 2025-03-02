package com.example.news.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.news.ui.components.ArticleCard
import com.example.news.ui.navigation.Screens
import com.example.news.viewmodal.NewsViewModel
import com.google.gson.Gson

@Composable
fun SavedArticlesScreen(
    navController: NavController,
    viewModel: NewsViewModel = hiltViewModel()
) {
    val savedArticles by viewModel.savedArticles.collectAsStateWithLifecycle(initialValue = emptyList())
    val gson = Gson()

    if (savedArticles.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No saved articles",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 56.dp)
        ) {
            items(savedArticles) { article ->
                val json = gson.toJson(article)
                ArticleCard(article, true, {
                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set("article", json)
                    navController.navigate(Screens.Details.route)
                }, {
                    viewModel.deleteArticle(article.url)
                })
            }
        }
    }
}