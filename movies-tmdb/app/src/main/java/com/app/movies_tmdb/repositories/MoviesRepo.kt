package com.app.movies_tmdb.repositories

import androidx.lifecycle.LiveData
import com.app.movies_tmdb.api.RetrofitService
import com.app.movies_tmdb.datamodels.ApiResponse

class MoviesRepo{

    fun getPopularMovies(): LiveData<ApiResponse> {
        val apiService = RetrofitService.create()
        return apiService.getPopularMovies()
    }

    fun getNowPlayingMovies(): LiveData<ApiResponse> {
        val apiService = RetrofitService.create()
        return apiService.getNowPlayingMovies()
    }

}