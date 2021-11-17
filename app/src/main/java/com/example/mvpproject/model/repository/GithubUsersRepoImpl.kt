package com.example.mvpproject.model.repository

import com.example.mvpproject.model.entities.GitHubUser
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single


class GithubUsersRepoImpl : UsersRepository{
    private val users = listOf(
        GitHubUser("log1"),
        GitHubUser("log2"),
        GitHubUser("log3"),
        GitHubUser("log4"),
        GitHubUser("log5")
    )
    override fun getUsers() : Single<List<GitHubUser>> = Single.just(users)

     override fun getUserByLogin(userId: String): Maybe<GitHubUser> =
        users.firstOrNull { user -> user.login == userId }
            ?.let {Maybe.just(it)}
            ?: Maybe.empty()

}