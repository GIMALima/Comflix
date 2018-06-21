package com.example.slash.comflix.entities

<<<<<<< HEAD
/**
 * Created by Slash on 13/04/2018.
 */
data class Serie(
        val first_air_date:String,
        val name:String,
        val poster_path:String,
        val popularity:Float,
        val id:Int,
        val backdrop:String?,
        val vote_average:Double,
        val overview:String
)
=======
data class Serie(val poster_path:String?, val popularity:Double, val id:Int, val backdrop_path:String?,
                 val vote_average:Double,val overview:String,val first_air_date:String,
                 val origin_country:ArrayList<String>,val genre_ids:ArrayList<Int>,
                 val original_language:String, val vote_count:Int,val name:String, val original_name:String)

>>>>>>> 777eed48788401481ceb4b6c995997d1734e29ba
