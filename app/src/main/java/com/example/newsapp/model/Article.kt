package com.example.newsapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "articles"
)
data class Article(

    val artistName: String="",
    val collectionName: String="",
    val collectionPrice: Double=0.0,
    val trackName: String="",

    val trackPrice: Double=0.0

): Serializable
{
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}