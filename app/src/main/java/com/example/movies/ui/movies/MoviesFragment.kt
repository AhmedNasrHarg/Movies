package com.example.movies.ui.movies

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.data.Movie


class MoviesFragment : Fragment(),MovieAdapter.ItemClickListener {

    lateinit var moviesViewModel: MoviesViewModel
    lateinit var adapter: MovieAdapter
    lateinit var recyclerview:RecyclerView
    lateinit var  myNavCont:NavController
    lateinit var movies:List<Movie>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies, container, false)


        recyclerview = view.findViewById(R.id.movie_recyclerview)
        val layoutManager = GridLayoutManager(context!!,2)
        recyclerview.layoutManager=layoutManager

        adapter = MovieAdapter(context!!, listOf(),this)
        moviesViewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)
        moviesViewModel.getMovies()
        lifecycleScope.launchWhenStarted{
        moviesViewModel.movies.collect{
            adapter = MovieAdapter(context!!,it,this@MoviesFragment)
            recyclerview.adapter = adapter
            movies=it
            }
        }
        return view
    }
    override fun onItemClick(position: Int) {
        Log.d("nasr","nasrs")
        val arg = MoviesFragmentDirections.actionMoviesFragmentToMovieDetailsFragment(movies.get(position))
        myNavCont.navigate(arg)
    }

    override fun onLongClick(position: Int) {
        Log.d("nasr","nasrs d")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myNavCont = view.findNavController()
    }
}