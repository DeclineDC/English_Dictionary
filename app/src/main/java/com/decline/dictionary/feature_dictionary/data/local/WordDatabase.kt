package com.decline.dictionaryapp.feature_dictionary.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.decline.dictionaryapp.feature_dictionary.data.local.entity.WordEntity

@Database(
    entities = [WordEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class WordDatabase : RoomDatabase() {

    abstract val dao: WordDao
}