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
import com.example.slash.comflix.entities.Person
import com.squareup.picasso.Picasso


class PersonAdapter : RecyclerView.Adapter<PersonAdapter.MyViewHolder> {
    var mcontext: Context
    var personList: ArrayList<Person>
    var layout:Int

    constructor(mcontext: Context, personList: ArrayList<Person>, layout:Int) : super() {
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
        holder.title.text=person.character
        holder.name.text=person.name+ " staring as"
        holder.personId.text=person.id.toString()
        Picasso.with(mcontext).load(mcontext.getString(R.string.image_url)+person.profile_path).into(holder.thumbnail)

    }

    override fun getItemCount(): Int {
        return  personList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        var itemView= LayoutInflater.from(parent!!.context)
                .inflate(layout,parent,false)
        val holder = MyViewHolder(itemView)
        holder.apply {
            card.setOnClickListener{openDetailsActivity(this)}
            title.setOnClickListener{openDetailsActivity(this)}
            thumbnail.setOnClickListener{openDetailsActivity(this)}
            name.setOnClickListener { openDetailsActivity(this) }

        }


        return MyViewHolder(itemView)

    }
    fun openDetailsActivity(holder: PersonAdapter.MyViewHolder)
    {
        val intent = Intent(mcontext, DetailsActivity::class.java)
        val bundle = Bundle()
        bundle.putInt("id", holder.personId.text.toString().toInt())
        bundle.putString("type","person")
        intent.putExtras(bundle) //P
        mcontext.startActivity(intent)
    }
    fun updateListPerson(listPerson:ArrayList<Person>){
        this.personList=listPerson
        this.notifyDataSetChanged()
    }

}

