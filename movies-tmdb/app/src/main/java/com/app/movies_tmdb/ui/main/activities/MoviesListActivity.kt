package com.app.movies_tmdb.ui.main.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.SpinnerAdapter
import com.app.movies_tmdb.R
import com.app.movies_tmdb.ui.main.MoviesListFragment
import com.app.movies_tmdb.utils.NOW_PLAYING
import com.app.movies_tmdb.utils.POPULAR
import kotlinx.android.synthetic.main.main_activity.*

class MoviesListActivity : AppCompatActivity() {

    private var optionsList: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            replaceFragment(POPULAR)
        }
        initViews()
        bindListener()
    }

    private fun initViews() {
        optionsList.add(POPULAR)
        optionsList.add(NOW_PLAYING)
        val arrayAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, optionsList)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_category.setPadding(4, 0, 4, 0)
        spinner_category.adapter = arrayAdapter
    }

    private fun bindListener() {
        spinner_category?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                replaceFragment(POPULAR)
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position == 0) {
                    replaceFragment(POPULAR)
                } else {
                    replaceFragment(NOW_PLAYING)
                }
            }

        }
    }

    private fun replaceFragment(moviesType: String) {
        val moviesListFragment = MoviesListFragment.newInstance()
        moviesListFragment.setMoviesType(moviesType)
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                moviesListFragment
            )
            .commitAllowingStateLoss()
    }

}
