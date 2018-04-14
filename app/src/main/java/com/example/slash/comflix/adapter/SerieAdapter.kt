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
 * Created by Slash on 13/04/2018.
 */
class SerieAdapter : RecyclerView.Adapter<SerieAdapter.MyViewHolder> {
    var mcontext: Context
    var serieList: List<Serie>

    constructor(mcontext: Context, serieList: List<Serie>) : super() {
        this.mcontext = mcontext
        this.serieList = serieList
    }


    inner class MyViewHolder : RecyclerView.ViewHolder{
        var title: TextView
        var season: TextView
        var episode: TextView
        var cover: ImageView

        constructor(itemView: View) : super(itemView) {
            this.title= itemView.findViewById<TextView>(R.id.title) as TextView
            this.season= itemView.findViewById<TextView>(R.id.season) as TextView
            this.episode= itemView.findViewById<TextView>(R.id.episode) as TextView
            this.cover= itemView.findViewById<ImageView>(R.id.serieCover) as ImageView

        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var serie=serieList.get(position)
        holder.title.text=serie.title
        holder.season.text=serie.season.toString()
        holder.episode.text=serie.episode.toString()
        Glide.with(mcontext).load(serie.cover).into(holder.cover)

    }

    override fun getItemCount(): Int {
        return  serieList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        var itemView= LayoutInflater.from(parent!!.context)
                .inflate(R.layout.serie_card,parent,false)

        return MyViewHolder(itemView)

    }
}

