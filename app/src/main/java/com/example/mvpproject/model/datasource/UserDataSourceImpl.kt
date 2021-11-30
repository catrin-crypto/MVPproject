package com.example.mvpproject.model.datasource

import com.example.mvpproject.model.api.GitHubApi
import com.example.mvpproject.model.api.GitHubUser
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit

class UserDataSourceImpl(
    private val gitHubApi: GitHubApi
) : UserDataSource {

    override fun getUsers(): Single<List<GitHubUser>> =
        gitHubApi
            .fetchUsers()
            .delay(2L, TimeUnit.SECONDS)

    override fun getUserByLogin(login: String): Maybe<GitHubUser> =
        gitHubApi
            .fetchUserByLogin(login)
            .onErrorComplete()

}