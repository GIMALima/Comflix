package com.example.slash.comflix

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v4.app.Fragment
import android.support.v4.app.NavUtils
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.TextView
import android.widget.Toast
import com.example.slash.comflix.fragment.MovieDetailsFragment
import com.example.slash.comflix.fragment.SerieDetailsFragment
import com.example.slash.comflix.fragment.PersonDetailsFragment
import com.example.slash.comflix.fragment.SeasonDetailsFragment
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.slash.comflix.fragment.*
import kotlinx.android.synthetic.main.activity_details.*
import java.util.*

class DetailsActivity : AppCompatActivity(),SerieDetailsFragment.OnFragmentInteractionListener
{

    var fragment: Fragment?=null
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
              // retour_toolbar.title = resources.getStringArray(R.array.movieTitles).get(id)
               fragment = MovieDetailsFragment()
           }
           "serie" -> {
               //retour_toolbar.title = resources.getStringArray(R.array.serieTitles).get(id)
               fragment = SerieDetailsFragment()
           }
           "person" -> {
              // retour_toolbar.title = resources.getStringArray(R.array.personName).get(id)
               fragment = PersonDetailsFragment()
           }
           "cinema" -> {
              // retour_toolbar.title = resources.getStringArray(R.array.movieTitles).get(id)
               fragment = CinemaFragment()
           }
           "cinema_favouris" -> {
              // retour_toolbar.title = "My favourite cinemas"
               fragment = FavouriteCinemaFragment()
           }
           "cinema_details" -> {
              // retour_toolbar.title = resources.getStringArray(R.array.cinemaName).get(id)
               fragment = CinemaDetailsFragment()
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

        val adapter = makeComments()
        btn_comment.setOnClickListener{
            val com = text_comment.text
            val filtredCom = com.replace(" ".toRegex(),"").replace("\n".toRegex(),"")

            if (filtredCom != "")
            {
                adapter.commentsList.add(Comment(com.toString(),getString(R.string.current_user)))
                text_comment.setText("")
                adapter.notifyDataSetChanged()
            }
            else
                Toast.makeText(this,getString(R.string.empty_string),Toast.LENGTH_LONG).show()



        }

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.back_menu, menu)
        return true
    }


    override fun onFragmentInteraction()
    {
        val newFragment:Fragment = SeasonDetailsFragment()
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


    fun makeComments():CommentsAdapter
    {

        val viewManager = LinearLayoutManager(this)
        val commentsList = createCommentsLis()
        val viewAdapter = CommentsAdapter(commentsList)
        comments_list.apply{
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter= viewAdapter
        }

        return viewAdapter

    }

    private fun createCommentsLis(): ArrayList<Comment>
    {
        var commentSize = ArrayList<Comment>()
        val userArray=resources.getStringArray(R.array.users)
        val commentArray = resources.getStringArray(R.array.comments)
        for (i in 0 until userArray.size)
            commentSize.add(Comment(commentArray[i],userArray[i]))

        return commentSize
    }
}


class CommentsAdapter(var commentsList:ArrayList<Comment>):RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val comment: TextView
        val author: TextView

        init {
            comment = itemView.findViewById(R.id.cardauthor)
            author = itemView.findViewById(R.id.cardcomment)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.comment_card, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.author?.text = commentsList[position].author
        holder?.comment?.text = commentsList[position].comment
    }

    override fun getItemCount() = commentsList.size
}
data class Comment(val author:String,val comment:String )
