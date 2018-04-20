package com.example.slash.comflix.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.res.Resources
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

import com.example.slash.comflix.R

class SerieDetailsFragment : Fragment(), AdapterView.OnItemClickListener {

    private var serieID: Int = -1

    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null)
        {
            serieID = arguments.getInt("id")
            Log.d("SerieDetailsFragment",serieID.toString())
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val fragView = inflater!!.inflate(R.layout.fragment_serie_details, container, false)


        fragView.findViewById<ImageView>(R.id.cover).
                setImageDrawable(
                        resources.getDrawable(
                                resources.obtainTypedArray(
                                        R.array.serieCovers).getResourceId(
                                        serieID,-1)))

        fragView.findViewById<TextView>(R.id.nbrSeason).text =resources.getIntArray(R.array.serieSeasons)[serieID].toString() + " "

        fragView.findViewById<Button>(R.id.seeSeasons).setOnClickListener { showSeasonsDialog() }

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


    fun showSeasonsDialog()
    {
        val builder= AlertDialog.Builder(context)
        builder.setTitle("Choose a season")
        var array: ArrayList<String> = ArrayList()
        for (i in 1..resources.getIntArray(R.array.serieSeasons)[serieID])
            array.add("Season $i")
        val arrayAdapter = ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,array)

        val lv = ListView(context)
        lv.adapter = arrayAdapter
        lv.onItemClickListener = this
        builder.setView(lv)
        builder.setNegativeButton("Annuler",null)


        builder.create().show()

    }



    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long)
    {

    }


    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {

        private val SERIE_ID_PARAM = "serieId"

        fun newInstance(serieId: Int): SerieDetailsFragment
        {
            val fragment = SerieDetailsFragment()
            val bundle = Bundle()
            bundle.putInt(SERIE_ID_PARAM,serieId)
            fragment.arguments = bundle
            return fragment
        }
    }
}// Required empty public constructor
