package com.decline.dictionaryapp.feature_dictionary.presentation

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.decline.dictionaryapp.R

@ExperimentalComposeUiApi
@Composable
fun CustomTopBar() {
    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.25f)
            ) {
                TopBarContent(.60f)
            }
        }
        else -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.14f)
            ) {
                TopBarContent(.50f)
            }
        }
    }

}

@ExperimentalComposeUiApi
@Composable
fun TopBarContent(height: Float) {
    val viewModel: DictionaryViewModel = hiltViewModel()
    val darkTheme: Boolean = isSystemInDarkTheme()
    Image(
        painter = if (darkTheme) {
            painterResource(id = R.drawable.dark_mode_background)
        } else {
            painterResource(id = R.drawable.light_mode_background)
        },
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxWidth()
    )
    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        CustomTextField(height)
        CustomIconButton(
            onClick = {
                viewModel.getRandomWord()
            },
            painter = painterResource(id = R.drawable.ic_random),
            height = height
        )

    }
}
