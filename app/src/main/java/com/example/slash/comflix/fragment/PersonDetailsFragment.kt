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
import com.example.slash.comflix.entities.GridSpacingItemDecoration
import com.example.slash.comflix.entities.Movie
import com.example.slash.comflix.entities.PersonDTO
import com.example.slash.comflix.entities.dpToPx
import com.example.slash.comflix.getPersonDetails
import com.example.slash.comflix.popular
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_person_details.*

class PersonDetailsFragment : Fragment() {

    var personId=0
    var position=-1
    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            personId = arguments.getInt(PersonDetailsFragment.ARG_PARAM1)
            position = arguments.getInt(PersonDetailsFragment.ARG_PARAM2)
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        super.onActivityCreated(savedInstanceState)


    }
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var view= inflater!!.inflate(R.layout.fragment_person_details, container, false)
        getPersonDetails(personId,this)
        var movieLayoutManager: RecyclerView.LayoutManager= GridLayoutManager(this.context,1,GridLayoutManager.HORIZONTAL,false)
        var movieRecyclerView=view.findViewById<RecyclerView>(R.id.filmRecyclerView) as RecyclerView
        var movieRelativeList=ArrayList<Movie>()
        var movieAdapter= MovieAdapter(this.context,movieRelativeList,R.layout.trending_card)
        movieRecyclerView.addItemDecoration(GridSpacingItemDecoration(2, dpToPx(10),true))
        movieRecyclerView.layoutManager=movieLayoutManager
        movieRecyclerView.itemAnimator= DefaultItemAnimator()
        movieRecyclerView.adapter=movieAdapter
        if(position!=-1 && popular!=null){

            movieRelativeList=popular!!.get(position)!!.known_for!!
            movieAdapter.updateListMovie(movieRelativeList)
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
//            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }
   fun onQueryResponse(personDetails: PersonDTO){
       Picasso.with(this.context).load(this.context.getString(R.string.image_url) + personDetails!!.profile_path).into(personPicture)
       name.text=personDetails.name
       title.text="Also known as: "
       for(i in 0 until personDetails.also_known_as.size) {
           title.text =title.text.toString() +", "+ personDetails.also_known_as.get(i)
       }
       date_birth.text=personDetails.birthday
       biography.text=personDetails.biography
       popularity.text=personDetails.popularity.toString()
       place_birth.text=personDetails.place_of_birth
   }
    companion object {

        private val ARG_PARAM1 = "id"
        private val ARG_PARAM2= "position"

        fun newInstance(param1: String,param2: Int): PersonDetailsFragment {
            val fragment = PersonDetailsFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putInt(ARG_PARAM2,param2)
            fragment.arguments = args
            return fragment
        }
    }
}
