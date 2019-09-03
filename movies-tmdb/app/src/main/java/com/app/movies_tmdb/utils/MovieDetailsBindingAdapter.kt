package com.app.movies_tmdb.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.app.movies_tmdb.R
import com.app.movies_tmdb.datamodels.MovieGenre
import com.app.movies_tmdb.datamodels.Movies
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * binding adapter for movie details screen
 */
class MovieDetailsBindingAdapter {

    companion object {

        @BindingAdapter("movieImage")
        @JvmStatic
        fun setImageUrl(imageView: ImageView, imagePath: String?) {
            val finalImageUrl = IMAGE_BASE_URL.plus(imagePath)
            val options = RequestOptions()
                .placeholder(R.drawable.placeholder_image)
            Glide.with(imageView.context).load(finalImageUrl)
                .apply(options)
                .into(imageView)
        }

        @BindingAdapter("runningTimeText")
        @JvmStatic
        fun setRunningTime(textView: TextView, runningTime: Int) {
            textView.text = runningTime.toString().plus(" min")
        }

        @BindingAdapter("genres")
        @JvmStatic
        fun setMovieGenres(textView: TextView, genres: List<MovieGenre>?) {
            var genreString = "Genres : "
            if (genres != null && genres.isNotEmpty()) {
                for (i in genres.indices) {
                    genreString = if (i < genres.size - 1) {
                        genreString.plus(genres[i].name.plus(", "))
                    } else {
                        genreString.plus(genres[i].name)
                    }
                }
                textView.text = genreString
            }
        }

        @BindingAdapter("voteCount")
        @JvmStatic
        fun setVotes(textView: TextView, voteCount: String) {
            val voteCountString = "Votes : ".plus(voteCount)
            textView.text = voteCountString
        }

    }

}
