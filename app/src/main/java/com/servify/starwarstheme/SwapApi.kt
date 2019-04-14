package com.servify.starwarstheme

import retrofit2.Call
import retrofit2.http.GET

interface SwapApi {
    @GET("/api/films/")
    fun getMovies(): Call<MovieResponse>

}