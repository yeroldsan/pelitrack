package com.github.yeroldsan.pelitrack.ui.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.github.yeroldsan.pelitrack.model.MoviesResponse
import com.github.yeroldsan.pelitrack.viewmodel.PelitrackUiState

@Composable
fun MoviesScreen(retryAction: () -> Unit, pelitrackUiState: PelitrackUiState) {
    when (pelitrackUiState) {
        is PelitrackUiState.Initial -> InitialScreen()
        is PelitrackUiState.Loading -> LoadingScreen()
        is PelitrackUiState.Error -> ErrorScreen(retryAction = { retryAction() })
        is PelitrackUiState.Success -> {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Fixed(3),
            ) {
                items(pelitrackUiState.movies) { movie ->
                    MovieCard(
                        movie = movie,
                        modifier = Modifier
                            .padding(4.dp, 4.dp)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieCard(movie: MoviesResponse.Movie, modifier: Modifier) {
    Card(
        modifier = modifier,
        onClick = { /* TODO */ },
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                .crossfade(true)
                .build(),
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )
    }
}
