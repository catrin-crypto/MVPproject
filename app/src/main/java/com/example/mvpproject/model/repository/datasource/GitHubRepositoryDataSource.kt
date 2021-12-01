package com.example.mvpproject.model.repository.datasource

import com.example.mvpproject.model.api.UserRepositories
import io.reactivex.rxjava3.core.Single

interface GitHubRepositoryDataSource {

    fun getUserRepositories(userId: String): Single<List<UserRepositories>>

}