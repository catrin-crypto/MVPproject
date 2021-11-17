package com.example.mvpproject.model.repository

import com.example.mvpproject.model.entities.GitHubUser
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface UsersRepository {
    fun getUsers() : Single<List<GitHubUser>>
    fun getUserByLogin(userId : String) : Maybe<GitHubUser>
}