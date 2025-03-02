package com.example.news.data.api

import com.example.news.data.modal.ArticlesResponse
import com.example.news.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticlesAPIService {

    @GET(Constants.NEWS_ENDPOINT)
    suspend fun getArticles(@Query("apiKey") apiKey: String): ArticlesResponse
}