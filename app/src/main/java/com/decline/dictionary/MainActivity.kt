package com.decline.dictionaryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.decline.dictionaryapp.feature_dictionary.presentation.CustomTopBar
import com.decline.dictionaryapp.feature_dictionary.presentation.DictionaryViewModel
import com.decline.dictionaryapp.feature_dictionary.presentation.WordItem
import com.decline.dictionaryapp.ui.theme.DictionaryAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            DictionaryScreen()

        }
    }

    @ExperimentalComposeUiApi
    @Composable
    fun DictionaryScreen() {
        DictionaryAppTheme {
            val viewModel: DictionaryViewModel = hiltViewModel()
            val state = viewModel.state.value
            val scaffoldState = rememberScaffoldState()

            LaunchedEffect(key1 = true) {
                viewModel.eventFlow.collectLatest { event ->
                    when (event) {
                        is DictionaryViewModel.UIEvent.ShowSnackbar -> {
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = event.message
                            )
                        }
                    }
                }
            }
            Scaffold(
                topBar = {
                    CustomTopBar()
                },
            ) {
                Box(
                    modifier = Modifier.background(MaterialTheme.colors.background)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(state.wordItems.size) { position ->
                                val word = state.wordItems[position]
                                if (position > 0) {
                                    Spacer(modifier = Modifier.height(8.dp))
                                }
                                WordItem(word = word)
                                if (position < state.wordItems.size - 1) {
                                    Divider(color = MaterialTheme.colors.primary, thickness = 4.dp)
                                }

                            }
                        }

                    }
                }
            }

        }

    }

}



