package com.example.slash.comflix.entities

/**
 * Created by Slash on 12/04/2018.
 */
class Movie {
    var title:String?=null
    var cover:Int?=null
    var time:String?=null
    var cinema:String?=null
    var movieId:Int?=null

    constructor(title: String?, cover: Int?,cinema:String?,time:String?,movieId:Int?) {
        this.title = title
        this.cover = cover
        this.cinema= cinema
        this.time=time
        this.movieId=movieId
    }
}