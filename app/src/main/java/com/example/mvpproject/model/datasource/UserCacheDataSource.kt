package com.example.mvpproject.model.datasource

import com.example.mvpproject.model.api.GitHubUser
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface UserCacheDataSource {

    fun getUsers(): Observable<List<GitHubUser>>
    fun getUserByLogin(login: String): Observable<GitHubUser>
    fun retain(users: List<GitHubUser>): Observable<List<GitHubUser>>
    fun retain(user: GitHubUser): Single<GitHubUser>

}