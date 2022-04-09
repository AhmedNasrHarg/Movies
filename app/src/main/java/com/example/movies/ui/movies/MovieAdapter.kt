package com.example.movies.ui.movies

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.data.Movie

class MovieAdapter(var context:Context) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    var items= listOf<Movie>()
        set(value) {
        field = value
        notifyDataSetChanged()
        }
    //Constructor
    constructor(  context:Context,  items:List<Movie>) : this(context) {
        this.context=context
        this.items=  items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.movie_row, parent, false) as View
        return ViewHolder(view)
    }

    override fun onBindViewHolder( holder:ViewHolder , position:Int ) {
        val item = items.get(position)
        val imgUrl = "https://image.tmdb.org/t/p/w500/${item.poster_path}"
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(holder.movieImg.context)
            .load(imgUri)
            .fitCenter()
            .into(holder.movieImg)
        holder.title.text = item.title
    }

    override fun getItemCount() = items.size

    class ViewHolder(itemVeiw: View): RecyclerView.ViewHolder(itemVeiw){
        val movieImg: ImageView = itemView.findViewById(R.id.movie_imageView)
        val title: TextView = itemVeiw.findViewById(R.id.movie_title)
    }

}