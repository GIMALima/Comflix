package com.example.slash.comflix.room

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "movie")
data class MovieEntity( @PrimaryKey() var id: String,
                        @ColumnInfo(name = "poster_path") var poster_path: String?,
                        @ColumnInfo(name = "overview") var overview: String?,
                        @ColumnInfo(name =  "release_date") var release_date:String?,
                        @ColumnInfo(name = "runtime") var runtime: Int?,
                        @ColumnInfo(name = "title") var title:String?,
                        @ColumnInfo(name="vote_average") var average:Double?,
                        @ColumnInfo(name = "vote_count") var vote_count:Int?,
                        @ColumnInfo(name = "genre") var genre:String?,
                        @ColumnInfo(name = "backdrop_path") var backdrop_path:String?,
                        @ColumnInfo(name = "favoris") var favoris:Boolean?
)
{
    constructor():this("","","","",0,"",0.0,0,"","",false){

    }
}

