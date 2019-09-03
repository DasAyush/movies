package com.app.movies_tmdb.ui.main.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import com.app.movies_tmdb.R
import com.app.movies_tmdb.ui.main.MoviesListFragment
import com.app.movies_tmdb.utils.MOVIES_NOW_PLAYING
import com.app.movies_tmdb.utils.MOVIES_POPULAR
import kotlinx.android.synthetic.main.main_activity.*

class MoviesListActivity : AppCompatActivity() {

    private var optionsList: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            replaceFragment(MOVIES_POPULAR)
        }
        initViews()
        bindListener()
    }

    /**
     * spinner has been used to allow the user to switch between popular and now-playing categories
     */
    private fun initViews() {
        optionsList.add(MOVIES_POPULAR)
        optionsList.add(MOVIES_NOW_PLAYING)
        val arrayAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, optionsList)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_category.setPadding(4, 0, 4, 0)
        spinner_category.adapter = arrayAdapter
    }

    private fun bindListener() {
        spinner_category?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            /**
             * popular category of movies inflated by default
             */
            override fun onNothingSelected(parent: AdapterView<*>?) {
                replaceFragment(MOVIES_POPULAR)
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                (parent!!.getChildAt(0) as TextView).setTextColor(Color.WHITE)

                // fragment inflation based on the movie categories
                if (position == 0) {
                    replaceFragment(MOVIES_POPULAR)
                } else {
                    replaceFragment(MOVIES_NOW_PLAYING)
                }
            }

        }
    }

    // fragment inflation
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
