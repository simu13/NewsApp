package com.example.newsapp.model

data class SongResponse(
    val resultCount: Int,
    val results: List<Song>
)