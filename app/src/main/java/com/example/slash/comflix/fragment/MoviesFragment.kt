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
import com.example.slash.comflix.*
import com.example.slash.comflix.adapter.MovieAdapter
import com.example.slash.comflix.entities.GridSpacingItemDecoration
import com.example.slash.comflix.entities.Movie
import com.example.slash.comflix.entities.dpToPx


class MoviesFragment : Fragment(){


    private var mListener: OnFragmentInteractionListener? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var view= inflater!!.inflate(R.layout.fragment_movies, container, false)
        var movieList=ArrayList<Movie>()
        var recyclerView=view.findViewById<RecyclerView>(R.id.recyclerView) as RecyclerView
        var movieAdapter= MovieAdapter(this.context,movieList,R.layout.movie_card)
        var mLayoutManager= GridLayoutManager(this.context,calculateCardNum(this.context))
        recyclerView.addItemDecoration(GridSpacingItemDecoration(2,dpToPx(10),true))
        recyclerView.layoutManager=mLayoutManager
        recyclerView.itemAnimator=DefaultItemAnimator()
        recyclerView.adapter=movieAdapter
        getListMoviesPopular(movieAdapter, movieList)
        if(chargerScoll(recyclerView,mLayoutManager)){
            getListMoviesPopular(movieAdapter,movieList)
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
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {

        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): MoviesFragment {
            val fragment = MoviesFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
