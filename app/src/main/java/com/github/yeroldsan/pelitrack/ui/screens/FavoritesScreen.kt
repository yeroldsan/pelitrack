package com.github.yeroldsan.pelitrack.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.github.yeroldsan.pelitrack.R
import com.github.yeroldsan.pelitrack.viewmodel.PelitrackUiState

@Composable
fun FavoritesScreen(pelitrackUiState: PelitrackUiState, retryAction: () -> Unit) {
    when (pelitrackUiState) {
        is PelitrackUiState.Initial -> InitialScreen()
        is PelitrackUiState.Loading -> LoadingScreen()
        is PelitrackUiState.Error -> ErrorScreen(retryAction = { retryAction() })
        is PelitrackUiState.Success -> {
            Column (
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(painter = painterResource(id = R.drawable.baseline_collections_bookmark_24), contentDescription = "Favorite")
                Text(
                    text = "Place for your favorite movies and series.",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}