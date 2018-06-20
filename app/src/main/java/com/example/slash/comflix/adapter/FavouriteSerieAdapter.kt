package com.example.slash.comflix.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.slash.comflix.R
import com.example.slash.comflix.entities.Serie

/**
 * Created by Slash on 18/04/2018.
 */
class FavouriteSerieAdapter : RecyclerView.Adapter<FavouriteSerieAdapter.MyViewHolder> {
    var mcontext: Context
    var serieList: ArrayList<Serie>
    var layout: Int

    constructor(mcontext: Context, movieList: ArrayList<Serie>, layout: Int) : super() {
        this.mcontext = mcontext
        this.serieList = movieList
        this.layout = layout
    }

    inner class MyViewHolder : RecyclerView.ViewHolder {
        var title: TextView
        var genre: TextView
        var season: TextView
        var episode: TextView
        var thumbnail: ImageView
        var serieId: TextView
        var delete: ImageView

        constructor(itemView: View) : super(itemView) {
            this.title = itemView.findViewById<TextView>(R.id.title) as TextView
            this.genre = itemView.findViewById<TextView>(R.id.genre) as TextView
            this.thumbnail = itemView.findViewById<ImageView>(R.id.cover) as ImageView
            this.serieId = itemView.findViewById<TextView>(R.id.serieId) as TextView
            this.delete = itemView.findViewById<ImageView>(R.id.delete) as ImageView
            this.episode = itemView.findViewById<TextView>(R.id.episode) as TextView
            this.season = itemView.findViewById<TextView>(R.id.season) as TextView

        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (!serieList.isEmpty()) {
          /*  var serie = serieList.get(position)
            holder.title.text = serie.title
            holder.genre.text = serie.genre
            holder.season.text = "Season" + serie.season
            holder.episode.text = "Episode" + serie.episode
            holder.serieId.text = serie.serieId.toString()
            Glide.with(mcontext).load(serie.cover).into(holder.thumbnail)*/

        }

    }

    override fun getItemCount(): Int {
        return serieList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        var itemView = LayoutInflater.from(parent!!.context)
                .inflate(layout, parent, false)
        val holder = MyViewHolder(itemView)
        holder.delete.setOnClickListener {

            val id: Int = holder.serieId.text.toString().toInt()
            if (serieList.size != 1) {
                serieList.removeAt(id)

            } else {
                serieList = ArrayList()
            }
            notifyDataSetChanged()
        }
        return MyViewHolder(itemView)

    }
}