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
import com.bumptech.glide.Glide

import com.example.slash.comflix.R
import com.example.slash.comflix.adapter.MovieAdapter
import com.example.slash.comflix.entities.GridSpacingItemDecoration
import com.example.slash.comflix.entities.Movie
import com.example.slash.comflix.entities.dpToPx
import com.example.slash.comflix.prepareMovies
import kotlinx.android.synthetic.main.fragment_cinema_details.*


class CinemaDetailsFragment : Fragment() {

    // TODO: Rename and change types of parameters
    var cinemaId=0

    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
             cinemaId=arguments.getInt(ARG_PARAM1)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var cinemaPictures= intArrayOf(
                R.drawable.ibnkhaldoun,
                R.drawable.atlas,
                R.drawable.mouggar,
                R.drawable.chabab,
                R.drawable.ibnkhaldoun,
                R.drawable.ibnkhaldoun,
                R.drawable.ibnkhaldoun,
                R.drawable.ibnkhaldoun,
                R.drawable.atlas,
                R.drawable.mouggar
        )
        //   Toast.makeText(this.context,movieId.toString(),Toast.LENGTH_LONG).show()
        Glide.with(this.context).load( cinemaPictures.get(cinemaId)).into(cinemaPicture)
        cinema.text=resources.getStringArray(R.array.cinemaName).get(cinemaId)
        location.text=resources.getStringArray(R.array.cinemaLocation).get(cinemaId)
        num_salle.text="Salle"+" "+resources.getIntArray(R.array.nb_salleCinema).get(cinemaId).toString()

    }
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var view=inflater!!.inflate(R.layout.fragment_cinema_details, container, false)
        var movieLayoutManager: RecyclerView.LayoutManager= GridLayoutManager(this.context,1,GridLayoutManager.HORIZONTAL,false)
        var movieRecyclerView=view.findViewById<RecyclerView>(R.id.moviesRecyclerView) as RecyclerView
        var movieRelativeList=ArrayList<Movie>()
        var movieAdapter= MovieAdapter(this.context,movieRelativeList,R.layout.trending_movie_card)
        movieRecyclerView.addItemDecoration(GridSpacingItemDecoration(2, dpToPx(10),true))
        movieRecyclerView.layoutManager=movieLayoutManager
        movieRecyclerView.itemAnimator= DefaultItemAnimator()
        movieRecyclerView.adapter=movieAdapter
        prepareMovies(this.context,movieRelativeList,movieAdapter)
        return view
    }

    // TODO: Rename method, update argument and hook method into UI event
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
         //   throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
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
        private val ARG_PARAM1 = "id"

        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: Int): CinemaDetailsFragment {
            val fragment = CinemaDetailsFragment()
            val args = Bundle()
            args.putInt(ARG_PARAM1, param1)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
