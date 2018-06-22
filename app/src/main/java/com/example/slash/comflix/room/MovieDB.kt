package com.example.slash.comflix.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context



@Database(entities = arrayOf(MovieEntity::class), version = 1)
abstract class MovieDB():RoomDatabase() {

 /*   abstract fun movieDAO(): MovieEntityDao

    companion object {
        private var instance: MovieDB? = null

        fun getInstance(context: Context): MovieDB? {
            if (instance == null) {

                instance = Room.databaseBuilder(context.getApplicationContext(),
                        MovieDB::class.java, "movie.db")
                        .build()
            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }
    }
*/
}
