package com.example.mvpproject.model.datasource

import com.example.mvpproject.model.api.GitHubUser
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

interface UserDataSource {

    fun getUsers(): Single<List<GitHubUser>>

    fun getUserByLogin(login: String): Maybe<GitHubUser>

}