package com.example.slash.comflix.room

import android.arch.persistence.room.*


@Dao
interface MovieEntityDao {

   /* @Query("SELECT * FROM movie")
    fun getAllMovies(): List<MovieEntity>

    @Query("SELECT * FROM movie WHERE id = :arg0")
    fun getMovie(id: Int): MovieEntity

    @Insert
    fun addMovie(movie : MovieEntity)

    @Delete
    fun deleteMovie(id:Int)*/
}