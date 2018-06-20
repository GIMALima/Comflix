package com.example.slash.comflix.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.slash.comflix.DetailsActivity
import com.example.slash.comflix.R
import com.example.slash.comflix.entities.Movie
import com.squareup.picasso.Picasso


class MovieAdapter :RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    var mcontext: Context
    var movieList: ArrayList<Movie>
    var layout:Int

    constructor(mcontext: Context, movieList: ArrayList<Movie>,layout:Int) : super() {
        this.mcontext = mcontext
        this.movieList = movieList
        this.layout=layout

    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var title: TextView
        var cinema: TextView
        var thumbnail: ImageView
        var card: CardView
        var movieId:TextView

        init {

            this.title= itemView.findViewById<TextView>(R.id.title) as TextView
            this.cinema= itemView.findViewById<TextView>(R.id.cinema) as TextView
            this.thumbnail= itemView.findViewById<ImageView>(R.id.movieCover) as ImageView
            this.card=itemView.findViewById<CardView>(R.id.card_view) as CardView
            this.movieId=itemView.findViewById<TextView>(R.id.movieId) as TextView
        }
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
         var movie=movieList.get(position)
          holder.title.text=movie.title
          holder.cinema.text=movie.release_date
          holder.movieId.text=movie.id.toString()
          Picasso.with(mcontext).load(mcontext.getString(R.string.image_url)+movie.poster_path).into(holder.thumbnail)
    }

    override fun getItemCount(): Int {

        return  movieList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        var itemView=LayoutInflater.from(parent!!.context)
                        .inflate(layout,parent,false)
     val holder = MyViewHolder(itemView)
        holder.apply {
            card.setOnClickListener{openDetailsActivity(this)}
            title.setOnClickListener{openDetailsActivity(this)}
            thumbnail.setOnClickListener{openDetailsActivity(this)}
            cinema.setOnClickListener { openDetailsActivity(this) }

        }

        return MyViewHolder(itemView)

    }

    fun openDetailsActivity(holder: MyViewHolder)
    {
        val intent = Intent(mcontext, DetailsActivity::class.java)
        val bundle = Bundle()
        bundle.putInt("id", holder.movieId.text.toString().toInt()) //Your id
        bundle.putString("type","movie")
        intent.putExtras(bundle) //P

        mcontext.startActivity(intent)
    }
   fun updateListMovie(listMovie:ArrayList<Movie>){
       this.movieList=listMovie
       this.notifyDataSetChanged()
   }
}

