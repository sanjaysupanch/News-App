package com.example.news.ui.screens

import android.annotation.SuppressLint
import android.webkit.WebView
import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.news.R
import com.example.news.data.modal.Article
import com.example.news.viewmodal.NewsViewModel

@Composable
fun DetailsScreen(
    article: Article,
    viewModel: NewsViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.fillMaxWidth(),
                containerColor = Color.Transparent,
                tonalElevation = 8.dp
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = {
                        viewModel.saveArticle(article)
                        Toast.makeText(
                            context,
                            context.resources.getString(R.string.article_saved),
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text("Save Article")
                }
            }
        }
    ) { paddingValues ->
        WebViewComponent(
            url = article.url,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewComponent(url: String, modifier: Modifier) {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                loadUrl(url)
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}