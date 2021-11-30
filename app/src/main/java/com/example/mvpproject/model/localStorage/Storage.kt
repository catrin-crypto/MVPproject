package com.example.mvpproject.model.localStorage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mvpproject.model.api.GitHubUser
import com.example.mvpproject.model.api.UserRepositories

@Database(exportSchema = false, entities = [GitHubUser::class, UserRepositories::class], version = 1)
abstract class Storage : RoomDatabase() {

    abstract fun getUserDao(): UserDao
    abstract fun getRepositoryDao(): RepositoryDao

}