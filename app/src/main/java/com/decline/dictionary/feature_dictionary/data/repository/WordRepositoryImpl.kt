package com.decline.dictionaryapp.feature_dictionary.data.repository

import com.decline.dictionaryapp.core.util.Resource
import com.decline.dictionaryapp.feature_dictionary.data.local.WordDao
import com.decline.dictionaryapp.feature_dictionary.data.remote.DictionaryAPI
import com.decline.dictionaryapp.feature_dictionary.domain.model.Word
import com.decline.dictionaryapp.feature_dictionary.domain.repository.WordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordRepositoryImpl(
    private val api: DictionaryAPI,
    private val dao: WordDao
) : WordRepository {
    override fun getWord(word: String): Flow<Resource<List<Word>>> = flow {
        emit(Resource.Loading())

        val words = dao.getWords(word).map { it.toWord() }
        emit(Resource.Loading(data = words))

        try {
            val remoteWords = api.getWord(word)
            dao.deleteWords(remoteWords.map { it.word })
            dao.insertWords(remoteWords.map { it.toWordEntity() })
        } catch (e: HttpException) {
            emit(Resource.Error(message = "Word not found.", data = words))
        } catch (e: IOException) {
            emit(Resource.Error(message = "Couldn't reach server.", data = words))
        }

        val newWords = dao.getWords(word).map { it.toWord() }
        emit(Resource.Success(data = newWords))
    }

}