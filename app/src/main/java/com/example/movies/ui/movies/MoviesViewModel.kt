package com.example.movies.ui.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.data.Movie
import com.example.movies.data.MoviesApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MoviesViewModel: ViewModel(){
    val movies = MutableStateFlow<List<Movie>>(listOf())
    private var moviesViewModelJob = Job()
    private val coroutineScope = CoroutineScope(moviesViewModelJob + Dispatchers.Main )


    fun getMovies():List<Movie>{
        viewModelScope.launch{
            var getMoviesDeferred = MoviesApi.retrofitService.getMovies()
            try {
                var res = getMoviesDeferred.await().results
                movies.emit(res)
            } catch (e: Exception) {
                movies.emit(listOf())
                }
            }
        return movies.value
    }
    override fun onCleared() {
        super.onCleared()
        moviesViewModelJob.cancel()
    }
}