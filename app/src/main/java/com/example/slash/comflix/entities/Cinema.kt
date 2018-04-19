package com.example.slash.comflix.entities

/**
 * Created by Slash on 19/04/2018.
 */
class Cinema {
    var nom:String?=null
    var cover:Int?=null
    var location:String?=null
    var nbSalle:Int?=null
    var cinemaId:Int?=null

    constructor(nom: String?, cover: Int?,location:String?,nbSalle:Int?,cinemaId:Int?) {
        this.nom = nom
        this.cover = cover
        this.location= location
        this.nbSalle=nbSalle
        this.cinemaId=cinemaId
    }
}