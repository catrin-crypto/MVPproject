package com.example.mvpproject.ui

import com.example.mvpproject.model.api.GitHubUser
import moxy.viewstate.strategy.alias.SingleState

interface UsersView : MainView{
    @SingleState
    fun showUsers(users: List<GitHubUser>)

}