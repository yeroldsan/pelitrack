package com.github.yeroldsan.pelitrack.ui.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import com.github.yeroldsan.pelitrack.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(
    openSettings: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    navController: NavController
) {
    TopAppBar(
        title = { Text(text = stringResource(R.string.top_bar_title), maxLines = 1, overflow = TextOverflow.Ellipsis) },
        scrollBehavior = scrollBehavior,
        actions = {
            IconButton(
                onClick = {
                    openSettings()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = stringResource(R.string.settings_icon_description)
                )
            }
        }
    )
}
