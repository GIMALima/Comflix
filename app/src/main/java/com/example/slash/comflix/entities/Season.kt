package com.example.slash.comflix.entities

data class Season(
        val id:Int,
        val air_date:String?,
        val name:String,
        val overview:String?,
        val poster_path:String,
        val season_number:Int,
        val episodes:ArrayList<Episode>
)

data class Episode(
        val air_date:String,
        val episode_number:Int,
        val name:String,
        val overview:String,
        val id:Int,
        val vote_average:Double,
        val vote_count:Int,
        val still_path:String
)