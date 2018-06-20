package com.example.slash.comflix.ViewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.slash.comflix.R
import com.example.slash.comflix.entities.Movie
import com.google.gson.Gson
import org.json.JSONObject

class MovieViewModel(application: Application): AndroidViewModel(application) {

    private var applicationContext=application.applicationContext
    private var listMovies:MutableLiveData<ArrayList<Movie>>?=null
    private var listMovieNowPlaying=ArrayList<Movie>()

    fun getListMovieNowPlaying(): LiveData<ArrayList<Movie>>?{
        var queue= Volley.newRequestQueue(applicationContext)
        var query=requeteDemandeListMovieNowPlaying(applicationContext)
        queue.add(query)
        return listMovies
    }
    fun requeteDemandeListMovieNowPlaying(applicationContext: Context): JsonObjectRequest {

        val url=applicationContext.resources.getString(R.string.url)+applicationContext.getString(R.string.movie_url)

        var jsonRequest = object : JsonObjectRequest(Request.Method.GET, url, null, Response.Listener<JSONObject> { response ->
            listMovies= MutableLiveData()
            val gson = Gson()
            val jArray = response.getJSONArray(applicationContext.getString(R.string.results))
            listMovies!!.value= gson.fromJson(jArray.toString(),listMovieNowPlaying::class.java )
        }, Response.ErrorListener { error ->
            Log.i("erreur", error.toString())

        })
        {


        }
        return jsonRequest

    }


}