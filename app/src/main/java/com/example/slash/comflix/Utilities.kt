package com.example.slash.comflix

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.slash.comflix.adapter.*
import com.example.slash.comflix.entities.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

var num_page_movies=1
var num_page_latest_movies=1
var num_page_series=1
fun calculateCardNum(context: Context):Int{
    val n=context.resources.displayMetrics
    val num=n.widthPixels/n.density
    return (num/160).toInt()

}

fun prepareMovies(context:Context,movieList:ArrayList<Movie>, movieAdapter: MovieAdapter){
    var covers= intArrayOf(
            R.drawable.divergent,
            R.drawable.hungergamescatchingfire,
            R.drawable.mazerunner,
            R.drawable.pirateofthecar,
            R.drawable.themazerunnerdeathcure,
            R.drawable.themazerunnerscorch
    )
    var movieTitles=context.resources.getStringArray(R.array.movieTitles)
    var movieCinema= context.resources.getStringArray(R.array.movieCinema)
    var movieTime=context.resources.getStringArray(R.array.movieTime)
    for (i in 0 until covers.size){
     //   var movie= Movie(movieTitles.get(i),covers.get(i),movieCinema.get(i),movieTime.get(i),i)
       // movieList.add(movie)
    }
    movieAdapter.notifyDataSetChanged()
}
fun prepareFavouriteMovies(context:Context,movieList:ArrayList<Movie>, movieAdapter: FavouriteMovieAdapter){
    var covers= intArrayOf(
            R.drawable.divergent,
            R.drawable.hungergamescatchingfire,
            R.drawable.mazerunner,
            R.drawable.pirateofthecar,
            R.drawable.themazerunnerdeathcure,
            R.drawable.themazerunnerscorch
    )
    var movieTitles=context.resources.getStringArray(R.array.movieTitles)
    var movieCinema= context.resources.getStringArray(R.array.movieCinema)
    var movieTime=context.resources.getStringArray(R.array.movieTime)
    for (i in 0 until covers.size){
     //   var movie= Movie(movieTitles.get(i),covers.get(i),movieCinema.get(i),movieTime.get(i),i)
       // movieList.add(movie)
    }
    movieAdapter.notifyDataSetChanged()
}
fun prepareFavouriteSeries(context:Context,serieList:ArrayList<Serie>, serieAdapter: FavouriteSerieAdapter){
    var covers= intArrayOf(

            R.drawable.houseofcards,
            R.drawable.gameofthrones,
            R.drawable.friends,
            R.drawable.suits,
            R.drawable.vikings,
            R.drawable.breakingbad,
            R.drawable.lacasadepapel,
            R.drawable.prisonbreak
    )
    var serieTitles=context.resources.getStringArray(R.array.serieTitles)
    var serieSeasons=context.resources.getIntArray(R.array.serieSeasons)
    var serieEpisodes=context.resources.getIntArray(R.array.serieEpisodes)
    var serieGenre=context.resources.getStringArray(R.array.serieGenre)
/*
    for (i in 0 until covers.size){
      //  var serie= Serie(serieTitles.get(i),covers.get(i),serieSeasons.get(i),serieEpisodes.get(i),i,serieGenre.get(i))
        //serieList.add(serie)
    }
    serieAdapter.notifyDataSetChanged()*/
}

fun prepareSeries(context:Context,serieList:ArrayList<Serie>, serieAdapter: SerieAdapter){
    var covers= intArrayOf(

            R.drawable.houseofcards,
            R.drawable.gameofthrones,
            R.drawable.friends,
            R.drawable.suits,
            R.drawable.vikings,
            R.drawable.breakingbad,
            R.drawable.lacasadepapel,
            R.drawable.prisonbreak
    )
    var serieTitles=context.resources.getStringArray(R.array.serieTitles)
    var serieSeasons=context.resources.getIntArray(R.array.serieSeasons)
    var serieEpisodes=context.resources.getIntArray(R.array.serieEpisodes)
    var serieGenre=context.resources.getStringArray(R.array.serieGenre)
/*
    for (i in 0 until covers.size){

        var serie= Serie(serieTitles.get(i),covers.get(i),serieSeasons.get(i),serieEpisodes.get(i),i,serieGenre.get(i))
        serieList.add(serie)
    }*/


    serieAdapter.notifyDataSetChanged()
}

