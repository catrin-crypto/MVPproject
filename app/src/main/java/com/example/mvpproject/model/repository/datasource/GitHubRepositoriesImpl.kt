package com.example.mvpproject.model.repository.datasource

import com.example.mvpproject.model.api.GitHubApi
import com.example.mvpproject.model.api.UserRepositories
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GitHubRepositoryDataSourceImpl
    @Inject constructor(
    private val gitHubApi: GitHubApi
): GitHubRepositoryDataSource {

    override fun getUserRepositories(userId: String): Single<List<UserRepositories>> =
        gitHubApi
            .fetchUserRepositories(userId)

}