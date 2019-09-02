package com.app.movies_tmdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.movies_tmdb.ui.main.MoviesListFragment

class MoviesListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MoviesListFragment.newInstance())
                .commitNow()
        }
    }

}
