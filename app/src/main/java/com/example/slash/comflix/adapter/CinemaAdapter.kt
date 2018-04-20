package com.example.slash.comflix.adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.slash.comflix.R
import com.example.slash.comflix.entities.Cinema

/**
 * Created by Slash on 20/04/2018.
 */
class CinemaAdapter : RecyclerView.Adapter<CinemaAdapter.MyViewHolder> {
    var mcontext: Context
    var cinemaList: List<Cinema>
    var layout:Int

    constructor(mcontext: Context, cinemaList: List<Cinema>, layout:Int) : super() {
        this.mcontext = mcontext
        this.cinemaList = cinemaList
        this.layout=layout
    }


    inner class MyViewHolder : RecyclerView.ViewHolder{

        var cinema: TextView
        var card: CardView
        var cinemaId: TextView
        var salle:TextView
        var favourite:ImageView


        constructor(itemView: View) : super(itemView) {
            this.cinema= itemView.findViewById<TextView>(R.id.cinema) as TextView
            this.favourite= itemView.findViewById<ImageView>(R.id.cinema_favouris) as ImageView
            this.card=itemView.findViewById<CardView>(R.id.card_view) as CardView
            this.cinemaId=itemView.findViewById<TextView>(R.id.cinemaId) as TextView
            this.salle=itemView.findViewById<TextView>(R.id.salle) as TextView


        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var cinema=cinemaList.get(position)
        holder.cinema.text=cinema.nom
        holder.cinemaId.text=cinema.cinemaId.toString()
        holder.salle.text=cinema.nbSalle.toString()


    }

    override fun getItemCount(): Int {
        return  cinemaList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        var itemView= LayoutInflater.from(parent!!.context)
                .inflate(layout,parent,false)


       val holder = MyViewHolder(itemView)
        var swith=true
        holder.favourite.setOnClickListener{

         if(swith) {
             swith=false
             holder.favourite.setImageDrawable(parent!!.context.resources.getDrawable(R.drawable.ic_favorite_black))
         }else{
             swith=true
             holder.favourite.setImageDrawable(parent!!.context.resources.getDrawable(R.drawable.ic_favorite_border_24dp))

         }


        }
/*
        holder.thumbnail.setOnClickListener {
            val intent = Intent(mcontext, DetailsActivity::class.java)
            val bundle = Bundle()
            bundle.putInt("id", holder.movieId.text.toString().toInt()) //Your id
            bundle.putString("type","movie")
            intent.putExtras(bundle) //P

            mcontext.startActivity(intent)
        }
        holder.title.setOnClickListener {
            val intent = Intent(mcontext, DetailsActivity::class.java)
            val bundle = Bundle()
            bundle.putInt("id", holder.movieId.text.toString().toInt()) //Your id
            bundle.putString("type","movie")
            intent.putExtras(bundle) //P

            mcontext.startActivity(intent)
        }

        holder.cinema.setOnClickListener {
            val intent = Intent(mcontext, DetailsActivity::class.java)
            val bundle = Bundle()
            bundle.putInt("id", holder.movieId.text.toString().toInt()) //Your id
            bundle.putString("type","movie")
            intent.putExtras(bundle) //P

            mcontext.startActivity(intent)
        }*/

        return MyViewHolder(itemView)

    }

}

