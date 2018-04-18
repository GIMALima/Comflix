package com.example.slash.comflix.entities

/**
 * Created by Slash on 13/04/2018.
 */
class Serie {
    var title:String?=null
    var cover:Int?=null
    var season:Int?=null
    var episode:Int?=null
    var serieId:Int?=null
    var genre:String?=null

    constructor(title: String?, cover: Int?, season: Int?, episode: Int?,serieId:Int?,genre:String?) {
        this.title = title
        this.cover = cover
        this.season = season
        this.episode = episode
        this.serieId=serieId
        this.genre=genre
    }
}