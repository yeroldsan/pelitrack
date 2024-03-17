package com.github.yeroldsan.pelitrack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.yeroldsan.pelitrack.model.MoviesResponse
import com.github.yeroldsan.pelitrack.model.PelitrackRepository
import com.github.yeroldsan.pelitrack.model.SeriesResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed interface PelitrackUiState {
    data class Success(val movies: List<MoviesResponse.Movie>, val series: List<SeriesResponse.Serie>) : PelitrackUiState
    object Initial : PelitrackUiState
    object Loading : PelitrackUiState
    object Error : PelitrackUiState
}

class PelitrackViewModel: ViewModel() {
    private val _pelitrackUiState = MutableStateFlow<PelitrackUiState>(PelitrackUiState.Loading)
    val pelitrackUiState: StateFlow<PelitrackUiState> = _pelitrackUiState

    // Settings
    private val _openBottomSheet = MutableStateFlow(false)
    val openBottomSheet: StateFlow<Boolean> = _openBottomSheet

    private val _apiKey = MutableStateFlow<String?>(null)
    val apiKey: StateFlow<String?> = _apiKey

    init {
        if (apiKey.value != null) {
            fetchMoviesAndSeries()
        } else {
            _pelitrackUiState.value = PelitrackUiState.Initial
        }
    }

    fun openSettings() {
        _openBottomSheet.value = !_openBottomSheet.value
    }

    fun setApiKey(userApiKey: String?) {
        _apiKey.value = userApiKey
        fetchMoviesAndSeries()
    }

    fun closeSettings() {
        _openBottomSheet.value = false
    }

    fun retryFetch() {
        fetchMoviesAndSeries()
    }

    private fun fetchMoviesAndSeries() {
        _pelitrackUiState.value = PelitrackUiState.Loading
        viewModelScope.launch {
            try {
                val moviesResponse = PelitrackRepository.create().getMovies(apiKey.value)
                val seriesResponse = PelitrackRepository.create().getSeries(apiKey.value)

                if (moviesResponse.isSuccessful && seriesResponse.isSuccessful) {
                    _pelitrackUiState.value = PelitrackUiState.Success(
                        moviesResponse.body()?.movies ?: emptyList(),
                        seriesResponse.body()?.series ?: emptyList()
                    )
                }
            } catch (e: Exception) {
                _pelitrackUiState.value = PelitrackUiState.Error
            }
        }
    }
}
