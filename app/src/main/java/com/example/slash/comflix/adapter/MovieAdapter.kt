package com.example.slash.comflix.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.slash.comflix.DetailsActivity
import com.example.slash.comflix.R
import com.example.slash.comflix.entities.Movie
import android.os.Bundle
import android.widget.AdapterView
import android.widget.LinearLayout


class MovieAdapter :RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    var mcontext: Context
    var movieList: List<Movie>
    var layout:Int

    constructor(mcontext: Context, movieList: List<Movie>,layout:Int) : super() {
        this.mcontext = mcontext
        this.movieList = movieList
        this.layout=layout
    }


    inner class MyViewHolder : RecyclerView.ViewHolder,AdapterView.OnItemClickListener{
        override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

        }

        var title: TextView
        var cinema: TextView
        var thumbnail: ImageView
        var card: CardView
        var movieId:TextView


        constructor(itemView: View) : super(itemView) {
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
          holder.cinema.text=movie.cinema
          holder.movieId.text=movie.movieId.toString()
          Glide.with(mcontext).load(movie.cover).into(holder.thumbnail)


    }

    override fun getItemCount(): Int {
      return  movieList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        var itemView=LayoutInflater.from(parent!!.context)
                        .inflate(layout,parent,false)
     /*   val holder = MyViewHolder(itemView)
        holder.card.setOnClickListener{

            val intent = Intent(mcontext, DetailsActivity::class.java)
            val bundle = Bundle()
            bundle.putInt("id", holder.movieId.text.toString().toInt()) //Your id
            bundle.putString("type","movie")
            intent.putExtras(bundle) //P

            mcontext.startActivity(intent)

        }
        holder.title.setOnClickListener{
            val intent = Intent(mcontext, DetailsActivity::class.java)
            intent.putExtra("type","movie")
            intent.putExtra("id",holder.movieId.text.toString().toInt())
            mcontext.startActivity(intent)
        }
        holder.thumbnail.setOnClickListener {
            val intent = Intent(mcontext, DetailsActivity::class.java)
            intent.putExtra("type","movie")
            intent.putExtra("type",holder.movieId.text.toString().toInt())
            mcontext.startActivity(intent)
        }
        holder.title.setOnClickListener {
            val intent = Intent(mcontext, DetailsActivity::class.java)
            intent.putExtra("type","movie")
            intent.putExtra("id",holder.movieId.text.toString().toInt())
            mcontext.startActivity(intent)
        }
        holder.cinema.setOnClickListener {
            val intent = Intent(mcontext, DetailsActivity::class.java)
            intent.putExtra("type","movie")
            intent.putExtra("id",holder.movieId.text.toString().toInt())
            mcontext.startActivity(intent)
        }
*/
        return MyViewHolder(itemView)

    }

}

