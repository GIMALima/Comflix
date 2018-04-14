package com.example.slash.comflix.entities

/**
 * Created by Slash on 12/04/2018.
 */
class Movie {
    var title:String?=null
    var cover:Int?=null
    var time:String?=null

    constructor(title: String?, cover: Int?, time: String?) {
        this.title = title
        this.cover = cover
        this.time = time
    }
}