package com.example.mvpproject.model.repository.datasource

import com.example.mvpproject.model.api.UserRepositories
import com.example.mvpproject.model.localStorage.Storage
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

class GitHubRepositoryCacheDataSourceImpl(
    private val gitHubStorage: Storage
): GitHubRepositoryCacheDataSource {

    override fun getUserRepositories(login: String): Observable<List<UserRepositories>> =
        gitHubStorage
            .getRepositoryDao()
            .getRepositoriesByLogin(login)

    override fun retain(repositories: List<UserRepositories>): Completable =
        gitHubStorage
            .getRepositoryDao()
            .retain(repositories)

}