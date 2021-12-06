package com.decline.dictionaryapp.feature_dictionary.presentation

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@ExperimentalComposeUiApi
@Composable
fun CustomTextField(height: Float) {
    val viewModel: DictionaryViewModel = hiltViewModel()
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        value = viewModel.searchQuery.value,
        onValueChange = viewModel::onSearch,
        shape = RoundedCornerShape(8.dp),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colors.onBackground
            )
        },
        textStyle = TextStyle(fontSize = 16.sp),
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.onPrimary,
            textColor = MaterialTheme.colors.onBackground,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = MaterialTheme.colors.onBackground
        ),
        modifier = Modifier
            .fillMaxWidth(.7f)
            .fillMaxHeight(height),
        placeholder = {
            Text(text = "Search..", color = MaterialTheme.colors.onBackground)
        },
        keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() })
    )
}