package com.decline.dictionaryapp.feature_dictionary.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decline.dictionaryapp.core.util.Constants.RANDOM_WORD
import com.decline.dictionaryapp.core.util.Constants.SEARCH_DELAY
import com.decline.dictionaryapp.core.util.Resource
import com.decline.dictionaryapp.feature_dictionary.domain.use_case.GetWord
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class DictionaryViewModel @Inject constructor(
    private val getWord: GetWord
) : ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    private val _state = mutableStateOf(WordState())
    val state: State<WordState> = _state

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var searchJob: Job? = null


    init {
        getRandomWord()
    }

    fun onSearch(query: String) {
        _searchQuery?.value = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(SEARCH_DELAY)
            getWord(query)
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _state.value = state.value.copy(
                                wordItems = result.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                        is Resource.Loading -> {
                            _state.value = state.value.copy(
                                wordItems = result.data ?: emptyList(),
                                isLoading = true
                            )
                        }
                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                wordItems = result.data ?: emptyList(),
                                isLoading = false
                            )
                            _eventFlow.emit(UIEvent.ShowSnackbar(result.message ?: "Unknown error"))
                        }
                    }
                }.launchIn(this)
        }
    }

    fun getRandomWord() {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            getWord(RANDOM_WORD[Random.nextInt(RANDOM_WORD.size)])
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _state.value = state.value.copy(
                                wordItems = result.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                        is Resource.Loading -> {
                            _state.value = state.value.copy(
                                wordItems = result.data ?: emptyList(),
                                isLoading = true
                            )
                        }
                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                wordItems = result.data ?: emptyList(),
                                isLoading = false
                            )
                            _eventFlow.emit(UIEvent.ShowSnackbar(result.message ?: "Unknown error"))
                        }
                    }
                }.launchIn(this)
        }
    }


    sealed class UIEvent {
        data class ShowSnackbar(val message: String) : UIEvent()
    }
}