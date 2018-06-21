package com.example.slash.comflix.entities

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Slash on 20/06/2018.
 */
interface SerieAPIClient {
    /* Get list of movies now playing */
    @GET("tv/on_the_air")
    fun getSeriesNowPalying(@Query("page")page:Int): Observable<SerieDTO>

    @GET("tv/popular")
    fun getPopluareSeries(@Query("page")page:Int): retrofit2.Call<PopularSerieDTO>

}