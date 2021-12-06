package com.decline.dictionaryapp.feature_dictionary.domain.model

data class Word(
    val meanings: List<Meaning>,
    val origin: String?,
    val phonetic: String?,
    val word: String
)
