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
import com.example.slash.comflix.entities.Cinema

/**
 * Created by Slash on 19/04/2018.
 */
class FavouriteCinemaAdapter : RecyclerView.Adapter<FavouriteCinemaAdapter.MyViewHolder> {
    var mcontext: Context
    var cinemaList: ArrayList<Cinema>
    var layout: Int

    constructor(mcontext: Context, cinemaList: ArrayList<Cinema>, layout: Int) : super() {
        this.mcontext = mcontext
        this.cinemaList = cinemaList
        this.layout = layout
    }


    inner class MyViewHolder : RecyclerView.ViewHolder {
        var name: TextView
        var location: TextView
        var thumbnail: ImageView
        var cinemaId: TextView
        var delete: ImageView

        constructor(itemView: View) : super(itemView) {
            this.name = itemView.findViewById<TextView>(R.id.cinema) as TextView
            this.location = itemView.findViewById<TextView>(R.id.adresse) as TextView
            this.thumbnail = itemView.findViewById<ImageView>(R.id.cover) as ImageView
            this.cinemaId = itemView.findViewById<TextView>(R.id.cinemaId) as TextView
            this.delete = itemView.findViewById<ImageView>(R.id.delete) as ImageView

        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (!cinemaList.isEmpty()) {
            var movie = cinemaList.get(position)
            holder.name.text = movie.nom
            holder.location.text = movie.location
            holder.cinemaId.text = movie.cinemaId.toString()
            Glide.with(mcontext).load(movie.cover).into(holder.thumbnail)

        }

    }

    override fun getItemCount(): Int {
        return cinemaList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        var itemView = LayoutInflater.from(parent!!.context)
                .inflate(layout, parent, false)
        val holder = MyViewHolder(itemView)
        holder.delete.setOnClickListener {

            val id: Int = holder.cinemaId.text.toString().toInt()
            if (cinemaList.size != 1) {
                cinemaList.removeAt(id)

            } else {
                cinemaList = ArrayList()
            }
            notifyDataSetChanged()
        }
        return MyViewHolder(itemView)

    }
}