package com.example.mvpproject.model.datasource

import com.example.mvpproject.model.api.GitHubUser
import com.example.mvpproject.model.localStorage.Storage
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Named


class UserCacheDataSourceImpl
    @Inject constructor(
    @Named("Persisted") private val gitHubStorage: Storage
) : UserCacheDataSource {

    override fun retain(users: List<GitHubUser>): Observable<List<GitHubUser>> =
        gitHubStorage
            .getUserDao()
            .retain(users)
            .andThen(
                gitHubStorage
                    .getUserDao()
                    .getUsers()
            )

    override fun retain(user: GitHubUser): Single<GitHubUser> =
        gitHubStorage
            .getUserDao()
            .retain(user)
            .andThen(
                gitHubStorage
                    .getUserDao()
                    .getUserByLogin(user.login)
                    .firstOrError()
            )

    override fun getUsers(): Observable<List<GitHubUser>> =
        gitHubStorage
            .getUserDao()
            .getUsers()

    override fun getUserByLogin(login: String): Observable<GitHubUser> =
        gitHubStorage
            .getUserDao()
            .getUserByLogin(login)

}