package com.example.mvpproject.model.repository

object GitHubUserRepositoryFactory {

    fun create(): UsersRepository = GithubUsersRepoImpl()
}