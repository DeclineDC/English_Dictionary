package com.decline.dictionaryapp.feature_dictionary.domain.repository

import com.decline.dictionaryapp.core.util.Resource
import com.decline.dictionaryapp.feature_dictionary.domain.model.Word
import kotlinx.coroutines.flow.Flow

interface WordRepository {

    fun getWord(word: String): Flow<Resource<List<Word>>>
}