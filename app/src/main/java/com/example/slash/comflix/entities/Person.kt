package com.example.slash.comflix.entities


data class Person(val character:String?, val credit_id:String?, val id:Int,
                  val name:String, val profile_path:String, val known_for:ArrayList<Movie>?)
