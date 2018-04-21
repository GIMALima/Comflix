package com.example.slash.comflix.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.slash.comflix.R
import com.example.slash.comflix.entities.Comment
import java.util.ArrayList

/**
 * Created by thinkpad on 21/04/2018.
 */

class CommentsAdapter(var commentsList: ArrayList<Comment>): RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val comment: TextView
        val author: TextView

        init {
            comment = itemView.findViewById(R.id.cardauthor)
            author = itemView.findViewById(R.id.cardcomment)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.comment_card, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.author?.text = commentsList[position].author
        holder?.comment?.text = commentsList[position].comment
    }

    override fun getItemCount() = commentsList.size
}