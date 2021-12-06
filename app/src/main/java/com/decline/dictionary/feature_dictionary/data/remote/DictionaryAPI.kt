package com.decline.dictionaryapp.feature_dictionary.data.remote

import com.decline.dictionaryapp.feature_dictionary.data.remote.dto.WordDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryAPI {

    @GET("/api/v2/entries/en/{word}")
    suspend fun getWord(
        @Path("word") word: String
    ): List<WordDto>
}