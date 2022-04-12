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
                                            //1.
class MovieAdapter(var context:Context, val mItemClickListener:ItemClickListener) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    //2.
    interface ItemClickListener{
        fun onItemClick(position: Int)
        fun onLongClick(position: Int)
    }

    var items= listOf<Movie>()
        set(value) {
        field = value
        notifyDataSetChanged()
        }
    //Constructor
    constructor(  context:Context,  items:List<Movie>,  mItemClickListener:ItemClickListener) : this(context,mItemClickListener) {
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

    inner class ViewHolder(itemVeiw: View): RecyclerView.ViewHolder(itemVeiw){
        init {
            itemVeiw.setOnClickListener{
                mItemClickListener.onItemClick(adapterPosition)
            }
            itemVeiw.setOnLongClickListener{
                mItemClickListener.onLongClick(adapterPosition)
                return@setOnLongClickListener true
            }
        }
        val movieImg: ImageView = itemView.findViewById(R.id.movie_imageView)
        val title: TextView = itemVeiw.findViewById(R.id.movie_title)
    }

}
