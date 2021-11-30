package com.example.mvpproject.model.localStorage

import androidx.room.Room
import com.example.mvpproject.App


object LocalStorageFactory {

    private val inMemoryDatabase: Storage by lazy {
        Room.inMemoryDatabaseBuilder(App.context, Storage::class.java)
            .fallbackToDestructiveMigration()
            .build()
    }

    private val database: Storage by lazy {
        var context = App.context
        Room.databaseBuilder(context, Storage::class.java, "github.db")
            .build()
    }

    fun create(): Storage = database

}