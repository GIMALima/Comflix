package com.example.slash.comflix.entities

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieAPIClient {

    @GET("movie/now_playing")
    fun getMoviesNowPalying(@Query("page")page:Int): Observable<MovieDTO>

    @GET("movie/popular")
    fun getPopularMovies(@Query("page")page:Int):Observable<MovieDTO>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id")movie_id:Int):Observable<MovieDetails>

    @GET("movie/{movie_id}/similar")
    fun getSimilarMovies(@Path("movie_id") movie_id:Int):Observable<MovieDTO>

    @GET("movie/{movie_id}/credits")
    fun getCredits(@Path("movie_id") movie_id:Int):Observable<CastCrewDTO>

    @GET("movie/{id}/reviews")
    fun getComments(@Path("id")id:Int):retrofit2.Call<CommentDTO>
}