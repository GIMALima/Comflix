package com.example.slash.comflix.entities

/**
 * Created by Slash on 01/04/2018.
 */
class TrendingObject {
    var movieTitle: String?=null
    var movieDuration: String?=null
    var movieCover: String?=null

    constructor(movieTitle: String, movieDuration: String,movieCover: String) {
       this.movieTitle=movieTitle
       this.movieDuration=movieDuration
       this.movieCover=movieCover
    }
}