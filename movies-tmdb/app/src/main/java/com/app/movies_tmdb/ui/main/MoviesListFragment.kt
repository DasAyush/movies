package com.app.movies_tmdb.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.movies_tmdb.R
import com.app.movies_tmdb.datamodels.Movies
import com.app.movies_tmdb.ui.main.adapters.MoviesListAdapter
import com.app.movies_tmdb.viewmodels.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movies.*

class MoviesListFragment : Fragment() {

    companion object {
        fun newInstance() = MoviesListFragment()
    }

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var moviesListAdapter: MoviesListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
        initViewModel()
        fetchMoviesList()
    }

    private fun initViews() {
        rec_view_movies.layoutManager = LinearLayoutManager(context)
        moviesListAdapter = MoviesListAdapter(PaginationItemCallback)
        rec_view_movies.adapter = moviesListAdapter
    }

    private fun initViewModel() {
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
    }


    private fun fetchMoviesList() {
        movieViewModel.getPopularMovies().observe(this, Observer {
            moviesListAdapter.submitList(it)
        })
    }

    object PaginationItemCallback : DiffUtil.ItemCallback<Movies?>() {
        override fun areItemsTheSame(oldItem: Movies, newItem: Movies): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movies, newItem: Movies): Boolean {
            return oldItem.originalTitle == newItem.originalTitle && oldItem.posterPath == newItem.posterPath
        }
    }

}
