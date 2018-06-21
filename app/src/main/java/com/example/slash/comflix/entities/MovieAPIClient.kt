package com.example.slash.comflix.entities

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieAPIClient {
    /* Get list of movies now playing */
    @GET("movie/now_playing?api_key=b73297ba2fd4486c8d1e39f3e8d0c0e4")
    fun getMoviesNowPalying(@Query("page")page:Int): Observable<MovieDTO>

    @GET("movie/popular?api_key=b73297ba2fd4486c8d1e39f3e8d0c0e4")
    fun getPopularMovies(@Query("page")page:Int):Observable<MovieDTO>

    @GET("movie/{movie_id}?api_key=b73297ba2fd4486c8d1e39f3e8d0c0e4")
    fun getMovieDetails(@Path("movie_id")movie_id:Int):Observable<MovieDetails>

    @GET("movie/{movie_id}/similar?api_key=b73297ba2fd4486c8d1e39f3e8d0c0e4")
    fun getSimilarMovies(@Path("movie_id") movie_id:Int):Observable<MovieDTO>

    @GET("movie/{movie_id}/credits?api_key=b73297ba2fd4486c8d1e39f3e8d0c0e4")
    fun getCredits(@Path("movie_id") movie_id:Int):Observable<CastCrewDTO>

}