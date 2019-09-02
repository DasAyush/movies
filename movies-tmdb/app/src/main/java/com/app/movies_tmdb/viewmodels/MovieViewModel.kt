package com.app.movies_tmdb.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.app.movies_tmdb.callbacks.MoviesBoundaryCallback
import com.app.movies_tmdb.data.datasource.MoviesDataSourceFactory
import com.app.movies_tmdb.data.repositories.MoviesRepo
import com.app.movies_tmdb.datamodels.Movies

class MovieViewModel : ViewModel() {

    private val moviesRepo: MoviesRepo = MoviesRepo()
    private lateinit var pagedListLiveData: LiveData<PagedList<Movies>>

    fun getPopularMovies(): LiveData<PagedList<Movies>> {
        pagedListLiveData = LivePagedListBuilder(
            MoviesDataSourceFactory(
                moviesRepo
            ), getPagingConfig())
                .build()
        return pagedListLiveData
    }

    private fun getPagingConfig(): PagedList.Config {
        return (PagedList.Config.Builder()).setEnablePlaceholders(true).setInitialLoadSizeHint(20)
            .setPageSize(20).build()
    }

//    fun getNowPlayingMovies(): LiveData<MoviesApiResponse> {
//        return moviesRepo.getNowPlayingMovies()
//    }

}
