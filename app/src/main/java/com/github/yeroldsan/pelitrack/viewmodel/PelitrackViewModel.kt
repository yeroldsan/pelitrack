package com.github.yeroldsan.pelitrack.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.yeroldsan.pelitrack.model.PelitrackData
import com.github.yeroldsan.pelitrack.model.PelitrackRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PelitrackViewModel: ViewModel() {
    private val _movies = MutableStateFlow<List<PelitrackData.Movie>>(emptyList())

    val movies: StateFlow<List<PelitrackData.Movie>> = _movies

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            try {
                val result = PelitrackRepository.create().getMovies()
                if (result.isSuccessful) {
                    val moviesResponse = result.body()
                    _movies.value = moviesResponse?.movies ?: emptyList()
                }
                Log.d("Result", result.body().toString())
            } catch (e: Exception) {
                Log.d("Error", e.toString())
            }
        }
    }
}
