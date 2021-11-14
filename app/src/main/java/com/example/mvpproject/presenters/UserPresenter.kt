package com.example.mvpproject.presenters

import com.example.mvpproject.model.repository.GithubUsersRepo
import com.example.mvpproject.ui.UserView
import moxy.MvpPresenter

class UserPresenter(
    private val userLogin: String,
    private val userRepository: GithubUsersRepo
) : MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        userRepository
            .getUserByLogin(userLogin)
            ?.let(viewState::showUser)
    }

}