package com.zinoview.tzmovieapp.data.cloud

import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("all.json")
    suspend fun movies(@Query("api-key") apiKey: String) : CloudMovies.Base
}