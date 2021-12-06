package com.decline.dictionaryapp.feature_dictionary.data.local

import androidx.room.*
import com.decline.dictionaryapp.feature_dictionary.data.local.entity.WordEntity

@Dao
interface WordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWords(words: List<WordEntity>)

    @Query("DELETE FROM wordentity WHERE word IN(:words)")
    suspend fun deleteWords(words: List<String>)

    @Query("SELECT * FROM wordentity WHERE word LIKE '%' || :word || '%'")
    suspend fun getWords(word: String): List<WordEntity>
}