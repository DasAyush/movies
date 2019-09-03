package com.app.movies_tmdb.data.datasource

import androidx.paging.DataSource
import com.app.movies_tmdb.data.repositories.MoviesRepo
import com.app.movies_tmdb.datamodels.Movies

/**
 * Popular movies data source factory
 */
class PopularMoviesDataSourceFactory(private val moviesRepo: MoviesRepo) :
    DataSource.Factory<Int, Movies>() {

    override fun create(): DataSource<Int, Movies> {
        return PopularMoviesPageKeyedDataSource(moviesRepo)
    }

}