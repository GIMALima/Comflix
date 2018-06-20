package com.example.slash.comflix.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.*
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import com.example.slash.comflix.R
import com.example.slash.comflix.adapter.MovieAdapter
import com.example.slash.comflix.calculateCardNum
import com.example.slash.comflix.entities.GridSpacingItemDecoration
import com.example.slash.comflix.entities.Movie
import com.example.slash.comflix.entities.dpToPx
import com.example.slash.comflix.getMovies
import com.example.slash.comflix.prepareMovies
import kotlinx.android.synthetic.main.app_bar_main.*

class MoviesFragment : Fragment(){
   var listMovies:ArrayList<Movie>?=null
   var adapterMovie:MovieAdapter?=null
    private var mListener: OnFragmentInteractionListener? = null



     fun search()
     {
         var item: MenuItem = this.activity.toolbar.menu.findItem(R.id.menu_search)

    }
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var view= inflater!!.inflate(R.layout.fragment_movies, container, false)
        var movieList=ArrayList<Movie>()
        var recyclerView=view.findViewById<RecyclerView>(R.id.recyclerView) as RecyclerView
        var movieAdapter= MovieAdapter(this.context,movieList,R.layout.movie_card)
        var mLayoutManager:RecyclerView.LayoutManager= GridLayoutManager(this.context,calculateCardNum(this.context))
        recyclerView.addItemDecoration(GridSpacingItemDecoration(2,dpToPx(10),true))
        recyclerView.layoutManager=mLayoutManager
        recyclerView.itemAnimator=DefaultItemAnimator()
        recyclerView.adapter=movieAdapter
        adapterMovie=movieAdapter
        listMovies=movieList
        prepareMovies(this.context,movieList,movieAdapter)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

         /*   var item = this.activity.toolbar.menu.findItem(R.id.menu_search)
            val searchView: SearchView = item.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String): Boolean {

                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {

                    var i = 0
                    var allMovie = getMovies(this@MoviesFragment.context)
                    while (i < allMovie!!.size) {
                        if (!allMovie!!.get(i).title!!.contains(newText, true)) {
                            var x = 0
                            while (x < listMovies!!.size) {
                                if (!listMovies!!.get(x).title!!.contains(newText, true)) {
                                    listMovies!!.removeAt(x)
                                }
                                x++
                            }
                        } else {
                            var add = true
                            var j = 0
                            while (add && j < listMovies!!.size) {
                                if (listMovies!!.get(j).title!!.equals(allMovie!!.get(i).title)) {
                                    add = false
                                }
                                j++
                            }
                            if (add) {
                                listMovies!!.add(allMovie.get(i))
                            }
                        }
                        adapterMovie!!.notifyDataSetChanged()
                        i++
                    }
                    return false
                }
            })*/

    }

    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }

    override fun onAttach(context: Context?) {

        super.onAttach(context)
        //Toast.makeText(this.context,"dssdss",Toast.LENGTH_LONG).show()
        /*var item = this.activity.toolbar.menu.findItem(R.id.menu_search)
        val searchView: SearchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

                var i = 0
                var allMovie = getMovies(this@MoviesFragment.context)
                while (i < allMovie!!.size) {
                    if (!allMovie!!.get(i).title!!.contains(newText, true)) {
                        var x = 0
                        while (x < listMovies!!.size) {
                            if (!listMovies!!.get(x).title!!.contains(newText, true)) {
                                listMovies!!.removeAt(x)
                            }
                            x++
                        }
                    } else {
                        var add = true
                        var j = 0
                        while (add && j < listMovies!!.size) {
                            if (listMovies!!.get(j).title!!.equals(allMovie!!.get(i).title)) {
                                add = false
                            }
                            j++
                        }
                        if (add) {
                            listMovies!!.add(allMovie.get(i))
                        }
                    }
                    adapterMovie!!.notifyDataSetChanged()
                    i++
                }
                return false
            }
        })*/
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


    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {

        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): MoviesFragment {
            val fragment = MoviesFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
