package com.example.slash.comflix

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import com.example.slash.comflix.fragment.FavouriteCinemaFragment
import com.example.slash.comflix.fragment.FavouriteMoviesFragment
import com.example.slash.comflix.fragment.FavouriteSeriesFragment
import com.example.slash.comflix.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*



class MainActivity :AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener  {
    private var fragment: Fragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        fragment= HomeFragment()
        supportFragmentManager.beginTransaction()
                .replace(conteneur_main.id,fragment)
                .addToBackStack(null)
                .commit()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        var item:MenuItem=menu.findItem(R.id.menu_search)
        val searchView:SearchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

                return false
            }
        })




        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                fragment= HomeFragment()
            }
            R.id.nav_movies -> {
               fragment=FavouriteMoviesFragment()
            }
            R.id.nav_series -> {
                fragment=FavouriteSeriesFragment()
            }
            R.id.nav_salles -> {
                fragment=FavouriteCinemaFragment()
            }
            R.id.nav_profil -> {

            }

        }
        supportFragmentManager.beginTransaction()
                .replace(conteneur_main.id,fragment)
                .addToBackStack(null)
                .commit()
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
