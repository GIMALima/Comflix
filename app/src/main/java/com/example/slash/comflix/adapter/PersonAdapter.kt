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
import com.bumptech.glide.Glide
import com.example.slash.comflix.DetailsActivity
import com.example.slash.comflix.R
import com.example.slash.comflix.entities.Person

/**
 * Created by Slash on 20/04/2018.
 */
class PersonAdapter : RecyclerView.Adapter<PersonAdapter.MyViewHolder> {
    var mcontext: Context
    var personList: List<Person>
    var layout:Int

    constructor(mcontext: Context, personList: List<Person>, layout:Int) : super() {
        this.mcontext = mcontext
        this.personList = personList
        this.layout=layout
    }


    inner class MyViewHolder : RecyclerView.ViewHolder{

        var title: TextView
        var name: TextView
        var thumbnail: ImageView
        var personId:TextView
        var card: CardView


        constructor(itemView: View) : super(itemView) {
            this.title= itemView.findViewById<TextView>(R.id.title) as TextView
            this.name= itemView.findViewById<TextView>(R.id.name) as TextView
            this.thumbnail= itemView.findViewById<ImageView>(R.id.personPicture) as ImageView
            this.card=itemView.findViewById<CardView>(R.id.card_view) as CardView
            this.personId=itemView.findViewById<TextView>(R.id.personId) as TextView
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var person=personList.get(position)
        holder.title.text=person.title
        holder.name.text=person.name
        holder.personId.text=person.personId.toString()
        Glide.with(mcontext).load(person.image).into(holder.thumbnail)


    }

    override fun getItemCount(): Int {
        return  personList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        var itemView= LayoutInflater.from(parent!!.context)
                .inflate(layout,parent,false)
        val holder = MyViewHolder(itemView)
        holder.card.setOnClickListener{

            val intent = Intent(mcontext, DetailsActivity::class.java)
            val bundle = Bundle()
            bundle.putInt("id", holder.personId.text.toString().toInt()) //Your id
            bundle.putString("type","person")
            intent.putExtras(bundle) //P
            mcontext.startActivity(intent)

        }

        holder.thumbnail.setOnClickListener {
            val intent = Intent(mcontext, DetailsActivity::class.java)
            val bundle = Bundle()
            bundle.putInt("id", holder.personId.text.toString().toInt()) //Your id
            bundle.putString("type","person")
            intent.putExtras(bundle) //P

            mcontext.startActivity(intent)
        }
        holder.title.setOnClickListener {
            val intent = Intent(mcontext, DetailsActivity::class.java)
            val bundle = Bundle()
            bundle.putInt("id", holder.personId.text.toString().toInt()) //Your id
            bundle.putString("type","person")
            intent.putExtras(bundle) //P

            mcontext.startActivity(intent)
        }

        holder.name.setOnClickListener {
            val intent = Intent(mcontext, DetailsActivity::class.java)
            val bundle = Bundle()
            bundle.putInt("id", holder.personId.text.toString().toInt()) //Your id
            bundle.putString("type","person")
            intent.putExtras(bundle) //P

            mcontext.startActivity(intent)
        }


        return MyViewHolder(itemView)

    }

}

