package com.example.news.utils

import android.content.Context
import android.content.Intent
import com.example.news.data.modal.Article

object Utils {
    fun shareArticle(context: Context, article: Article) {
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, article.title)
            putExtra(Intent.EXTRA_TEXT, "${article.title}\n\n${article.description}\n\nRead more: ${article.url}")
        }
        context.startActivity(Intent.createChooser(shareIntent, "Share via"))
    }
}