package com.example.mvpproject.model.repository

import com.example.mvpproject.model.entities.GitHubApi
import com.example.mvpproject.model.entities.GitHubApiFactory
import com.example.mvpproject.model.entities.GitHubUser
import com.example.mvpproject.model.entities.UserRepositories
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single


class GithubUsersRepoImpl(private val gitHubApi: GitHubApi = GitHubApiFactory.create()) : UsersRepository{

    override fun getUsers(): Single<List<GitHubUser>> =
        gitHubApi
            .fetchUsers()

    override fun getUserByLogin(userId: String): Maybe<GitHubUser>  =
        gitHubApi
            .fetchUserByLogin(userId)
            .onErrorComplete()

    override fun getRepositoriesByLogin(userId: String): Maybe<List<UserRepositories>> =
        gitHubApi
            .fetchUserRepositories(userId)
            .onErrorComplete()
}