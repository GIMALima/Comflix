package com.example.slash.comflix.entities

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SeriesAPIClient
{
    @GET("tv/popular")
    fun getPopluareSeries(@Query("page")page:Int): retrofit2.Call<PopularSerieDTO>

}