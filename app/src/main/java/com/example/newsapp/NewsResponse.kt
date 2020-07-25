package com.example.newsapp

import com.example.newsapp.db.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)