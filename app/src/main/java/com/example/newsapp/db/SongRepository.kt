package com.example.newsapp.db

import com.example.newsapp.api.RetrofitInstance
import com.example.newsapp.model.Song


class SongRepository(
    val db: ArticleDatabase
) {


    suspend fun searchNews(searchQuery:String) =
        RetrofitInstance.api.searchForSongs(searchQuery)

    suspend fun upsert(song: Song) = db.getArticleDao().upsert(song)

    fun getSavedNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(song: Song) = db.getArticleDao().deleteArticle(song)
}