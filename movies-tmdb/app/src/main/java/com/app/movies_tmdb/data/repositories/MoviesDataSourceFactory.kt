package com.app.movies_tmdb.data.repositories

import androidx.paging.DataSource
import com.app.movies_tmdb.datamodels.Movies

class MoviesDataSourceFactory(private val moviesRepo: MoviesRepo) :
    DataSource.Factory<Int, Movies>() {

    override fun create(): DataSource<Int, Movies> {
        return MoviesPageKeyedDataSource(moviesRepo)
    }

}