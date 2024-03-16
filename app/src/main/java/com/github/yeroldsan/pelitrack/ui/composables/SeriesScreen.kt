package com.github.yeroldsan.pelitrack.ui.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.github.yeroldsan.pelitrack.model.SeriesResponse

@Composable
fun SeriesScreen(navController: NavController, series: List<SeriesResponse.Serie>, getSeries: () -> Unit) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(3),
    ) {
        if (series.isNotEmpty()) {
            items(series) { serie ->
                SerieCard(
                    serie = serie,
                    modifier = Modifier
                        .padding(0.dp)
                )
            }
        } else {
            item { Text(text = "No series found") }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SerieCard(serie: SeriesResponse.Serie, modifier: Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(0.dp),
        onClick = { /* TODO */ },
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://image.tmdb.org/t/p/w500${serie.posterPath}")
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