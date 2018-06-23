package com.example.slash.comflix.entities

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
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

    @GET("tv/{id}")
    fun getSeriesDetails(@Path("id")id:Int):retrofit2.Call<SerieDetails>

    @GET("tv/{id}/similar")
    fun getSimilarSeries(@Path("id") id:Int):Observable<SerieDTO>

    @GET("tv/{id}/credits")
    fun getCreditsSeries(@Path("id") id:Int):Observable<CastCrewDTO>

    @GET ("tv/{id}/season/{number}")
    fun getSeason(@Path("id")id:Int,@Path("number") number:Int):retrofit2.Call<Season>


    @GET("tv/{id}/reviews")
    fun getComments(@Path("id")id:Int):retrofit2.Call<CommentDTO>
}