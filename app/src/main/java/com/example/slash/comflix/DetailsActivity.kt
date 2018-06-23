package com.example.slash.comflix

import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v4.app.Fragment
import android.support.v4.app.NavUtils
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.slash.comflix.adapter.CommentsAdapter
import com.example.slash.comflix.entities.Comment
import com.example.slash.comflix.entities.CommentDTO
import com.example.slash.comflix.entities.RetrofitBuilder
import com.example.slash.comflix.fragment.MovieDetailsFragment
import com.example.slash.comflix.fragment.PersonDetailsFragment
import com.example.slash.comflix.fragment.SeasonDetailsFragment
import com.example.slash.comflix.fragment.SerieDetailsFragment
import kotlinx.android.synthetic.main.activity_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsActivity : AppCompatActivity(),SerieDetailsFragment.OnFragmentInteractionListener,
        SeasonDetailsFragment.SeasonDetailsInteraction,
        MovieDetailsFragment.OnFragmentInteractionListener
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


    var  commentsAdapter:CommentsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setSupportActionBar(findViewById(R.id.retour_toolbar))
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val type = intent.getStringExtra("type")
        val id= intent.getIntExtra("id",0)
        val pos= intent.getIntExtra("position",-1)
        val from= intent.getIntExtra("from",0)
        var bundle= Bundle()
        bundle.putInt("id",id)
        bundle.putInt("position",pos)
        bundle.putInt("from",from)
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
        commentsAdapter = CommentsAdapter(commentsList)
        comments_list.apply{
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter= commentsAdapter
        }



    }

    override fun loadCOmmentsSeries(serieID: Int)
    {
        val typeFragment = fragment
        if(typeFragment is SerieDetailsFragment)
        {
            RetrofitBuilder.serieApi.getComments(serieID).enqueue(object: Callback<CommentDTO>{
                override fun onFailure(call: Call<CommentDTO>?, t: Throwable?) {
                //Toast.makeText(this@DetailsActivity,"Network problem",Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<CommentDTO>?, response: Response<CommentDTO>?) {

                    if(response?.isSuccessful!!)
                    {
                        //Toast.makeText(this@DetailsActivity,"Sucess",Toast.LENGTH_SHORT).show()

                        commentsAdapter?.commentsList = response.body()!!.results
                        commentsAdapter?.notifyDataSetChanged()
                        total_reviews.text = response.body()!!.total_results.toString() + " Reviews"

                    }else
                    {
                        //Toast.makeText(this@DetailsActivity,response.message(),Toast.LENGTH_SHORT).show()

                    }
                }

            })

        }
    }



    override fun loadCOmmentsMovies(movieID: Int)
    {
        val typeFragment = fragment
        if(typeFragment is MovieDetailsFragment)
        {
            RetrofitBuilder.movieApi.getComments(movieID).enqueue(object: Callback<CommentDTO>{
                override fun onFailure(call: Call<CommentDTO>?, t: Throwable?) {
                }

                override fun onResponse(call: Call<CommentDTO>?, response: Response<CommentDTO>?) {

                    if(response?.isSuccessful!!)
                    {

                        commentsAdapter?.commentsList = response.body()!!.results
                        commentsAdapter?.notifyDataSetChanged()
                        total_reviews.text = response.body()!!.total_results.toString() + " Reviews"

                    }else
                    {
                        //Toast.makeText(this@DetailsActivity,response.message(),Toast.LENGTH_SHORT).show()

                    }
                }

            })

        }
    }


}


