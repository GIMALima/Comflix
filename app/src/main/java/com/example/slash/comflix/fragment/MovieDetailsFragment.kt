package com.example.slash.comflix.fragment

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.slash.comflix.R
import com.example.slash.comflix.adapter.MovieAdapter
import com.example.slash.comflix.adapter.PersonAdapter
import com.example.slash.comflix.entities.*
import com.example.slash.comflix.getCastCrew
import com.example.slash.comflix.getMovieDetails
import com.example.slash.comflix.getSimilarMovies
import com.example.slash.comflix.room.MovieEntity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_movie_details.*
import java.io.FileInputStream
import java.io.FileOutputStream
import java.net.URL


class MovieDetailsFragment : Fragment() {


    var movieId=0
    var favoris=false
    var moviefavoris:MovieEntity?=null
    var from=0
    var pos=0
    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
           movieId = arguments.getInt(ARG_PARAM1)
           from=arguments.getInt(ARG_PARAM2)
           pos=arguments.getInt(ARG_PARAM3)
           Log.e("posssssssssssssssss:",pos.toString())
        }
    }
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var view= inflater!!.inflate(R.layout.fragment_movie_details, container, false)
     if(from==0) {
         getMovieDetails(movieId, this)
         var movieLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(this.context, 1, GridLayoutManager.HORIZONTAL, false)
         var movieRecyclerView = view.findViewById<RecyclerView>(R.id.moviesRecyclerView) as RecyclerView
         var movieRelativeList = ArrayList<Movie>()
         var movieAdapter = MovieAdapter(this.context, movieRelativeList, R.layout.trending_card)
         movieRecyclerView.addItemDecoration(GridSpacingItemDecoration(2, dpToPx(10), true))
         movieRecyclerView.layoutManager = movieLayoutManager
         movieRecyclerView.itemAnimator = DefaultItemAnimator()
         movieRecyclerView.adapter = movieAdapter
         getSimilarMovies(movieId, movieAdapter)

         var personLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(this.context, 1, GridLayoutManager.HORIZONTAL, false)
         var actorsRecyclerView = view.findViewById<RecyclerView>(R.id.actorsRecyclerView) as RecyclerView
         var personRelativeList = ArrayList<Person>()
         var personAdapter = PersonAdapter(this.context, personRelativeList, R.layout.person_relative_card, 1)
         actorsRecyclerView.addItemDecoration(GridSpacingItemDecoration(2, dpToPx(10), true))
         actorsRecyclerView.layoutManager = personLayoutManager
         actorsRecyclerView.itemAnimator = DefaultItemAnimator()
         actorsRecyclerView.adapter = personAdapter
         getCastCrew(movieId, personAdapter)
     }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(from==1){

            movieCover.setImageBitmap(loadImageBitmap(this.context,Room.movies!!.get(pos).id+".jpg"))
            title.text = Room.movies!!.get(pos).title
            time.text = Room.movies!!.get(pos).runtime.toString() + " min"
            release_date.text = Room.movies!!.get(pos).release_date
            votes.text = Room.movies!!.get(pos).vote_count.toString() + " votes"
            average.text = Room.movies!!.get(pos).average.toString() + "/10"
            description.text = Room.movies!!.get(pos).overview
            genre.text=Room.movies!!.get(pos).genre
            this.moviefavoris = Room.movies!!.get(pos)

            addFavoris.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_black))
            favoris=true
        }
        addFavoris.setOnClickListener {


            if (favoris) {
                addFavoris.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_border_24dp))
                favoris=false
                if(moviefavoris!=null) {
                    moviefavoris!!.favoris=favoris
                    Room.deleteMovie(this.context, moviefavoris!!)
                }

            }else{
                addFavoris.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_black))
                favoris=true
                if(moviefavoris!=null){
                   moviefavoris!!.favoris=favoris
                   Room.addMovie(this.context,moviefavoris!!)
                   DownloadImage().execute(this.context.getString(R.string.image_url) + moviefavoris!!.poster_path)


                }
            }
        }
    }
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        }
    }
    override fun onDetach() {
        super.onDetach()
        mListener = null
    }
    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }
    fun onQueryResponse(movieDetails:MovieDetails){

         Picasso.with(this.context).load(this.context.getString(R.string.image_url) + movieDetails!!.backdrop_path)
                 .into(movieCover)

         title.text = movieDetails.title
         genre.text = ""
         for (i in 0 until movieDetails.genres.size) {
             genre.text = genre.text.toString() + movieDetails.genres[i].name + ", "
         }
         time.text = movieDetails.runtime.toString() + " min"
         release_date.text = movieDetails.release_date
         votes.text = movieDetails.vote_count.toString() + " votes"
         average.text = movieDetails.vote_average.toString() + "/10"
         description.text = movieDetails.overview

        var genre=""
        for(i in 0 until movieDetails.genres.size) {
            genre = genre+ movieDetails.genres.get(i).name+ ", "
        }
         this.moviefavoris = MovieEntity(movieId.toString(), movieDetails.poster_path, movieDetails.overview, movieDetails.release_date,
                 movieDetails.runtime, movieDetails.title, movieDetails.vote_average, movieDetails.vote_count,genre,
                 movieDetails.backdrop_path, favoris)



    }
    companion object {
        private val ARG_PARAM1 = "id"
        private val ARG_PARAM2 = "from"
        private val ARG_PARAM3 = "position"
        fun newInstance(param1: Int, param2:Int, param3:Int): MovieDetailsFragment {
            val fragment = MovieDetailsFragment()
            val args = Bundle()
            args.putInt(ARG_PARAM1, param1)
            args.putInt(ARG_PARAM2, param2)
            args.putInt(ARG_PARAM3, param3)
            fragment.arguments = args
            return fragment
        }
    }

    fun saveImage(context: Context, b: Bitmap, imageName: String) {
        val foStream: FileOutputStream
        try {
            foStream = context.openFileOutput(imageName, Context.MODE_PRIVATE)
            b.compress(Bitmap.CompressFormat.PNG, 100, foStream)
            foStream.close()
            Log.e("in save image",imageName)

        } catch (e: Exception) {
            Log.d("saveImage", "Exception 2, Something went wrong!")
            e.printStackTrace()
        }

    }

    private inner class DownloadImage : AsyncTask<String, Void, Bitmap>() {
        private val TAG = "DownloadImage"

        private fun downloadImageBitmap(sUrl: String): Bitmap? {
            var bitmap: Bitmap? = null
            try {
                val inputStream = URL(sUrl).openStream()
                bitmap = BitmapFactory.decodeStream(inputStream)
                inputStream.close()
                Log.e("in download image",sUrl)
            } catch (e: Exception) {
                Log.d(TAG, "Exception 1, Something went wrong!")
                e.printStackTrace()
            }

            return bitmap
        }

        override fun doInBackground(vararg params: String): Bitmap? {
            return downloadImageBitmap(params[0])
        }

        override fun onPostExecute(result: Bitmap) {
            saveImage(context, result,moviefavoris!!.id+".jpg")
        }
    }

    fun loadImageBitmap(context: Context, imageName: String): Bitmap? {
        var bitmap: Bitmap? = null
        val fiStream: FileInputStream
        try {
            fiStream = context.openFileInput(imageName)
            bitmap = BitmapFactory.decodeStream(fiStream)
            fiStream.close()
            Log.e("in load image",imageName)
        } catch (e: Exception) {
            Log.e("saveImage", "Exception 3, Something went wrong!")
            e.printStackTrace()
        }

        return bitmap
    }
}