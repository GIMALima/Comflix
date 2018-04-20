package com.example.slash.comflix.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.slash.comflix.DetailsActivity
import com.example.slash.comflix.R
import com.example.slash.comflix.entities.Serie

/**
 * Created by Slash on 13/04/2018.
 */
class SerieAdapter : RecyclerView.Adapter<SerieAdapter.MyViewHolder> {
    var mcontext: Context
    var serieList: List<Serie>
    var layout:Int

    constructor(mcontext: Context, serieList: List<Serie>,layout:Int) : super() {
        this.mcontext = mcontext
        this.serieList = serieList
        this.layout=layout
    }


    inner class MyViewHolder : RecyclerView.ViewHolder{
        var title: TextView
        var season: TextView
        var episode: TextView
        var cover: ImageView
        var card: CardView
        var serieId: TextView

        constructor(itemView: View) : super(itemView) {
            this.card = itemView.findViewById(R.id.card_view)
            this.title= itemView.findViewById<TextView>(R.id.title) as TextView
            this.season= itemView.findViewById<TextView>(R.id.season) as TextView
            this.episode= itemView.findViewById<TextView>(R.id.episode) as TextView
            this.cover= itemView.findViewById<ImageView>(R.id.serieCover) as ImageView
            this.serieId = itemView.findViewById(R.id.serieId)
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val serie=serieList.get(position)
        holder.title.text=serie.title
        holder.season.text="Season"+" "+serie.season.toString()
        holder.episode.text="Episode"+" "+serie.episode.toString()
        holder.serieId.text = serie.serieId.toString()
        Log.d("SerieAdapter",serie.serieId.toString())
        Glide.with(mcontext).load(serie.cover).into(holder.cover)

    }

    override fun getItemCount(): Int {
        return  serieList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        var itemView= LayoutInflater.from(parent!!.context)
                .inflate(layout,parent,false)

        var holder = MyViewHolder(itemView)
        holder.apply {
            card.setOnClickListener{openDetailsActivity(this)}
            title.setOnClickListener{openDetailsActivity(this)}
            cover.setOnClickListener{openDetailsActivity(this)}
            episode.setOnClickListener { openDetailsActivity(this)
            season.setOnClickListener { openDetailsActivity(this)}}

        }
        return MyViewHolder(itemView)
    }
    fun openDetailsActivity(holder: SerieAdapter.MyViewHolder)
    {
        val intent = Intent(mcontext, DetailsActivity::class.java)
        val bundle = Bundle()
        bundle.putInt("id", holder.serieId.text.toString().toInt()) //Your id
        bundle.putString("type","serie")
        intent.putExtras(bundle)

        mcontext.startActivity(intent)
    }

}

