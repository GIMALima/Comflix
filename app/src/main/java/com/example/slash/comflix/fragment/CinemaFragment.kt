package com.example.slash.comflix.fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.slash.comflix.DetailsActivity
import com.example.slash.comflix.R
import com.example.slash.comflix.adapter.CinemaAdapter
import com.example.slash.comflix.entities.Cinema
import com.example.slash.comflix.entities.GridSpacingItemDecoration
import com.example.slash.comflix.entities.dpToPx
import com.example.slash.comflix.prepareFavouriteCinema
import kotlinx.android.synthetic.main.fragment_cinema.*


class CinemaFragment:Fragment() {



            private var mListener:OnFragmentInteractionListener? = null

            public override fun onCreate(savedInstanceState:Bundle?) {
            super.onCreate(savedInstanceState)

            }

            public override fun onCreateView(inflater:LayoutInflater?, container:ViewGroup?,
            savedInstanceState:Bundle?):View? {

                var view= inflater!!.inflate(R.layout.fragment_cinema, container, false)
                var cinemaList=ArrayList<Cinema>()
                var recyclerView=view.findViewById<RecyclerView>(R.id.cinemaRecyclerView) as RecyclerView
                var cinemaAdapter= CinemaAdapter(this.context,cinemaList,R.layout.relative_cinema_card)
                var mLayoutManager: RecyclerView.LayoutManager= GridLayoutManager(this.context,1)
                recyclerView.addItemDecoration(GridSpacingItemDecoration(2, dpToPx(10),true))
                recyclerView.layoutManager=mLayoutManager
                recyclerView.itemAnimator= DefaultItemAnimator()
                recyclerView.adapter=cinemaAdapter
                prepareFavouriteCinema(this.context,cinemaList,cinemaAdapter)
                return view
            }

            override fun onActivityCreated(savedInstanceState: Bundle?) {
                super.onActivityCreated(savedInstanceState)
                near_cinema.setOnClickListener{
                    Toast.makeText(this.context,"ddddddddd",Toast.LENGTH_LONG).show()
                }
                see_favourite_cinema.setOnClickListener{

                    val intent = Intent(this.context, DetailsActivity::class.java)
                    val bundle = Bundle()
                    bundle.putInt("id", 0) //Your id
                    bundle.putString("type","cinema_favouris")
                    intent.putExtras(bundle) //P

                    this.context.startActivity(intent)
                }

            }

             // TODO: Rename method, update argument and hook method into UI event
                 fun onButtonPressed(uri:Uri) {
            if (mListener != null)
            {
            mListener!!.onFragmentInteraction(uri)
            }
            }

            public override fun onAttach(context:Context?) {
            super.onAttach(context)
            if (context is OnFragmentInteractionListener)
            {
            mListener = context as OnFragmentInteractionListener?
            }
            else
            {
            //throw RuntimeException((context!!.toString() + " must implement OnFragmentInteractionListener"))
            }
            }

            public override fun onDetach() {
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
                     fun onFragmentInteraction(uri:Uri)
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
             * @return A new instance of fragment CinemaFragment.
             */
                // TODO: Rename and change types and number of parameters
                 fun newInstance(param1:String, param2:String):CinemaFragment {
            val fragment = CinemaFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.setArguments(args)
            return fragment
            }
            }
}// Required empty public constructor
