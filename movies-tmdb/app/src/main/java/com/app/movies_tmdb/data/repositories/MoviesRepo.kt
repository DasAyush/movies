package com.app.movies_tmdb.data.repositories

import com.app.movies_tmdb.api.RetrofitService
import com.app.movies_tmdb.datamodels.Movies
import com.app.movies_tmdb.datamodels.MoviesApiResponse
import retrofit2.Call

class MoviesRepo {

    val apiService = RetrofitService.create()

    fun getPopularMovies(page: Int): Call<MoviesApiResponse> {
        return apiService.getPopularMovies(page)
    }

    fun getMovieDetails(movieId: Int): Call<Movies> {
        return apiService.getMovieDetails(movieId)
    }

//    fun getNowPlayingMovies(page: Int): LiveData<MoviesApiResponse> {
//        val apiService = RetrofitService.create()
//        return apiService.getNowPlayingMovies(page)
//    }

}