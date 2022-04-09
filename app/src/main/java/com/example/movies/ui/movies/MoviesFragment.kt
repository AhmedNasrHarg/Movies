package com.example.movies.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R


class MoviesFragment : Fragment() {

    lateinit var moviesViewModel: MoviesViewModel
    lateinit var adapter: MovieAdapter
    lateinit var recyclerview:RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies, container, false)

        recyclerview = view.findViewById(R.id.movie_recyclerview)
        val layoutManager = GridLayoutManager(context!!,2)
        recyclerview.layoutManager=layoutManager

        adapter = MovieAdapter(context!!, listOf())
        moviesViewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)
        moviesViewModel.getMovies()
        lifecycleScope.launchWhenStarted{
        moviesViewModel.movies.collect{
            adapter = MovieAdapter(context!!, it)
            recyclerview.adapter = adapter
            }
        }
        return view
    }
}