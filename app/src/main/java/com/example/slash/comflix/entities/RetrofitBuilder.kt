package com.example.slash.comflix.entities

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Slash on 20/06/2018.
 */
object RetrofitBuilder {


    val imageBaseURL = "https://image.tmdb.org/t/p/w500"

    private val clientInterceptor = Interceptor { chain ->
        var request = chain.request()
        val url = request.url().newBuilder().addQueryParameter("api_key", "b73297ba2fd4486c8d1e39f3e8d0c0e4").build()
        request = request.newBuilder().url(url).build()
        chain.proceed(request)
    }

    private val client = OkHttpClient.Builder()
            .addNetworkInterceptor(clientInterceptor)
            .build()

    val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.themoviedb.org/3/")
            .client(client)
            .build()

    val movieApi = retrofit.create(MovieAPIClient::class.java)
    val serieApi = retrofit.create(SeriesAPIClient::class.java)


}