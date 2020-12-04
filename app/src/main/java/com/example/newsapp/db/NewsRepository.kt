package com.example.newsapp.db

import com.example.newsapp.api.RetrofitInstance
import com.example.newsapp.model.Article


class NewsRepository(
    val db: ArticleDatabase
) {


    suspend fun searchNews(searchQuery:String) =
        RetrofitInstance.api.searchForNews(searchQuery)

    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)

    fun getSavedNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)
}