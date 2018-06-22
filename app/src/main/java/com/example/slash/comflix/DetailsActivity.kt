package com.example.slash.comflix

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v4.app.Fragment
import android.support.v4.app.NavUtils
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.slash.comflix.fragment.MovieDetailsFragment
import com.example.slash.comflix.fragment.SerieDetailsFragment
import com.example.slash.comflix.fragment.PersonDetailsFragment
import com.example.slash.comflix.fragment.SeasonDetailsFragment
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.slash.comflix.adapter.CommentsAdapter
import com.example.slash.comflix.entities.Comment
import com.example.slash.comflix.entities.CommentDTO
import com.example.slash.comflix.entities.RetrofitBuilder
import com.example.slash.comflix.fragment.*
import kotlinx.android.synthetic.main.activity_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class DetailsActivity : AppCompatActivity(),SerieDetailsFragment.OnFragmentInteractionListener,
        SeasonDetailsFragment.SeasonDetailsInteraction
{
    override fun addSeasonToTitle(season: String) {
        if(!supportActionBar?.title!!.contains(":"))
            supportActionBar?.title = "${supportActionBar?.title} : $season"
    }

    override fun getActionBarTitle():String
    {
        return supportActionBar?.title.toString()!!
    }

    override fun changeBarTitle(title:String) {
        supportActionBar?.title = title
    }

    var fragment: Fragment?=null

    var isLiked= false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setSupportActionBar(findViewById(R.id.retour_toolbar))
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val type = intent.getStringExtra("type")
        val id= intent.getIntExtra("id",0)
        var bundle= Bundle()
        bundle.putInt("id",id)

       when (type) {
           "movie" -> {
               fragment = MovieDetailsFragment()
           }
           "serie" -> {
               fragment = SerieDetailsFragment()
           }
           "person" -> {
               fragment = PersonDetailsFragment()
           }

       }
        fragment!!.arguments=bundle
        supportFragmentManager.beginTransaction()
                .replace(details_container.id,fragment)
                .commit()

        val bottomsheet= BottomSheetBehavior.from(comments_section)

        comment.setOnClickListener{
            bottomsheet.state = BottomSheetBehavior.STATE_EXPANDED
        }

        bottomsheet.setBottomSheetCallback(object:BottomSheetBehavior.BottomSheetCallback()
        {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
               return
            }

            override fun onStateChanged(bottomSheet: View, newState: Int)
            {
                when(newState)
                {
                    BottomSheetBehavior.STATE_COLLAPSED ->
                    {
                        comment.setImageDrawable(resources.getDrawable(R.drawable.ic_comment))
                        comment.setOnClickListener{
                            bottomsheet.state = BottomSheetBehavior.STATE_EXPANDED
                        }
                    }
                    BottomSheetBehavior.STATE_EXPANDED ->
                    {comment.setImageDrawable(resources.getDrawable(R.drawable.ic_collapse))
                     comment.setOnClickListener{
                        bottomsheet.state = BottomSheetBehavior.STATE_COLLAPSED
                    }}
                }
            }

        })

        makeComments()



    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.back_menu, menu)
        return true
    }


    override fun onFragmentInteraction(serieID:Int,position:Int)
    {
        val newFragment:Fragment = SeasonDetailsFragment.newInstance(serieID,position +1)
        supportFragmentManager.beginTransaction()
                .replace(fragment!!.id,newFragment)
                .addToBackStack(null)
                .commit()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
        // Respond to the action bar's Up/Home button
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    fun makeComments()
    {

        val viewManager = LinearLayoutManager(this)
        val commentsList = ArrayList<Comment>()
        val viewAdapter = CommentsAdapter(commentsList)
        comments_list.apply{
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter= viewAdapter
        }

        createCommentsLis(viewAdapter)



    }

    private fun createCommentsLis(viewAdapter:CommentsAdapter)
    {
        val typeFragment = fragment
        if(typeFragment is SerieDetailsFragment)
        {
            RetrofitBuilder.serieApi.getComments(typeFragment.serieID).enqueue(object: Callback<CommentDTO>{
                override fun onFailure(call: Call<CommentDTO>?, t: Throwable?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(call: Call<CommentDTO>?, response: Response<CommentDTO>?) {

                    if(response?.isSuccessful!!)
                    {
                        viewAdapter.commentsList = response.body()!!.results
                        viewAdapter.notifyDataSetChanged()
                    }
                }

            })

        }
    }


}


