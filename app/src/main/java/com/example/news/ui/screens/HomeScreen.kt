package com.example.news.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
fun HomeScreen(
    navController: NavController,
    viewModel: NewsViewModel = hiltViewModel()
) {
    val articlesState = viewModel.articles.collectAsStateWithLifecycle().value
    val gson = Gson()

    when {
        // isLoading
        articlesState == null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        articlesState.isSuccess -> {
            val articles = articlesState.getOrNull().orEmpty()
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 56.dp)
            ) {
                items(articles) { article ->
                    val json = gson.toJson(article)
                    ArticleCard(article, false, {
                        navController.currentBackStackEntry
                            ?.savedStateHandle
                            ?.set("article", json)
                        navController.navigate(Screens.Details.route)
                    }, {})
                }
            }
        }

        articlesState.isFailure -> {
            val errorMessage = articlesState.exceptionOrNull()?.message ?: "Unknown error"
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Error: $errorMessage", color = Color.Red, fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { viewModel.fetchNews() }) {
                        Text(text = "Retry")
                    }
                }
            }
        }
    }
}