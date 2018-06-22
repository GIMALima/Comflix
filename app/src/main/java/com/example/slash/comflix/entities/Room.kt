package com.example.slash.comflix.entities

import android.content.Context
import android.os.AsyncTask
import com.example.slash.comflix.room.MovieDB
import com.example.slash.comflix.room.MovieEntity
import com.example.slash.comflix.room.MovieEntityDao


object Room {

    private var db: MovieDB? = null
    private var dao: MovieEntityDao? = null
    private var movies: List<MovieEntity>? = null

 /*   fun getListFavorisMovie(context: Context) {

        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                db = MovieDB.getInstance(context)
                dao = db!!.movieDAO()
                movies = dao?.getAllMovies()
                return null
            }
        }.execute()
    }
    fun addMovie(context: Context,movie:MovieEntity){
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                db = MovieDB.getInstance(context)
                dao = db!!.movieDAO()
                dao?.addMovie(movie)
                return null
            }
        }.execute()
    }
    fun deleteMovie(context: Context,movie_id:Int){
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                db = MovieDB.getInstance(context)
                dao = db!!.movieDAO()
                dao?.deleteMovie(movie_id)
                return null
            }
        }.execute()
    }*/
}