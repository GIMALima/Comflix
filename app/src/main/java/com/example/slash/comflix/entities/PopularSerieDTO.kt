package com.example.slash.comflix.entities

data class PopularSerieDTO(val page:Int,val total_results:Int, val total_pages:Int, val results:ArrayList<Serie>)