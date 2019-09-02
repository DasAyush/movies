package com.app.movies_tmdb.data.repositories

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.app.movies_tmdb.datamodels.ApiResponse
import com.app.movies_tmdb.datamodels.Movies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesPageKeyedDataSource(private val moviesRepo: MoviesRepo) :
    PageKeyedDataSource<Int, Movies>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movies>
    ) {
        val response = moviesRepo.getPopularMovies(1)
        response.enqueue(object : Callback<ApiResponse<Any?>> {
            override fun onFailure(call: Call<ApiResponse<Any?>>, t: Throwable) {
                Log.d("datasource", t.message)
            }

            override fun onResponse(
                call: Call<ApiResponse<Any?>>,
                response: Response<ApiResponse<Any?>>
            ) {
                val popularMovies = response.body()!!.results
                val previousPageKey = response.body()!!.page
                callback.onResult(popularMovies, previousPageKey, previousPageKey + 1)
            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movies>) {
        moviesRepo.getPopularMovies(params.key).enqueue(object : Callback<ApiResponse<Any?>> {
            override fun onFailure(call: Call<ApiResponse<Any?>>, t: Throwable) {
                Log.d("datasource", t.message)
            }

            override fun onResponse(
                call: Call<ApiResponse<Any?>>,
                response: Response<ApiResponse<Any?>>
            ) {
                if (response.isSuccessful) {
                    callback.onResult(response.body()!!.results, params.key.inc())
                }
            }
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movies>) {
        moviesRepo.getPopularMovies(params.key).enqueue(object : Callback<ApiResponse<Any?>> {
            override fun onFailure(call: Call<ApiResponse<Any?>>, t: Throwable) {
                Log.d("datasource", t.message)
            }

            override fun onResponse(
                call: Call<ApiResponse<Any?>>,
                response: Response<ApiResponse<Any?>>
            ) {
                if (response.isSuccessful) {
                    callback.onResult(response.body()!!.results, params.key.dec())
                }
            }
        })
    }

}