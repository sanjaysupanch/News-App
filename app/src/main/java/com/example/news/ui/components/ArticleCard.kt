package com.example.news.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.news.R
import com.example.news.data.modal.Article
import com.example.news.utils.Utils.shareArticle

@Composable
fun ArticleCard(
    article: Article,
    showActions: Boolean = false,
    onClick: () -> Unit,
    onDelete: (() -> Unit)
) {
    val context = LocalContext.current

    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = article.urlToImage
                    ?: context.resources.getString(R.string.default_url),
                contentDescription = "Image",
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = article.title ?: context.resources.getString(R.string.no_title),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3
                )
                Text(
                    text = article.description
                        ?: context.resources.getString(R.string.no_description),
                    fontSize = 14.sp,
                    color = Color.Gray,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "Read More...",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.clickable { onClick() }
                    )
                }
            }

            if (showActions) {
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Delete Icon",
                        modifier = Modifier.clickable { onDelete() }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Icon(
                        imageVector = Icons.Filled.Share,
                        contentDescription = "Share Icon",
                        modifier = Modifier.clickable {
                            shareArticle(context, article)
                        }
                    )
                }
            }
        }
    }
}