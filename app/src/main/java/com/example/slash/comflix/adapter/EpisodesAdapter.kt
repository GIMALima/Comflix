package com.example.slash.comflix.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.slash.comflix.R
import com.example.slash.comflix.entities.Episode
import com.squareup.picasso.Picasso
import org.w3c.dom.Text
import java.util.ArrayList

/**
 * Created by thinkpad on 21/04/2018.
 */

class EpisodesAdapter(val context:Context, var EpisodesList: ArrayList<Episode>): RecyclerView.Adapter<EpisodesAdapter.ViewHolder>()
{

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.episode_title)
        val description: TextView = itemView.findViewById(R.id.episode_description)
        val cover:ImageView = itemView.findViewById(R.id.episode_cover)
        val average:TextView = itemView.findViewById(R.id.average)
        val votes:TextView = itemView.findViewById(R.id.votes)
    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.episode_card, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int)
    {
        val episode =  EpisodesList[position]
        holder?.title?.text = episode.episode_number.toString() +". "+ episode.name
        Picasso.with(context).load(context.getString(R.string.image_url)+episode.still_path).into(holder?.cover)
        //holder?.cover?.setImageDrawable(context.resources.getDrawable(EpisodesList[position].coverID))
        holder?.description?.text = episode.overview
        holder?.votes?.text=episode.vote_count.toString()+" votes"
        holder?.average?.text=episode.vote_average.toString()+"/10"


    }

    override fun getItemCount() = EpisodesList.size
}

//data class Episode(val coverID:Int, val title:String,val description:String)