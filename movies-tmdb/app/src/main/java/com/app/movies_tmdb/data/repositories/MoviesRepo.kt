package com.app.movies_tmdb.data.repositories

import androidx.lifecycle.LiveData
import com.app.movies_tmdb.api.RetrofitService
import com.app.movies_tmdb.datamodels.MoviesApiResponse
import retrofit2.Call

class MoviesRepo {

    fun getPopularMovies(page: Int): Call<MoviesApiResponse> {
        val apiService = RetrofitService.create()
        return apiService.getPopularMovies(page)
    }

//    fun getNowPlayingMovies(page: Int): LiveData<MoviesApiResponse> {
//        val apiService = RetrofitService.create()
//        return apiService.getNowPlayingMovies(page)
//    }

}