fun prepareFavouriteCinema(context: Context, cinemaFavourite:ArrayList<Cinema>,cinemaAdapter:FavouriteCinemaAdapter){
    var covers= intArrayOf(

            R.drawable.ibnkhaldoun,
            R.drawable.atlas,
            R.drawable.mouggar,
            R.drawable.chabab,
            R.drawable.ibnkhaldoun,
            R.drawable.ibnkhaldoun
    )
    var cinemaName=context.resources.getStringArray(R.array.cinemaName)
    var cinemaLocation=context.resources.getStringArray(R.array.cinemaLocation)
    var cinemaSalle=context.resources.getIntArray(R.array.nb_salleCinema)


    for (i in 0 until covers.size){
        var cinema= Cinema(cinemaName.get(i),covers.get(i),cinemaLocation.get(i),cinemaSalle.get(i),i)
        cinemaFavourite.add(cinema)
    }
    cinemaAdapter.notifyDataSetChanged()
}
fun prepareFavouriteCinema(context: Context, cinemaFavourite:ArrayList<Cinema>,cinemaAdapter:CinemaAdapter){
    var covers= intArrayOf(

            R.drawable.ibnkhaldoun,
            R.drawable.atlas,
            R.drawable.mouggar,
            R.drawable.chabab,
            R.drawable.ibnkhaldoun,
            R.drawable.ibnkhaldoun,
            R.drawable.ibnkhaldoun,
            R.drawable.ibnkhaldoun,
            R.drawable.atlas,
            R.drawable.mouggar
    )
    var cinemaName=context.resources.getStringArray(R.array.cinemaName)
    var cinemaLocation=context.resources.getStringArray(R.array.cinemaLocation)
    var cinemaSalle=context.resources.getIntArray(R.array.nb_salleCinema)


    for (i in 0 until covers.size){
        var cinema= Cinema(cinemaName.get(i),covers.get(i),cinemaLocation.get(i),cinemaSalle.get(i),i)
        cinemaFavourite.add(cinema)
    }
    cinemaAdapter.notifyDataSetChanged()
}
fun preparePersons(context:Context, personList:ArrayList<Person>, personAdapter: PersonAdapter){
    var covers= intArrayOf(
            R.drawable.dyl,
            R.drawable.dyl,
            R.drawable.dyl,
            R.drawable.dyl,
            R.drawable.dyl,
            R.drawable.dyl,
            R.drawable.dyl,
            R.drawable.dyl,
            R.drawable.dyl,
            R.drawable.dyl
    )
    var personTitles=context.resources.getStringArray(R.array.personTitles)
    var personNames= context.resources.getStringArray(R.array.personName)
    var personDateOfBirth=context.resources.getStringArray(R.array.personDateOfBirth)
    var personBiographie=context.resources.getStringArray(R.array.personBiographie)
    var personFilmographie=context.resources.getStringArray(R.array.personFilmographie)
    for (i in 0 until covers.size){
        var person= Person(personTitles.get(i),personNames.get(i),covers.get(i),personDateOfBirth.get(i),personBiographie.get(i),personFilmographie.get(i),i)
        personList.add(person)
    }
    personAdapter.notifyDataSetChanged()
}

fun getListMoviesNowPlaying(movieAdapter: MovieAdapter,movieList: ArrayList<Movie>){
    RetrofitBuilder.movieApi.getMoviesNowPalying(num_page_movies)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                    { result ->
                       movieList.addAll(result.results)
                       movieAdapter.updateListMovie(movieList)
                    },
                    { error -> Log.e("ERROR", error.message) }
            )
    num_page_movies++
}
fun getListMoviesLatest(movieAdapter: MovieAdapter,movieList: ArrayList<Movie>){
    RetrofitBuilder.movieApi.getAllLastestMovies(num_page_latest_movies)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                    { result ->
                        movieList.addAll(result.results)
                        movieAdapter.updateListMovie(movieList)
                    },
                    { error -> Log.e("ERROR", error.message) }
            )
    num_page_latest_movies++
}
fun getListSeries(serieAdapter: SerieAdapter,serieList: ArrayList<Serie>){

    RetrofitBuilder.serieApi.getSeriesNowPalying(num_page_series)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                    { result ->
                        serieList.addAll(result.results)
                        serieAdapter.updateListSerie(serieList)
                    },
                    { error -> Log.e("ERROR", error.message) }
            )
    num_page_movies++
}
fun chargerScoll(recyclerView: RecyclerView, layoutManager: GridLayoutManager,loading:()->Unit):Boolean{
    var charger=false
    recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        var pastVisiblesItems: Int = 0
        var visibleItemCount: Int = 0
        var totalItemCount: Int = 0

        override fun onScrolled(recyclerView: RecyclerView?,
                                dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            visibleItemCount = layoutManager.childCount
            totalItemCount = layoutManager.itemCount
            pastVisiblesItems = layoutManager.findFirstVisibleItemPosition()

            if (visibleItemCount + pastVisiblesItems >= totalItemCount) charger=true

            if(charger)
                loading()
        }
    })
    return  charger

}


fun chargerScoll(recyclerView: RecyclerView, layoutManager: GridLayoutManager):Boolean{
    var charger=false
    recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        var pastVisiblesItems: Int = 0
        var visibleItemCount: Int = 0
        var totalItemCount: Int = 0
        val visibleThreshold = 5

        override fun onScrolled(recyclerView: RecyclerView?,
                                dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            visibleItemCount = layoutManager.childCount
            totalItemCount = layoutManager.itemCount
            pastVisiblesItems = layoutManager.findFirstVisibleItemPosition()

            if (visibleItemCount + pastVisiblesItems + visibleThreshold >= totalItemCount) charger=true


        }
    })
    return  charger

}
