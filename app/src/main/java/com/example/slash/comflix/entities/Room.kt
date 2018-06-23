package com.example.slash.comflix.entities

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import com.example.slash.comflix.adapter.FavouriteMovieAdapter
import com.example.slash.comflix.room.MovieDB
import com.example.slash.comflix.room.MovieEntity
import com.example.slash.comflix.room.MovieEntityDao


object Room {

    private var db: MovieDB? = null
    private var dao: MovieEntityDao? = null
    var movies: List<MovieEntity>? = null

  fun getListFavorisMovie(context: Context,favouriteMovieAdapter: FavouriteMovieAdapter) {

        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                db = MovieDB.getInstance(context)
                dao = db!!.movieDAO()
                movies = dao?.getAllMovies()

                return null
            }
            override fun onPostExecute(result: Void?) {
                favouriteMovieAdapter.updateListFavouriteMovie(movies!!)

            }
        }.execute()
    }
    fun addMovie(context: Context,movie:MovieEntity){
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                db = MovieDB.getInstance(context)
                dao = db!!.movieDAO()
                Log.e("movieFavouris",movie.toString())
                dao?.addMovie(movie)
                return null
            }
        }.execute()
    }
    fun deleteMovie(context: Context,movie:MovieEntity){
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                db = MovieDB.getInstance(context)
                dao = db!!.movieDAO()
                dao?.deleteMovie(movie)
                return null
            }
        }.execute()
    }
}