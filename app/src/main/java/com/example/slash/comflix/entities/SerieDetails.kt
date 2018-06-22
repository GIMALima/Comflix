package com.example.slash.comflix.entities

data class SerieDetails(
        val name:String,
        val genres: ArrayList<genre>,
        val number_of_seasons:Int,
        val vote_average:Double,
        val vote_count:Int,
        val overview:String,
        val in_production:Boolean,
        val poster_path:String,
        val episode_run_time:ArrayList<String>,
        val first_air_date:String,
        val last_air_date:String
)
{
    fun getGendersFormatted():String
    {
        var result = ""
        for (i in genres)
        {
            result += if(i != genres.last())
                "${i.name}, "
            else
                "${i.name}"
        }

        return result
    }
}