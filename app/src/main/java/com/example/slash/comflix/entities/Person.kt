package com.example.slash.comflix.entities

/**
 * Created by Slash on 20/04/2018.
 */
class Person {
    var title:String?=null
    var name:String?=null
    var image:Int?=null
    var dateOfBirht:String?=null
    var personBiographie:String?=null
    var personFilmographie:String?=null
    var personId:Int?=null

    constructor(title: String?, name: String?, image: Int?, dateOfBirht: String?, personBiographie: String?, personFilmographie: String?, personId: Int?) {
        this.title = title
        this.name = name
        this.image = image
        this.dateOfBirht = dateOfBirht
        this.personBiographie = personBiographie
        this.personFilmographie = personFilmographie
        this.personId = personId
    }
}