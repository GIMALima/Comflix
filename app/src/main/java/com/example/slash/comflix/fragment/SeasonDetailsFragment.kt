package com.example.slash.comflix.fragment

import android.support.v4.app.Fragment
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.slash.comflix.R

/**
 * Created by thinkpad on 20/04/2018.
 */
class SeasonDetailsFragment: Fragment()
{

    private var serieID: Int = 0

    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val fragView = inflater!!.inflate(R.layout.season_details, container, false)


        fragView.findViewById<ImageView>(R.id.cover).
                setImageDrawable(
                        resources.getDrawable(
                                resources.obtainTypedArray(
                                        R.array.episodesCover).getResourceId(
                                        serieID,-1)))

        fragView.findViewById<TextView>(R.id.nbrSeason).text =resources.getIntArray(R.array.serieEpisodes)[serieID].toString() + " "

        fragView.findViewById<Button>(R.id.seeSeasons).setOnClickListener { showEpisodesActivity() }

        return fragView
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
            // throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }


    fun showEpisodesActivity()
    {

    }

    interface OnFragmentInteractionListener
    {
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {

        private val SERIE_ID_PARAM = "seasonID"

        fun newInstance(serieId: Int): SeasonDetailsFragment
        {
            val fragment = SeasonDetailsFragment()
            val bundle = Bundle()
            bundle.putInt(SERIE_ID_PARAM,serieId)
            fragment.arguments = bundle
            return fragment
        }
    }
}