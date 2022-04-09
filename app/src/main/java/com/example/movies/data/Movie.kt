package com.example.movies.data

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

data class Movie(val title:String,
                 val poster_path:String,
                 val release_date:String,
                 val vote_average:Double,
                 val vote_count:Int,
                 val overview: String
                 ) {}
data class MoviesResponse(val page:Int,
                          val results:List<Movie>,
                          val total_pages:Int,
                          val total_results:Int){}
/*fun ImageView.bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .into(imgView)
    }
}*/