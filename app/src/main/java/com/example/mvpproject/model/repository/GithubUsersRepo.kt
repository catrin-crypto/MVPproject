package com.example.mvpproject.model.repository

import com.example.mvpproject.model.entities.GitHubUser

class GithubUsersRepo {
    private val users = listOf(
        GitHubUser("log1"),
        GitHubUser("log2"),
        GitHubUser("log3"),
        GitHubUser("log4"),
        GitHubUser("log5")
    )
    fun getUsers() : List<GitHubUser>{
        return users
    }
     fun getUserByLogin(userId: String): GitHubUser? =
        users.firstOrNull { user -> user.login == userId }

}