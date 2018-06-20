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
import com.example.slash.comflix.adapter.PersonAdapter
import com.example.slash.comflix.entities.GridSpacingItemDecoration
import com.example.slash.comflix.entities.Movie
import com.example.slash.comflix.entities.Person
import com.example.slash.comflix.entities.dpToPx

class MovieDetailsFragment : Fragment() {


    var movieId=0
    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
           movieId = arguments.getInt(ARG_PARAM1)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var movieCovers= intArrayOf(
                R.drawable.divergent,
                R.drawable.hungergamescatchingfire,
                R.drawable.mazerunner,
                R.drawable.pirateofthecar,
                R.drawable.themazerunnerdeathcure,
                R.drawable.themazerunnerscorch
        )
     //   Toast.makeText(this.context,movieId.toString(),Toast.LENGTH_LONG).show()
/*        Glide.with(this.context).load( movieCovers.get(movieId)).into(movieCover)
        title.text=resources.getStringArray(R.array.movieTitles).get(movieId)
        director.text=resources.getStringArray(R.array.movieDirector).get(movieId)
        writers.text=resources.getStringArray(R.array.movieWriters).get(movieId)
        stars.text=resources.getStringArray(R.array.movieStars).get(movieId)
        genre.text=resources.getStringArray(R.array.movieGenre).get(movieId)
        time.text=resources.getStringArray(R.array.movieTime).get(movieId)
        releae_date.text=resources.getStringArray(R.array.movieRelease).get(movieId)
        description.text=resources.getStringArray(R.array.movieDescription).get(movieId)
        cinema.setOnClickListener {
            val intent = Intent(this.context, DetailsActivity::class.java)
            val bundle = Bundle()
            bundle.putInt("id", movieId.toString().toInt()) //Your id
            bundle.putString("type","cinema")
            intent.putExtras(bundle) //P
            this.context.startActivity(intent)

        }*/
    }
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view= inflater!!.inflate(R.layout.fragment_movie_details, container, false)
        var movieLayoutManager: RecyclerView.LayoutManager= GridLayoutManager(this.context,1,GridLayoutManager.HORIZONTAL,false)
        var personLayoutManager: RecyclerView.LayoutManager= GridLayoutManager(this.context,1,GridLayoutManager.HORIZONTAL,false)

        var movieRecyclerView=view.findViewById<RecyclerView>(R.id.moviesRecyclerView) as RecyclerView
        var movieRelativeList=ArrayList<Movie>()
        var movieAdapter= MovieAdapter(this.context,movieRelativeList,R.layout.movie_card)
        movieRecyclerView.addItemDecoration(GridSpacingItemDecoration(2, dpToPx(10),true))
        movieRecyclerView.layoutManager=movieLayoutManager
        movieRecyclerView.itemAnimator= DefaultItemAnimator()
        movieRecyclerView.adapter=movieAdapter
        prepareMovies(this.context,movieRelativeList,movieAdapter)
        var actorsRecyclerView=view.findViewById<RecyclerView>(R.id.actorsRecyclerView) as RecyclerView
        var personRelativeList=ArrayList<Person>()
        var personAdapter= PersonAdapter(this.context,personRelativeList,R.layout.person_relative_card)
        actorsRecyclerView.addItemDecoration(GridSpacingItemDecoration(2, dpToPx(10),true))
        actorsRecyclerView.layoutManager=personLayoutManager
        actorsRecyclerView.itemAnimator= DefaultItemAnimator()
        actorsRecyclerView.adapter=personAdapter
        preparePersons(this.context,personRelativeList,personAdapter)
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
            //throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
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
        private val ARG_PARAM1 = "id"


        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: Int): MovieDetailsFragment {
            val fragment = MovieDetailsFragment()
            val args = Bundle()
            args.putInt(ARG_PARAM1, param1)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
