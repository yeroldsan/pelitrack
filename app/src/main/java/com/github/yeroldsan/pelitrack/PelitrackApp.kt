/*
 * *
 *  * Created by Yerold S.
 *  * Copyright (c) 2024 . All rights reserved.
 *
 */

package com.github.yeroldsan.pelitrack

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.github.yeroldsan.pelitrack.nav.NavGraph
import com.github.yeroldsan.pelitrack.nav.Screen
import com.github.yeroldsan.pelitrack.ui.composables.MainBottomAppBar
import com.github.yeroldsan.pelitrack.ui.composables.MainTopAppBar
import com.github.yeroldsan.pelitrack.ui.composables.SettingsPanel
import com.github.yeroldsan.pelitrack.viewmodel.PelitrackViewModel

@Preview(showBackground = true)
@Composable
fun PelitrackAppPreview() {
    PelitrackApp()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PelitrackApp(vModel: PelitrackViewModel = viewModel()) {
    val scrollTopBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val openBottomSheet by vModel.openBottomSheet.collectAsState()
    val navController = rememberNavController()
    val screens = listOf(
        Screen.Movies,
        Screen.Series,
        Screen.Favorites
    )
    Scaffold(
        modifier = Modifier.nestedScroll(scrollTopBehavior.nestedScrollConnection),
        topBar = {
            MainTopAppBar(
                openSettings = vModel::openSettings,
                scrollBehavior = scrollTopBehavior,
                navController = navController
            )
        },
        bottomBar = {
            MainBottomAppBar(navController = navController, screens = screens)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            // NAV GRAPH
            NavGraph(navController = navController, vModel = vModel)
        }

        if (openBottomSheet) {
            SettingsPanel(vModel = vModel)
        }
    }
}
