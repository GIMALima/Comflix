package com.example.slash.comflix.entities

/**
 * Created by thinkpad on 21/04/2018.
 */
data class Comment(val author:String,val content:String )

data class CommentDTO(val id:Int,val total_results:Int,val total_pages:Int,val results:ArrayList<Comment>)