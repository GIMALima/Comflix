package com.example.slash.comflix.fragment

import android.app.AlertDialog
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
import android.widget.*
import com.bumptech.glide.Glide
import com.example.slash.comflix.R
import com.example.slash.comflix.adapter.PersonAdapter
import com.example.slash.comflix.entities.GridSpacingItemDecoration
import com.example.slash.comflix.entities.Person
import com.example.slash.comflix.entities.dpToPx
import kotlinx.android.synthetic.main.fragment_serie_details.*

class SerieDetailsFragment : Fragment(), AdapterView.OnItemClickListener {

    private var serieID: Int = -1

    private var mListener: OnFragmentInteractionListener? = null
    private var alertDialog:AlertDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null)
        {
            serieID = arguments.getInt(SERIE_ID_PARAM)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val fragView = inflater!!.inflate(R.layout.fragment_serie_details, container, false)

        fragView.findViewById<TextView>(R.id.nbrSeason).text =resources.getIntArray(R.array.serieSeasons)[serieID].toString() + " "
        fragView.findViewById<Button>(R.id.seeSeasons).setOnClickListener { showSeasonsDialog() }
        var personLayoutManager: RecyclerView.LayoutManager= GridLayoutManager(this.context,1, GridLayoutManager.HORIZONTAL,false)
        var actorsRecyclerView=fragView.findViewById<RecyclerView>(R.id.actorsRecyclerView) as RecyclerView
        var personRelativeList=ArrayList<Person>()
        var personAdapter= PersonAdapter(this.context,personRelativeList,R.layout.person_relative_card)
        actorsRecyclerView.addItemDecoration(GridSpacingItemDecoration(2, dpToPx(10),true))
        actorsRecyclerView.layoutManager=personLayoutManager
        actorsRecyclerView.itemAnimator= DefaultItemAnimator()
        actorsRecyclerView.adapter=personAdapter

        return fragView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var covers= intArrayOf(

                R.drawable.houseofcards,
                R.drawable.gameofthrones,
                R.drawable.friends,
                R.drawable.suits,
                R.drawable.vikings,
                R.drawable.breakingbad,
                R.drawable.lacasadepapel,
                R.drawable.prisonbreak
        )
        Glide.with(this.context).load(covers.get(serieID)).into(serieCover)
}

// TODO: Rename method, update argument and hook method into UI event
fun onButtonPressed(uri: Uri) {
if (mListener != null) {
    mListener!!.onFragmentInteraction()
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

alertDialog = builder.create()
alertDialog?.show()

}



override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long)
{

    alertDialog?.dismiss()
    mListener?.onFragmentInteraction()
}


interface OnFragmentInteractionListener
{
fun onFragmentInteraction()
}

companion object {

private val SERIE_ID_PARAM = "id"

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
