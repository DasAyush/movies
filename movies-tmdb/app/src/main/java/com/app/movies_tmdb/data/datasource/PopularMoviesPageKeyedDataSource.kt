package com.app.movies_tmdb.data.datasource

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.app.movies_tmdb.data.repositories.MoviesRepo
import com.app.movies_tmdb.datamodels.MoviesApiResponse
import com.app.movies_tmdb.datamodels.Movies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * class to fetch data from network for popular movies
 */
class PopularMoviesPageKeyedDataSource(private val moviesRepo: MoviesRepo) :
    PageKeyedDataSource<Int, Movies>() {

    // fetches initial list
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movies>
    ) {
        moviesRepo.getPopularMovies(1).enqueue(object : Callback<MoviesApiResponse> {
            override fun onFailure(call: Call<MoviesApiResponse>, t: Throwable) {
                Log.d("data-source", t.message.toString())
            }

            override fun onResponse(
                call: Call<MoviesApiResponse>,
                response: Response<MoviesApiResponse>
            ) {
                val popularMovies = response.body()!!.results
                val previousPageKey = response.body()!!.page
                callback.onResult(popularMovies, previousPageKey, previousPageKey + 1)
            }
        })
    }

    // fetches next list
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movies>) {
        moviesRepo.getPopularMovies(params.key).enqueue(object : Callback<MoviesApiResponse> {
            override fun onFailure(call: Call<MoviesApiResponse>, t: Throwable) {
                Log.d("data-source", t.message.toString())
            }

            override fun onResponse(
                call: Call<MoviesApiResponse>,
                response: Response<MoviesApiResponse>
            ) {
                if (response.isSuccessful) {
                    callback.onResult(response.body()!!.results, params.key.inc())
                }
            }
        })
    }

    // fetches previous list
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movies>) {
        moviesRepo.getPopularMovies(params.key).enqueue(object : Callback<MoviesApiResponse> {
            override fun onFailure(call: Call<MoviesApiResponse>, t: Throwable) {
                Log.d("data-source", t.message.toString())
            }

            override fun onResponse(
                call: Call<MoviesApiResponse>,
                response: Response<MoviesApiResponse>
            ) {
                if (response.isSuccessful) {
                    callback.onResult(response.body()!!.results, params.key.dec())
                }
            }
        })
    }

}