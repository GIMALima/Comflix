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
import kotlinx.android.synthetic.main.fragment_person_details.*

class PersonDetailsFragment : Fragment() {

    var personId=0
    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            personId = arguments.getInt(PersonDetailsFragment.ARG_PARAM1)
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        super.onActivityCreated(savedInstanceState)

        Glide.with(this.context).load( R.drawable.dyl).into(personPicture)
        title.text=resources.getStringArray(R.array.personTitles).get(personId)
        name.text=resources.getStringArray(R.array.personName).get(personId)
        date_birth.text=resources.getStringArray(R.array.personDateOfBirth).get(personId)
        biography.text=resources.getStringArray(R.array.personBiographie).get(personId)
    }
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view= inflater!!.inflate(R.layout.fragment_person_details, container, false)
        var movieLayoutManager: RecyclerView.LayoutManager= GridLayoutManager(this.context,1,GridLayoutManager.HORIZONTAL,false)
        var movieRecyclerView=view.findViewById<RecyclerView>(R.id.filmRecyclerView) as RecyclerView
        var movieRelativeList=ArrayList<Movie>()
        var movieAdapter= MovieAdapter(this.context,movieRelativeList,R.layout.movie_card)
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
//            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
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

        fun newInstance(param1: String): PersonDetailsFragment {
            val fragment = PersonDetailsFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
