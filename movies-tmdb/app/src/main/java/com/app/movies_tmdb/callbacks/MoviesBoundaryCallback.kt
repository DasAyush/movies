package com.app.movies_tmdb.callbacks

import androidx.paging.PagedList
import com.app.movies_tmdb.datamodels.Movie

class MoviesBoundaryCallback : PagedList.BoundaryCallback<Movie>(){

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
    }

    override fun onItemAtEndLoaded(itemAtEnd: Movie) {
        super.onItemAtEndLoaded(itemAtEnd)
    }

    override fun onItemAtFrontLoaded(itemAtFront: Movie) {
        super.onItemAtFrontLoaded(itemAtFront)
    }

}