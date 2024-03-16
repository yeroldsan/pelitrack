package com.github.yeroldsan.pelitrack.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.github.yeroldsan.pelitrack.R
import com.github.yeroldsan.pelitrack.ui.screens.FavoritesScreen
import com.github.yeroldsan.pelitrack.ui.screens.MoviesScreen
import com.github.yeroldsan.pelitrack.ui.screens.SeriesScreen
import com.github.yeroldsan.pelitrack.viewmodel.PelitrackViewModel

sealed class Screen(val route: String, val icon: Int) {
    data object Movies : Screen("Movies",  R.drawable.baseline_movie_24)
    data object Series : Screen("Series", R.drawable.baseline_tv_24)
    data object Favorites : Screen("Favorites", R.drawable.baseline_collections_bookmark_24)
}

@Composable
fun NavGraph(navController: NavHostController, vModel: PelitrackViewModel) {
    val uiSate by vModel.pelitrackUiState.collectAsState()
    NavHost(
        navController = navController,
        startDestination = Screen.Series.route
    ) {
        composable(Screen.Movies.route) {
            MoviesScreen(retryAction = vModel::retryFetch, pelitrackUiState = uiSate)
        }
        composable(Screen.Series.route) {
            SeriesScreen(retryAction = vModel::retryFetch, pelitrackUiState = uiSate)
        }
        composable(Screen.Favorites.route) {
            FavoritesScreen(pelitrackUiState = uiSate, retryAction = vModel::retryFetch)
        }
    }
}