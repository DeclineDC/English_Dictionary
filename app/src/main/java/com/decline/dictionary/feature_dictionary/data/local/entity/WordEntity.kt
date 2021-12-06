package com.decline.dictionaryapp.feature_dictionary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.decline.dictionaryapp.feature_dictionary.domain.model.Meaning
import com.decline.dictionaryapp.feature_dictionary.domain.model.Word

@Entity
data class WordEntity(
    val word: String,
    val phonetic: String,
    val origin: String,
    val meanings: List<Meaning>,
    @PrimaryKey val id: Int? = null
) {
    fun toWord(): Word {
        return Word(
            meanings = meanings,
            phonetic = phonetic,
            origin = origin,
            word = word
        )
    }
}
