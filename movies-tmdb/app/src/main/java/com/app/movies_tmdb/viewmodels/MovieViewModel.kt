package com.app.movies_tmdb.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.app.movies_tmdb.data.datasource.PopularMoviesDataSourceFactory
import com.app.movies_tmdb.data.datasource.NowPlayingMoviesDataSourceFactory
import com.app.movies_tmdb.data.repositories.MoviesRepo
import com.app.movies_tmdb.datamodels.Movies
import retrofit2.Call

/**
 * view model to deal with all the operations related to movies
 */
class MovieViewModel : ViewModel() {

    private val moviesRepo: MoviesRepo = MoviesRepo()
    private lateinit var pagedListLiveData: LiveData<PagedList<Movies>>

    /**
     * paging config defined to fetch the data from the server accordingly
     */
    private fun getPagingConfig(): PagedList.Config {
        return (PagedList.Config.Builder()).setEnablePlaceholders(true).setInitialLoadSizeHint(20)
            .setPageSize(20).build()
    }

    fun getPopularMovies(): LiveData<PagedList<Movies>> {
        pagedListLiveData = LivePagedListBuilder(
            PopularMoviesDataSourceFactory(
                moviesRepo
            ), getPagingConfig()
        )
            .build()
        return pagedListLiveData
    }

    fun getNowPlayingMovies(): LiveData<PagedList<Movies>> {
        pagedListLiveData = LivePagedListBuilder(
            NowPlayingMoviesDataSourceFactory(moviesRepo), getPagingConfig()
        )
            .build()
        return pagedListLiveData
    }

    fun getMovieDetails(movieId: Int): Call<Movies> {
        return moviesRepo.getMovieDetails(movieId)
    }

}
