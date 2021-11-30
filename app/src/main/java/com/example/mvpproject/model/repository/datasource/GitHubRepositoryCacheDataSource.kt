package com.example.mvpproject.model.repository.datasource

import com.example.mvpproject.model.api.UserRepositories
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

interface GitHubRepositoryCacheDataSource {

    fun getUserRepositories(login: String): Observable<List<UserRepositories>>
    fun retain(repositories: List<UserRepositories>): Completable

}