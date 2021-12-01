package com.example.mvpproject.ui

import com.example.mvpproject.model.api.GitHubUser
import com.example.mvpproject.model.api.UserRepositories
import moxy.viewstate.strategy.alias.SingleState

interface UserView : MainView {

    @SingleState
    fun showUser(user: GitHubUser)
    @SingleState
    fun showRepositories(repositories : List<UserRepositories>)

}