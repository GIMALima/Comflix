package com.example.slash.comflix.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.slash.comflix.R
import com.example.slash.comflix.adapter.MovieAdapter
import com.example.slash.comflix.adapter.SerieAdapter
import com.example.slash.comflix.chargerScoll
import com.example.slash.comflix.entities.GridSpacingItemDecoration
import com.example.slash.comflix.entities.Movie
import com.example.slash.comflix.entities.Serie
import com.example.slash.comflix.entities.dpToPx
import com.example.slash.comflix.getListMoviesNowPlaying
import com.example.slash.comflix.getListSeries


class TrendingFragment : Fragment() {


    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.fragment_trending, container, false)
        //Trending movies
        var trendingMoviesList=ArrayList<Movie>()
        var movieRecyclerView=view.findViewById<RecyclerView>(R.id.moviesRecyclerView) as RecyclerView
        var movieAdapter= MovieAdapter(this.context,trendingMoviesList,R.layout.movie_card)
        var movieLayoutManager = GridLayoutManager(this.context,1,GridLayoutManager.HORIZONTAL,false)
        movieRecyclerView.addItemDecoration(GridSpacingItemDecoration(2, dpToPx(10),true))
        movieRecyclerView.layoutManager=movieLayoutManager
        movieRecyclerView.itemAnimator= DefaultItemAnimator()
        movieRecyclerView.adapter=movieAdapter
        getListMoviesNowPlaying(movieAdapter, trendingMoviesList)
        if(chargerScoll(movieRecyclerView,movieLayoutManager)){
            getListMoviesNowPlaying(movieAdapter,trendingMoviesList)
        }
        //Trending series
        var trendingseriesList=ArrayList<Serie>()
        var serieRecyclerView=view.findViewById<RecyclerView>(R.id.seriesRecyclerView) as RecyclerView
        var serieAdapter= SerieAdapter(this.context,trendingseriesList,R.layout.movie_card)
        var serieLayoutManager = GridLayoutManager(this.context,1,GridLayoutManager.HORIZONTAL,false)
        serieRecyclerView.addItemDecoration(GridSpacingItemDecoration(2, dpToPx(10),true))
        serieRecyclerView.layoutManager=serieLayoutManager
        serieRecyclerView.itemAnimator= DefaultItemAnimator()
        serieRecyclerView.adapter=serieAdapter
        getListSeries(serieAdapter,trendingseriesList)
        if(chargerScoll(serieRecyclerView,serieLayoutManager)){
            getListSeries(serieAdapter,trendingseriesList)
        }
        return view
    }

    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
           // throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }


    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }


}
