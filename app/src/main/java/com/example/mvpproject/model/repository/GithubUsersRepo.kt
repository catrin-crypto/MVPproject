package com.example.mvpproject.model.repository

import com.example.mvpproject.model.entities.GithubUser

class GithubUsersRepo {
    private val repositories = listOf(
        GithubUser("log1"),
        GithubUser("log2"),
        GithubUser("log3"),
        GithubUser("log4"),
        GithubUser("log5")
    )
    fun getUsers() : List<GithubUser>{
        return repositories
    }
}