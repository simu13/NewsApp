package com.example.newsapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.newsapp.model.Song


@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(song: Song): Long

    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Song>>

    @Delete
    suspend fun deleteArticle(song: Song)
}