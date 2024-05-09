package com.github.yeroldsan.pelitrack.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.yeroldsan.pelitrack.R
import com.github.yeroldsan.pelitrack.viewmodel.PelitrackUiState

@Composable
fun FavoritesScreen(pelitrackUiState: PelitrackUiState, retryAction: () -> Unit) {
    when (pelitrackUiState) {
        is PelitrackUiState.Initial -> InitialScreen()
        is PelitrackUiState.Loading -> LoadingScreen()
        is PelitrackUiState.Error -> ErrorScreen(
            modifier = Modifier.fillMaxSize(),
            retryAction = { retryAction() }
        )
        is PelitrackUiState.Success -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Favorite,
                    contentDescription = stringResource(R.string.favorite_content_description)
                )
                Text(
                    text = stringResource(R.string.favorites_screen_message),
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}
