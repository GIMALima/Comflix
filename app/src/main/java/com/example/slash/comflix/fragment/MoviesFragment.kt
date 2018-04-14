package com.example.slash.comflix.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.slash.comflix.R
import com.example.slash.comflix.adapter.MovieAdapter
import com.example.slash.comflix.entities.LinearLayoutSpaceItemDecoration
import com.example.slash.comflix.entities.Movie

class MoviesFragment : Fragment() {



    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        var view= inflater!!.inflate(R.layout.fragment_movies, container, false)
        var movieList=ArrayList<Movie>()
        var recyclerView=view.findViewById<RecyclerView>(R.id.recyclerView) as RecyclerView
        var movieAdapter= MovieAdapter(this.context,movieList)
        var mLayoutManager:RecyclerView.LayoutManager= LinearLayoutManager(this.context)
        recyclerView.addItemDecoration(LinearLayoutSpaceItemDecoration(0))
        recyclerView.layoutManager=mLayoutManager
        recyclerView.itemAnimator=DefaultItemAnimator()
        recyclerView.adapter=movieAdapter
        prepareMovies(movieList,movieAdapter)
        return view
    }
    fun prepareMovies(movieList:ArrayList<Movie>, movieAdapter: MovieAdapter){
       var covers= intArrayOf(
               R.drawable.divergent,
               R.drawable.hungergamescatchingfire,
               R.drawable.mazerunner,
               R.drawable.pirateofthecar,
               R.drawable.themazerunnerdeathcure,
               R.drawable.themazerunnerscorch
       )
        var movieTitles=resources.getStringArray(R.array.movieTitles)
        var movieTime= resources.getStringArray(R.array.movieTime)
        for (i in 0 until covers.size){
            var movie=Movie(movieTitles.get(i),covers.get(i),movieTime.get(i))
            movieList.add(movie)
        }
        movieAdapter.notifyDataSetChanged()
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MoviesFragment.
         */
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
