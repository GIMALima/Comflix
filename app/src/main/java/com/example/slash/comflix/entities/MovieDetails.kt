package com.example.slash.comflix.entities

data class MovieDetails(val adult:Boolean,val backdrop_path:String?,val genres:ArrayList<genre>,
                        val homepage:String, val original_language:String,val original_title:String,
                        val overview:String?, val popularity:Double, val poster_path:String?,
                        val release_date:String,val runtime:Int?,val status:String, val title:String,
                        val vote_average:Double, val vote_count:Int,val favoris:Boolean?)