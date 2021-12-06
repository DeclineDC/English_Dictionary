package com.decline.dictionaryapp.feature_dictionary.presentation

import com.decline.dictionaryapp.feature_dictionary.domain.model.Word

data class WordState(
    val wordItems: List<Word> = emptyList(),
    val isLoading: Boolean = false
)