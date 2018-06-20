package com.example.slash.comflix.entities

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Slash on 20/06/2018.
 */
interface SerieAPIClient {
    /* Get list of movies now playing */
    @GET("tv/on_the_air?api_key=b73297ba2fd4486c8d1e39f3e8d0c0e4")
    fun getSeriesNowPalying(@Query("page")page:Int): Observable<SerieDTO>
}