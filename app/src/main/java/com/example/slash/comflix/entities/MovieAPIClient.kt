package com.example.slash.comflix.entities

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieAPIClient {
    /* Get list of movies now playing */
    @GET("movie/now_playing?api_key=b73297ba2fd4486c8d1e39f3e8d0c0e4")
    fun getMoviesNowPalying(@Query("page")page:Int): Observable<MovieDTO>

    @GET("movie/latest?api_key=b73297ba2fd4486c8d1e39f3e8d0c0e4")
    fun getAllLastestMovies(@Query("page")page:Int):Observable<MovieDTO>
}