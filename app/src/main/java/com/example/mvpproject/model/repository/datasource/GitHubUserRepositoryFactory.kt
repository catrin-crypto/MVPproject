package com.example.mvpproject.model.repository.datasource

import com.example.mvpproject.model.api.GitHubApiFactory
import com.example.mvpproject.model.datasource.UserCacheDataSourceImpl
import com.example.mvpproject.model.datasource.UserDataSourceImpl
import com.example.mvpproject.model.localStorage.LocalStorageFactory

object GitHubUserRepositoryFactory {

    private val gitHubUserRepository: UsersRepository by lazy(LazyThreadSafetyMode.NONE) {
        GitHubUserRepositoryImpl(
            UserDataSourceImpl(
                GitHubApiFactory.create()
            ),
            UserCacheDataSourceImpl(
                LocalStorageFactory.create()
            ),
            GitHubRepositoryDataSourceImpl(
                GitHubApiFactory.create()
            ),
            GitHubRepositoryCacheDataSourceImpl(
                LocalStorageFactory.create()
            )
        )
    }

    fun create(): UsersRepository = gitHubUserRepository

}