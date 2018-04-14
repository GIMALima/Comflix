package com.example.slash.comflix.entities

/**
 * Created by Slash on 13/04/2018.
 */
class Serie {
    var title:String?=null
    var cover:Int?=null
    var season:Int?=null
    var episode:Int?=null

    constructor(title: String?, cover: Int?, season: Int?, episode: Int?) {
        this.title = title
        this.cover = cover
        this.season = season
        this.episode = episode
    }
}