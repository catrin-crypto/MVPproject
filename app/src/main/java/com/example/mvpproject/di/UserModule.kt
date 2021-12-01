package com.example.mvpproject.di

import com.example.mvpproject.model.datasource.UserCacheDataSource
import com.example.mvpproject.model.datasource.UserCacheDataSourceImpl
import com.example.mvpproject.model.datasource.UserDataSource
import com.example.mvpproject.model.datasource.UserDataSourceImpl
import com.example.mvpproject.model.repository.datasource.*
import com.example.mvpproject.ui.MainActivity
import com.example.mvpproject.ui.UserFragment
import com.example.mvpproject.ui.UsersFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [NetworkModule::class, StorageModule::class])
interface UserModule {

    @ContributesAndroidInjector
    fun bindMainFragment(): MainActivity

    @ContributesAndroidInjector
    fun bindUsersFragment(): UsersFragment

    @ContributesAndroidInjector
    fun bindUserFragment(): UserFragment

    @Binds
    fun bindGitHubUserRepository(
        gitHubUserRepository: GitHubUserRepositoryImpl
    ): UsersRepository

    @Binds
    fun bindGitHubUserDataSource(
        gitHubUserDataSource: UserDataSourceImpl
    ): UserDataSource

    @Binds
    fun bindGitHubUserCacheDataSource(
        gitHubUserCacheDataSource: UserCacheDataSourceImpl
    ): UserCacheDataSource

    @Binds
    fun bindGitHubRepositoryDataSource(
        gitHubRepositoryDataSource: GitHubRepositoryDataSourceImpl
    ): GitHubRepositoryDataSource

    @Binds
    fun bindGitHubRepositoryCacheDataSource(
        gitHubRepositoryCacheDataSource: GitHubRepositoryCacheDataSourceImpl
    ): GitHubRepositoryCacheDataSource
}