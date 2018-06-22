package com.example.slash.comflix.entities

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface PersonAPIClient{

    @GET("person/{person_id}")
    fun getPersonDetails(@Path("person_id")person_id:Int): Observable<PersonDTO>

    @GET("person/popular")
    fun getPopularPersons(@Query("page") page:Int):Observable<PopularPersonDTO>
}