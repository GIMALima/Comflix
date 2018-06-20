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
import com.example.slash.comflix.entities.Movie
import com.example.slash.comflix.entities.Serie
import com.squareup.picasso.Picasso

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
        var date: TextView
        var cover: ImageView
        var card: CardView
        var serieId: TextView

        constructor(itemView: View) : super(itemView) {
            this.card = itemView.findViewById(R.id.card_view)
            this.title= itemView.findViewById<TextView>(R.id.title) as TextView
            this.date= itemView.findViewById<TextView>(R.id.date) as TextView
            this.cover= itemView.findViewById<ImageView>(R.id.cover) as ImageView
            this.serieId = itemView.findViewById<TextView>(R.id.id) as TextView
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val serie=serieList.get(position)
        holder.title.text=serie.name
        holder.date.text=serie.first_air_date
        holder.serieId.text = serie.id.toString()
        Picasso.with(mcontext).load(mcontext.getString(R.string.image_url)+serie.poster_path).into(holder.cover)

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
            date.setOnClickListener { openDetailsActivity(this)
            }

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
    fun updateListSerie(listSerie:ArrayList<Serie>){
        this.serieList=listSerie
        this.notifyDataSetChanged()
    }
}

