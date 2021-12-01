package com.example.mvpproject.di

import android.content.Context
import androidx.room.Room
import com.example.mvpproject.model.localStorage.Storage
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class StorageModule {

    @Singleton
    @Named("InMemory")
    @Provides
    fun provideInMemoryDatabase(context: Context): Storage =
        Room.inMemoryDatabaseBuilder(context, Storage::class.java)
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Named("Persisted")
    @Provides
    fun providePersistedDatabase(context: Context): Storage =
        Room.databaseBuilder(context, Storage::class.java, "github.db")
            .build()

}