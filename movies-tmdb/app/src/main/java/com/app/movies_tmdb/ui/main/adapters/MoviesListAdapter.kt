package com.app.movies_tmdb.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.movies_tmdb.utils.IMAGE_BASE_URL
import com.app.movies_tmdb.R
import com.app.movies_tmdb.datamodels.Movies
import com.app.movies_tmdb.ui.main.MoviesListFragment
import com.app.movies_tmdb.utils.MovieItemViewTypes
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_movies_list.view.*

class MoviesListAdapter(diffCallback: MoviesListFragment.PaginationItemCallback) :
    PagedListAdapter<Movies, MoviesListAdapter.MoviesViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return when (viewType) {
            MovieItemViewTypes.MOVIE_ITEM -> return MoviesViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_movies_list,
                    parent,
                    false
                )
            )
            else -> MoviesViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_movie_loading,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        if (position < 0 || position >= currentList?.size!!) {
            return
        }
        if (getItemViewType(position) == MovieItemViewTypes.MOVIE_ITEM) {
            holder.setData(getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            in 0 until (itemCount - 1) -> MovieItemViewTypes.MOVIE_ITEM
            else -> MovieItemViewTypes.MOVIE_ITEM_LOADER
        }
    }

    override fun getItemCount(): Int {
        return if (currentList != null && currentList!!.size > 1) {
            currentList!!.size + 1
        } else {
            0
        }
    }

    class MoviesViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        fun setData(movie: Movies?) {
            val imageUrl = IMAGE_BASE_URL.plus(movie!!.posterPath)
            val options = RequestOptions()
                .placeholder(R.drawable.placeholder_image)
            Glide.with(itemView.context).load(imageUrl)
                .apply(options)
                .into(itemView.iv_poster)

            itemView.tv_movie_name.text = movie.originalTitle

            val ratingText = "Rating : ".plus(movie.voteAverage.toString())
            itemView.tv_movie_rating.text = ratingText
        }
    }

}