package com.app.movies_tmdb.ui.main.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.app.movies_tmdb.R
import com.app.movies_tmdb.databinding.ActivitySingleMovieBinding
import com.app.movies_tmdb.datamodels.Movies
import com.app.movies_tmdb.viewmodels.MovieViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var movieViewModel: MovieViewModel
    private var movieId: Int = 0
    private lateinit var binding: ActivitySingleMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_single_movie)
        extractIntent()
        initViewModel()
        fetchMovieDetails()
        setUpListener()
    }

    private fun extractIntent() {
        if (intent != null) {
            movieId = intent.extras!!.getInt(EXTRA_MOVIE_ID)
        }
    }

    private fun initViewModel() {
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
    }

    private fun setUpListener() {
        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    /**
     * fetching a single movie's details
     */
    private fun fetchMovieDetails() {
        binding.showProgress = true
        movieViewModel.getMovieDetails(movieId).enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                binding.showProgress = false
                if (response.isSuccessful) {
                    setMovieData(response.body())
                }
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
                binding.showProgress = false
                Log.d(MovieDetailsActivity::class.java.name, t.message.toString())
            }
        })
    }

    /**
     * data binding for setting the ui data
     */
    private fun setMovieData(body: Movies?) {
        if (body != null) {
            binding.movie = body
        }
    }

    // intent key for getting the movie-id
    companion object {
        val EXTRA_MOVIE_ID: String? by lazy { "EXTRA_MOVIE_ID" }
    }
}
