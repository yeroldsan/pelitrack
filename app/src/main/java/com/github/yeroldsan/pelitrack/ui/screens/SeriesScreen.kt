package com.github.yeroldsan.pelitrack.ui.screens

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.github.yeroldsan.pelitrack.R
import com.github.yeroldsan.pelitrack.model.SeriesResponse
import com.github.yeroldsan.pelitrack.viewmodel.PelitrackUiState

@Composable
fun SeriesScreen(retryAction: () -> Unit, pelitrackUiState: PelitrackUiState) {
    when (pelitrackUiState) {
        is PelitrackUiState.Initial -> InitialScreen()
        is PelitrackUiState.Loading -> LoadingScreen()
        is PelitrackUiState.Error -> ErrorScreen(retryAction = { retryAction() })
        is PelitrackUiState.Success -> {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Fixed(3),
            ) {
                items(pelitrackUiState.series) { serie ->
                    SerieCard(
                        serie = serie,
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
fun SerieCard(serie: SeriesResponse.Serie, modifier: Modifier) {
    Card(
        modifier = modifier,
        onClick = { /* TODO */ },
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(stringResource(R.string.api_images_url, "${serie.posterPath}"))
                .crossfade(true)
                .build(),
            contentDescription = serie.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )
    }
}