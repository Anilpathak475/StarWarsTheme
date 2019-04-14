package com.servify.starwarstheme

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.past3.ketro.model.Wrapper

class MainViewModel: ViewModel(){

    val list = mutableListOf<Movie>()

    private val liveData = MutableLiveData<Wrapper<MovieResponse>>()

    fun getMovies(): LiveData<Wrapper<MovieResponse>> {
        return MovierRequest().doRequest()
    }
}