package com.example.slash.comflix.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.slash.comflix.R
import com.example.slash.comflix.entities.Movie


class MovieAdapter :RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    var mcontext: Context
    var movieList: List<Movie>

    constructor(mcontext: Context, movieList: List<Movie>) : super() {
        this.mcontext = mcontext
        this.movieList = movieList
    }


    inner class MyViewHolder : RecyclerView.ViewHolder{
        var title: TextView
        var time: TextView
        var thumbnail: ImageView

        constructor(itemView: View) : super(itemView) {
            this.title= itemView.findViewById<TextView>(R.id.title) as TextView
            this.time= itemView.findViewById<TextView>(R.id.date) as TextView
            this.thumbnail= itemView.findViewById<ImageView>(R.id.movieCover) as ImageView

        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
          var movie=movieList.get(position)
          holder.title.text=movie.title
          holder.time.text=movie.time
          Glide.with(mcontext).load(movie.cover).into(holder.thumbnail)

    }

    override fun getItemCount(): Int {
      return  movieList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        var itemView=LayoutInflater.from(parent!!.context)
                        .inflate(R.layout.movie_card,parent,false)

        return MyViewHolder(itemView)

    }
}
