package com.example.movies.ui.movie_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.data.Movie

class MovieDetailsFragment : Fragment() {

    lateinit var movie:Movie
    lateinit var title:TextView
    lateinit var rating:RatingBar
    lateinit var date:TextView
    lateinit var overview: EditText
    lateinit var poster:ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_movie_details, container, false)
        var args = MovieDetailsFragmentArgs.fromBundle(arguments!!)
        movie = args.movie

        title = view.findViewById(R.id.movieDetails_title)
        title.text=movie.title
        rating = view.findViewById(R.id.movieDetails_ratingBar)
        rating.rating= (movie.vote_average/2.0).toFloat()
        date = view.findViewById(R.id.movieDetails_date)
        date.text=movie.release_date
        overview = view.findViewById(R.id.movieDetails_overview)
        overview.text.append(movie.overview)

        poster = view.findViewById(R.id.movieDetails_imageView)
        val imgUrl = "https://image.tmdb.org/t/p/w500/${movie.poster_path}"
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(context!!)
            .load(imgUri)
            .into(poster)

        return view
    }


}