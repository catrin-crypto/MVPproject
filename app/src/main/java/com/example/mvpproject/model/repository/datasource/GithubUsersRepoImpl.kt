package com.example.mvpproject.model.repository.datasource

import com.example.mvpproject.model.api.GitHubUser
import com.example.mvpproject.model.api.UserRepositories
import com.example.mvpproject.model.datasource.UserCacheDataSource
import com.example.mvpproject.model.datasource.UserDataSource
import io.reactivex.rxjava3.core.Observable


class GitHubUserRepositoryImpl(
    private val userDataSource: UserDataSource,
    private val userCacheDataSource: UserCacheDataSource,
    private val gitHubRepositoryDataSource: GitHubRepositoryDataSource,
    private val gitHubRepositoryCacheDataSource: GitHubRepositoryCacheDataSource,
) : UsersRepository {

    override fun getUsers(): Observable<List<GitHubUser>> =
        Observable.merge(
            userCacheDataSource
                .getUsers(),
            userDataSource
                .getUsers()
                .flatMapObservable(userCacheDataSource::retain)
        )

    override fun getUser(userId: String): Observable<GitHubUser> =
        Observable.merge(
            userCacheDataSource
                .getUserByLogin(userId),
            userDataSource
                .getUserByLogin(userId)
                .flatMapCompletable { user ->
                    userCacheDataSource
                        .retain(user)
                        .flatMapCompletable {
                            gitHubRepositoryDataSource
                                .getUserRepositories(user.login)
                                .map { repositories -> repositories.map { repository -> repository.copy(login = user.login) }}
                                .flatMapCompletable(gitHubRepositoryCacheDataSource::retain)
                        }
                }
                .toObservable()
        )

    override fun getUserRepositories(login: String): Observable<List<UserRepositories>> =
        Observable.merge(
            gitHubRepositoryCacheDataSource
                .getUserRepositories(login),
            gitHubRepositoryDataSource
                .getUserRepositories(login)
                .map { repositories -> repositories.map { repository -> repository.copy(login = login) }}
                .flatMapCompletable(gitHubRepositoryCacheDataSource::retain)
                .toObservable()
        )

}