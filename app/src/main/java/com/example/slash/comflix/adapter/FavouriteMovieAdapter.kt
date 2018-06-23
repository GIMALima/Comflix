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
import com.example.slash.comflix.DetailsActivity
import com.example.slash.comflix.R
import com.example.slash.comflix.room.MovieEntity
import com.squareup.picasso.Picasso


class FavouriteMovieAdapter : RecyclerView.Adapter<FavouriteMovieAdapter.MyViewHolder> {
    var mcontext: Context
    var movieList:List<MovieEntity>
    var layout: Int

    constructor(mcontext: Context, movieList: List<MovieEntity>, layout: Int) : super() {
        this.mcontext = mcontext
        this.movieList = movieList
        this.layout = layout
    }


    inner class MyViewHolder : RecyclerView.ViewHolder {
        var title: TextView
        var date: TextView
        var thumbnail: ImageView
        var card: CardView
        var movieId:TextView
        var pos:TextView

        constructor(itemView: View) : super(itemView) {
                this.title= itemView.findViewById<TextView>(R.id.title) as TextView
                this.date= itemView.findViewById<TextView>(R.id.date) as TextView
                this.thumbnail= itemView.findViewById<ImageView>(R.id.cover) as ImageView
                this.card=itemView.findViewById<CardView>(R.id.card_view) as CardView
                this.movieId=itemView.findViewById<TextView>(R.id.id) as TextView
                this.pos=itemView.findViewById<TextView>(R.id.pos) as TextView

        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

            var movie=movieList.get(position)
            holder.title.text=movie.title
            holder.date.text=movie.release_date
            holder.movieId.text=movie.id
            holder.pos.text=position.toString()
            Picasso.with(mcontext).load(mcontext.getString(R.string.image_url)+movie.poster_path).into(holder.thumbnail)



    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        var itemView = LayoutInflater.from(parent!!.context)
                .inflate(layout, parent, false)
        val holder = MyViewHolder(itemView)
        holder.apply {
            card.setOnClickListener{openDetailsActivity(this)}
            title.setOnClickListener{openDetailsActivity(this)}
            thumbnail.setOnClickListener{openDetailsActivity(this)}
            date.setOnClickListener { openDetailsActivity(this) }
        }

        return MyViewHolder(itemView)

    }
    fun openDetailsActivity(holder: FavouriteMovieAdapter.MyViewHolder)
    {
        val intent = Intent(mcontext, DetailsActivity::class.java)
        val bundle = Bundle()
        bundle.putInt("id", holder.movieId.text.toString().toInt())
        bundle.putString("type","movie")
        bundle.putInt("from",1)
        bundle.putInt("position",holder.pos.text.toString().toInt())
        intent.putExtras(bundle)
        mcontext.startActivity(intent)
    }
    fun updateListFavouriteMovie(listMovie:List<MovieEntity>){
        this.movieList=listMovie
        this.notifyDataSetChanged()
    }
}