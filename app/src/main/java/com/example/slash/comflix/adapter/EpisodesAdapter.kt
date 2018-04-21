package com.example.slash.comflix.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.slash.comflix.R
import java.util.ArrayList

/**
 * Created by thinkpad on 21/04/2018.
 */

class EpisodesAdapter(val context:Context, var commentsList: ArrayList<Episode>): RecyclerView.Adapter<EpisodesAdapter.ViewHolder>()
{

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView
        val description: TextView
        val cover:ImageView
        init {
            title = itemView.findViewById(R.id.episode_title)
            description = itemView.findViewById(R.id.episode_description)
            cover = itemView.findViewById(R.id.episode_cover)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.episode_card, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.title?.text = commentsList[position].title
        holder?.cover?.setImageDrawable(context.resources.getDrawable(commentsList[position].coverID))
        holder?.description?.text = commentsList[position].description
    }

    override fun getItemCount() = commentsList.size
}

data class Episode(val coverID:Int, val title:String,val description:String)