package com.servify.starwarstheme

import com.past3.ketro.api.ApiErrorHandler
import com.past3.ketro.api.GenericRequestHandler
import retrofit2.Call

class MovierRequest() : GenericRequestHandler<MovieResponse>(){
    override fun makeRequest(): Call<MovieResponse> {
        return swapApi.getMovies()
    }

    private val swapApi: SwapApi by lazy {
        NetworkModule.createRetrofit().create(SwapApi::class.java)
    }

    override val errorHandler: ApiErrorHandler = MovieErrorHandler()

}