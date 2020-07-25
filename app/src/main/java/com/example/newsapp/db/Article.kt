package com.example.newsapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsapp.Source

@Entity(
    tableName = "articles"
)
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null,
    val author: Any,
    val content: Any,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)