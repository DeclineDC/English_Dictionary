package com.decline.dictionaryapp.feature_dictionary.di

import android.app.Application
import androidx.room.Room
import com.decline.dictionaryapp.core.util.Constants.BASE_URL
import com.decline.dictionaryapp.feature_dictionary.data.local.Converters
import com.decline.dictionaryapp.feature_dictionary.data.local.WordDatabase
import com.decline.dictionaryapp.feature_dictionary.data.remote.DictionaryAPI
import com.decline.dictionaryapp.feature_dictionary.data.repository.WordRepositoryImpl
import com.decline.dictionaryapp.feature_dictionary.data.util.GsonParser
import com.decline.dictionaryapp.feature_dictionary.domain.repository.WordRepository
import com.decline.dictionaryapp.feature_dictionary.domain.use_case.GetWord
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WordModule {

    @Provides
    @Singleton
    fun provideGetWordUseCase(repository: WordRepository): GetWord {
        return GetWord(repository)
    }

    @Provides
    @Singleton
    fun provideWordRepository(
        api: DictionaryAPI,
        db: WordDatabase
    ): WordRepository {
        return WordRepositoryImpl(api, db.dao)
    }

    @Provides
    @Singleton
    fun provideWordDataBase(app: Application): WordDatabase {
        return Room.databaseBuilder(
            app, WordDatabase::class.java, "word_db"
        ).addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }

    @Provides
    @Singleton
    fun provideDictionaryApi(): DictionaryAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryAPI::class.java)
    }
}