package com.example.mvpproject.model.repository.datasource

import com.example.mvpproject.model.api.GitHubUser
import com.example.mvpproject.model.api.UserRepositories
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface UsersRepository {
    fun getUsers(): Observable<List<GitHubUser>>

    fun getUser(userId: String): Observable<GitHubUser>

    fun getUserRepositories(login: String): Observable<List<UserRepositories>>

}