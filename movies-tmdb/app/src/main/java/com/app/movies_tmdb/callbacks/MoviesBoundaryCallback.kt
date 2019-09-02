package com.app.movies_tmdb.callbacks

import androidx.paging.PagedList
import com.app.movies_tmdb.datamodels.Movies

class MoviesBoundaryCallback : PagedList.BoundaryCallback<Movies>(){

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
    }

    override fun onItemAtEndLoaded(itemAtEnd: Movies) {
        super.onItemAtEndLoaded(itemAtEnd)
    }

    override fun onItemAtFrontLoaded(itemAtFront: Movies) {
        super.onItemAtFrontLoaded(itemAtFront)
    }

}