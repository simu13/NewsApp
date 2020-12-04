package com.example.newsapp.api


import com.example.newsapp.model.SongResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SearchAPI {

    @GET("search?entity=song")
    suspend fun searchForNews(
        @Query("term")
        Y: String
    ): Response<SongResponse>
}