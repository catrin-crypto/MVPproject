package com.example.mvpproject.presenters

import com.example.mvpproject.model.repository.GithubUsersRepoImpl
import com.example.mvpproject.ui.UserView
import moxy.MvpPresenter

class UserPresenter(
    private val userLogin: String,
    private val userRepository: GithubUsersRepoImpl
) : MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        userRepository
            .getUserByLogin(userLogin)
            .subscribe(viewState::showUser)
    }

}