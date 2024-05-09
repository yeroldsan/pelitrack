package com.github.yeroldsan.pelitrack.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.github.yeroldsan.pelitrack.R
import com.github.yeroldsan.pelitrack.viewmodel.PelitrackViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsPanel(vModel: PelitrackViewModel) {
    ModalBottomSheet(
        modifier = Modifier.imePadding(),
        onDismissRequest = { vModel.closeSettings() }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(24.dp)
        ) {
            var keyHidden by remember { mutableStateOf(true) }
            val apiKey by vModel.apiKey.collectAsState()

            Text(
                text = stringResource(R.string.save_api_key_bottom_sheet),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            OutlinedTextField(
                value = apiKey ?: "",
                onValueChange = { vModel.setApiKey(it) },
                label = { Text(text = stringResource(R.string.api_key_text_input_field)) },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                visualTransformation =
                if (keyHidden) {
                    PasswordVisualTransformation()
                } else {
                    VisualTransformation.None
                },
                trailingIcon = {
                    val visibilityIcon =
                        if (keyHidden) {
                            Icons.Filled.Visibility
                        } else {
                            Icons.Filled.VisibilityOff
                        }
                    // Localized description for accessibility services
                    val description =
                        if (keyHidden) {
                            stringResource(R.string.show_key)
                        } else {
                            stringResource(R.string.hide_key)
                        }

                    IconButton(
                        onClick = { keyHidden = !keyHidden }
                    ) {
                        Icon(
                            imageVector = visibilityIcon,
                            contentDescription = description
                        )
                    }
                }
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 38.dp, vertical = 24.dp),
                onClick = {
                    vModel.retryFetch()
                    vModel.closeSettings()
                }
            ) {
                Text(text = stringResource(R.string.save_button))
            }
        }
    }
}
