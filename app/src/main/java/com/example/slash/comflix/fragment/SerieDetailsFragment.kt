package com.example.slash.comflix.fragment

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.slash.comflix.R
import com.example.slash.comflix.adapter.PersonAdapter
import com.example.slash.comflix.adapter.SerieAdapter
import com.example.slash.comflix.entities.*
import com.example.slash.comflix.getCastCrewSeries
import com.example.slash.comflix.getSimilarSeries
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_serie_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SerieDetailsFragment : Fragment(), AdapterView.OnItemClickListener {

    var serieID: Int = -1

    private var numberOfSseasons = 0
    private var mListener: OnFragmentInteractionListener? = null
    private var alertDialog: AlertDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            serieID = arguments.getInt(SERIE_ID_PARAM)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val fragView = inflater!!.inflate(R.layout.fragment_serie_details, container, false)


        fragView.findViewById<Button>(R.id.seeSeasons).setOnClickListener { showSeasonsDialog() }


        var movieLayoutManager: RecyclerView.LayoutManager= GridLayoutManager(this.context,1,GridLayoutManager.HORIZONTAL,false)
        var movieRecyclerView=fragView.findViewById<RecyclerView>(R.id.moviesRecyclerView) as RecyclerView
        var movieRelativeList=ArrayList<Serie>()
        var movieAdapter= SerieAdapter(this.context,movieRelativeList,R.layout.trending_card)
        movieRecyclerView.addItemDecoration(GridSpacingItemDecoration(2, dpToPx(10),true))
        movieRecyclerView.layoutManager=movieLayoutManager
        movieRecyclerView.itemAnimator= DefaultItemAnimator()
        movieRecyclerView.adapter=movieAdapter
        getSimilarSeries(serieID,movieAdapter)


        var personLayoutManager: RecyclerView.LayoutManager= GridLayoutManager(this.context,1,GridLayoutManager.HORIZONTAL,false)
        var actorsRecyclerView=fragView.findViewById<RecyclerView>(R.id.actorsRecyclerView) as RecyclerView
        var personRelativeList=ArrayList<Person>()
        var personAdapter= PersonAdapter(this.context,personRelativeList,R.layout.person_relative_card,2)
        actorsRecyclerView.addItemDecoration(GridSpacingItemDecoration(2, dpToPx(10),true))
        actorsRecyclerView.layoutManager=personLayoutManager
        actorsRecyclerView.itemAnimator= DefaultItemAnimator()
        actorsRecyclerView.adapter=personAdapter
        getCastCrewSeries(serieID,personAdapter)

        mListener?.loadCOmmentsSeries(serieID)
        return fragView
    }

    override fun onStart()
    {
        super.onStart()
        RetrofitBuilder.serieApi.getSeriesDetails(serieID).enqueue(object: Callback<SerieDetails>
        {
            override fun onFailure(call: Call<SerieDetails>?, t: Throwable?) {
                Toast.makeText(activity,"Network problem",Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<SerieDetails>?, response: Response<SerieDetails>?) {

                if(response?.isSuccessful!!)
                {
                    val serie = response?.body()
                    (activity as OnFragmentInteractionListener).changeBarTitle(serie!!.name)
                    Picasso.with(activity).load(activity.getString(R.string.image_url)+serie?.poster_path).into(movieCover)
                    title.text = serie?.name
                    genre.text = serie?.getGendersFormatted()
                    episode_time.text = serie!!.episode_run_time[0] + " min"
                    first_release_date.text = serie.first_air_date
                    last_release_date.text = serie.last_air_date + " (${if(serie.in_production) "still in production" else "not in production"})"
                    votes.text=serie.vote_count.toString()+" votes"
                    average.text=serie.vote_average.toString()+"/10"
                    description.text = serie.overview
                    nbrSeasons.text = "${serie.number_of_seasons} seasons"
                    numberOfSseasons = serie.number_of_seasons

                }
            }

        })


    }



    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context

        } else {
            // throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }


    fun showSeasonsDialog() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Choose a season")
        var array: ArrayList<String> = ArrayList()
        for (i in 1..numberOfSseasons)
            array.add("Season $i")
        val arrayAdapter = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, array)

        val lv = ListView(context)
        lv.adapter = arrayAdapter
        lv.onItemClickListener = this
        builder.setView(lv)
        builder.setNegativeButton("Annuler", null)

        alertDialog = builder.create()
        alertDialog?.show()

    }


    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        alertDialog?.dismiss()
        mListener?.onFragmentInteraction(serieID,position)
    }


    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(serieID:Int,position: Int)
        fun changeBarTitle(title:String)
        fun loadCOmmentsSeries(serieID: Int)
    }

    companion object {

        private val SERIE_ID_PARAM = "id"

        fun newInstance(serieId: Int): SerieDetailsFragment {
            val fragment = SerieDetailsFragment()
            val bundle = Bundle()
            bundle.putInt(SERIE_ID_PARAM, serieId)
            fragment.arguments = bundle
            return fragment
        }
    }
}// Required empty public constructor
