package com.example.slash.comflix.fragment

import android.support.v4.app.Fragment
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.slash.comflix.EpisodesActivity
import com.example.slash.comflix.R
import com.example.slash.comflix.entities.Episode
import com.example.slash.comflix.entities.RetrofitBuilder
import com.example.slash.comflix.entities.Season
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.season_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by thinkpad on 20/04/2018.
 */
class SeasonDetailsFragment: Fragment()
{

    private var serieID: Int = 0
    private var seasonNumber:Int = 0
    private var mListener: SeasonDetailsInteraction? = null


    private var  nbrEpisodes =0


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val fragView = inflater!!.inflate(R.layout.season_details, container, false)

        fragView.findViewById<Button>(R.id.seeSeasons).setOnClickListener { showEpisodesActivity() }



        return fragView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            if (arguments != null) {
                serieID = arguments.getInt(SERIE_ID_PARAM)
                seasonNumber = arguments.getInt(SEASON_NBR_PARAM)
            }


    }


    override fun onStart() {
        super.onStart()
        Toast.makeText(activity,"$serieID,$seasonNumber",Toast.LENGTH_LONG).show()

        RetrofitBuilder.serieApi.getSeason(serieID,seasonNumber).enqueue(object: Callback<Season>
        {
            override fun onFailure(call: Call<Season>?, t: Throwable?) {
                Toast.makeText(activity,"Network problem",Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<Season>?, response: Response<Season>?) {

                if(response?.isSuccessful!!)
                {
                    Toast.makeText(activity,"success",Toast.LENGTH_LONG).show()

                    val result =response.body()!!
                    Picasso.with(activity).load(activity.getString(R.string.image_url)+result.poster_path).into(cover)
                    title.text = result.name
                    season_nbr.text = result.season_number.toString()

                    projection_date.text = result.air_date ?: "Inavailable"
                    description.text =if (result.overview!= null) if(!result.overview.isBlank()) result.overview else "Inavailable" else "Inavailable"
                    nbrSeason.text = result.episodes.size.toString()
                    mListener?.addSeasonToTitle(result.name)
                    currentSeasonEpisodes = result.episodes
                }else
                {
                    Toast.makeText(activity,response.message().toString(),Toast.LENGTH_LONG).show()

                }

            }

        })
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is SeasonDetailsInteraction) {
            mListener = context
        } else {
             throw RuntimeException(context!!.toString() + " must implement SeasonDetailsFragment")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }


    fun showEpisodesActivity()
    {
        val intent = Intent(context,EpisodesActivity::class.java).apply {
            putExtra("Title",mListener?.getActionBarTitle())
        }

        startActivity(intent)

    }

    interface SeasonDetailsInteraction
    {
        fun addSeasonToTitle(season: String)
        fun getActionBarTitle():String
    }

    companion object {

        private val SERIE_ID_PARAM = "seasonID"
        private val SEASON_NBR_PARAM = "seasonNUMBER"

        var currentSeasonEpisodes:ArrayList<Episode>? = null

        fun newInstance(serieId: Int,seasonNumber:Int): SeasonDetailsFragment
        {
            val fragment = SeasonDetailsFragment()
            val bundle = Bundle()
            bundle.putInt(SERIE_ID_PARAM,serieId)
            bundle.putInt(SEASON_NBR_PARAM,seasonNumber)

            fragment.arguments = bundle
            return fragment
        }


    }
}