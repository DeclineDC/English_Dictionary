package com.decline.dictionaryapp.feature_dictionary.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun CustomIconButton(
    onClick: () -> Unit,
    painter: Painter,
    height: Float
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .clip(shape = CircleShape)
            .background(MaterialTheme.colors.onPrimary, shape = CircleShape)
            .fillMaxSize(height)
    ) {
        Icon(
            painter,
            contentDescription = null,
            tint = MaterialTheme.colors.onBackground,
            modifier = Modifier.fillMaxSize(.5f)
        )
    }
}