package com.decline.dictionaryapp.feature_dictionary.domain.use_case

import com.decline.dictionaryapp.core.util.Resource
import com.decline.dictionaryapp.feature_dictionary.domain.model.Word
import com.decline.dictionaryapp.feature_dictionary.domain.repository.WordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWord(
    private val repository: WordRepository
) {

    operator fun invoke(word: String): Flow<Resource<List<Word>>> {
        if (word.isBlank()) {
            return flow { }
        }
        return repository.getWord(word)
    }
}