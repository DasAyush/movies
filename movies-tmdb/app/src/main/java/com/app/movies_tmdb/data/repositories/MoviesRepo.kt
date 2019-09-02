package com.app.movies_tmdb.data.repositories

import androidx.lifecycle.LiveData
import com.app.movies_tmdb.api.RetrofitService
import com.app.movies_tmdb.datamodels.ApiResponse
import retrofit2.Call

class MoviesRepo {

    fun getPopularMovies(page: Int): Call<ApiResponse<Any?>> {
        val apiService = RetrofitService.create()
        return apiService.getPopularMovies(page)
    }

//    fun getNowPlayingMovies(page: Int): LiveData<ApiResponse> {
//        val apiService = RetrofitService.create()
//        return apiService.getNowPlayingMovies(page)
//    }

}