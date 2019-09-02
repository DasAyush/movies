package com.app.movies_tmdb.api

import androidx.lifecycle.LiveData
import com.app.movies_tmdb.datamodels.ApiResponse
import retrofit2.http.GET

interface ApiService {

    @GET("movie/popular")
    fun getPopularMovies(): LiveData<ApiResponse>

    @GET("movie/now_playing")
    fun getNowPlayingMovies(): LiveData<ApiResponse>

}