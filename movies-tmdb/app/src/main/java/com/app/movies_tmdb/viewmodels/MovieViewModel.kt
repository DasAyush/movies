package com.app.movies_tmdb.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.app.movies_tmdb.datamodels.ApiResponse
import com.app.movies_tmdb.repositories.MoviesRepo

class MovieViewModel : ViewModel() {

    private val moviesRepo: MoviesRepo = MoviesRepo()

    fun getPopularMovies(): LiveData<ApiResponse> {
        return moviesRepo.getPopularMovies()
    }

    fun getNowPlayingMovies(): LiveData<ApiResponse> {
        return moviesRepo.getNowPlayingMovies()
    }

}
