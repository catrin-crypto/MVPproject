package com.example.mvpproject.ui

import com.example.mvpproject.model.entities.GitHubUser
import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState

interface UserView : MvpView {

    @SingleState
    fun showUser(user: GitHubUser)

}