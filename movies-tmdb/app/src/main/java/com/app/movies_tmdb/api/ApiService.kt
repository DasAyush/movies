package com.app.movies_tmdb.api

import com.app.movies_tmdb.datamodels.Movies
import com.app.movies_tmdb.datamodels.MoviesApiResponse
import com.app.movies_tmdb.utils.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    fun getPopularMovies(@Query("page") page: Int): Call<MoviesApiResponse>

    @GET("movie/now_playing")
    fun getNowPlayingMovies(@Query("page") page: Int): Call<MoviesApiResponse>

    @GET("movie/{movie_id}?api_key=$API_KEY")
    fun getMovieDetails(@Path("movie_id") movieId: Int): Call<Movies>

}