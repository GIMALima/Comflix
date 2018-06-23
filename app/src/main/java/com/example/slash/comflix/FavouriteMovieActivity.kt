package com.example.slash.comflix

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.slash.comflix.adapter.FavouriteMovieAdapter
import com.example.slash.comflix.entities.GridSpacingItemDecoration
import com.example.slash.comflix.entities.Room
import com.example.slash.comflix.entities.dpToPx
import com.example.slash.comflix.room.MovieEntity

class FavouriteMovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_movies)


        var movieList=listOf<MovieEntity>()
        var recyclerView=this.findViewById<RecyclerView>(R.id.recyclerView) as RecyclerView
        var movieAdapter= FavouriteMovieAdapter(this,movieList,R.layout.movie_card)
        var mLayoutManager= GridLayoutManager(this,calculateCardNum(this))
        recyclerView.addItemDecoration(GridSpacingItemDecoration(2, dpToPx(10),true))
        recyclerView.layoutManager=mLayoutManager
        recyclerView.itemAnimator= DefaultItemAnimator()
        recyclerView.adapter=movieAdapter
        Room.getListFavorisMovie(this,movieAdapter)

    }
}

