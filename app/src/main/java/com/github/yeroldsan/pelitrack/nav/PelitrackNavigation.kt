package com.github.yeroldsan.pelitrack.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.Tv
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.github.yeroldsan.pelitrack.ui.screens.FavoritesScreen
import com.github.yeroldsan.pelitrack.ui.screens.MoviesScreen
import com.github.yeroldsan.pelitrack.ui.screens.SeriesScreen
import com.github.yeroldsan.pelitrack.viewmodel.PelitrackViewModel

sealed class Screen(
    val route: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
) {
    data object Movies : Screen("Movies", Icons.Outlined.Movie, Icons.Filled.Movie)
    data object Series : Screen("Series", Icons.Outlined.Tv, Icons.Filled.LiveTv)
    data object Favorites : Screen("Favorites", Icons.Outlined.FavoriteBorder, Icons.Filled.Favorite)
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
