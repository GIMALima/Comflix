package com.example.slash.comflix

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.NavUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.slash.comflix.fragment.*
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    var fragment: Fragment?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setSupportActionBar(findViewById(R.id.retour_toolbar))
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val type = intent.getStringExtra("type")
        val id= intent.getIntExtra("id",0)
        var bundle= Bundle()
        bundle.putInt("id",id)
       when (type){
           "movie"->{
                retour_toolbar.title=resources.getStringArray(R.array.movieTitles).get(id)
                fragment=MovieDetailsFragment()
            }
           "serie"->{
               retour_toolbar.title=resources.getStringArray(R.array.serieTitles).get(id)
               fragment=MovieDetailsFragment()
            }
           "person"->{
               retour_toolbar.title=resources.getStringArray(R.array.personName).get(id)
               fragment= PersonDetailsFragment()
           }
           "cinema"->{
               retour_toolbar.title=resources.getStringArray(R.array.movieTitles).get(id)
               fragment= CinemaFragment()
           }
           "cinema_favouris"->{
               retour_toolbar.title="My favourite cinemas"
               fragment= FavouriteCinemaFragment()
           }
           "cinema_details"->{
               retour_toolbar.title=resources.getStringArray(R.array.cinemaName).get(id)
               fragment= CinemaDetailsFragment()
           }
        }
        fragment!!.arguments=bundle
        supportFragmentManager.beginTransaction()
                .replace(details_container.id,fragment)
                .addToBackStack(null)
                .commit()

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.back_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
        // Respond to the action bar's Up/Home button
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
