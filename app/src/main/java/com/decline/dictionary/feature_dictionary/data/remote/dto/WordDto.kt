package com.decline.dictionaryapp.feature_dictionary.data.remote.dto

import com.decline.dictionaryapp.feature_dictionary.data.local.entity.WordEntity
import com.decline.dictionaryapp.feature_dictionary.domain.model.Word

data class WordDto(
    val meanings: List<MeaningDto>,
    val origin: String?,
    val phonetic: String?,
    val phonetics: List<PhoneticDto>,
    val word: String
) {
    fun toWordEntity(): WordEntity {
        return WordEntity(
            meanings = meanings.map { it.toMeaning() },
            origin = origin ?: "",
            phonetic = phonetic ?: "",
            word = word,
        )
    }

}