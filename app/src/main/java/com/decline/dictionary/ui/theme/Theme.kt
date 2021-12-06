package com.decline.dictionaryapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = PrimaryDarkModeBlue,
    primaryVariant = PrimaryDarkModeBlue,
    onPrimary = DarkModeBlue,
    background = Black,
    onBackground = Color.White
)

private val LightColorPalette = lightColors(
    primary = PrimaryLightModeBlue,
    primaryVariant = PrimaryLightModeBlue,
    onPrimary = LightModeBlue,
    background = Color.White,
    onBackground = Color.Black

)

@Composable
fun DictionaryAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = SemiTransparent
    )
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}