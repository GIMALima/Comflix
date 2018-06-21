package com.example.slash.comflix.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.slash.comflix.*
import com.example.slash.comflix.adapter.MovieAdapter
import com.example.slash.comflix.adapter.PersonAdapter
import com.example.slash.comflix.entities.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_movie_details.*

class MovieDetailsFragment : Fragment() {


    var movieId=0
    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
           movieId = arguments.getInt(ARG_PARAM1)
        }
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var view= inflater!!.inflate(R.layout.fragment_movie_details, container, false)

        getMovieDetails(movieId,this)




        var movieLayoutManager: RecyclerView.LayoutManager= GridLayoutManager(this.context,1,GridLayoutManager.HORIZONTAL,false)
        var movieRecyclerView=view.findViewById<RecyclerView>(R.id.moviesRecyclerView) as RecyclerView
        var movieRelativeList=ArrayList<Movie>()
        var movieAdapter= MovieAdapter(this.context,movieRelativeList,R.layout.trending_card)
        movieRecyclerView.addItemDecoration(GridSpacingItemDecoration(2, dpToPx(10),true))
        movieRecyclerView.layoutManager=movieLayoutManager
        movieRecyclerView.itemAnimator= DefaultItemAnimator()
        movieRecyclerView.adapter=movieAdapter
        getSimilarMovies(movieId,movieAdapter)


        var personLayoutManager: RecyclerView.LayoutManager= GridLayoutManager(this.context,1,GridLayoutManager.HORIZONTAL,false)
        var actorsRecyclerView=view.findViewById<RecyclerView>(R.id.actorsRecyclerView) as RecyclerView
        var personRelativeList=ArrayList<Person>()
        var personAdapter= PersonAdapter(this.context,personRelativeList,R.layout.person_relative_card)
        actorsRecyclerView.addItemDecoration(GridSpacingItemDecoration(2, dpToPx(10),true))
        actorsRecyclerView.layoutManager=personLayoutManager
        actorsRecyclerView.itemAnimator= DefaultItemAnimator()
        actorsRecyclerView.adapter=personAdapter
        getCastCrew(movieId,personAdapter)

        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }


    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }
    fun onQueryResponse(movieDetails:MovieDetails){
        Picasso.with(this.context).load(this.context.getString(R.string.image_url) + movieDetails!!.backdrop_path).into(movieCover)
        title.text = movieDetails.title
        genre.text=""
        for(i in 0 until movieDetails.genres.size) {
            genre.text= genre.text.toString()+movieDetails.genres[i].name+", "
        }
        time.text=movieDetails.runtime.toString()+" min"
        release_date.text=movieDetails.release_date
        votes.text=movieDetails.vote_count.toString()+" votes"
        average.text=movieDetails.vote_average.toString()+"/10"
        description.text=movieDetails.overview

    }

    companion object {

        private val ARG_PARAM1 = "id"
        fun newInstance(param1: Int): MovieDetailsFragment {
            val fragment = MovieDetailsFragment()
            val args = Bundle()
            args.putInt(ARG_PARAM1, param1)
            fragment.arguments = args
            return fragment
        }
    }
}