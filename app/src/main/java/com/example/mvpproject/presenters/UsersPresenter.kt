package com.example.mvpproject.presenters

import com.example.mvpproject.model.api.GitHubUser
import com.example.mvpproject.model.repository.datasource.UsersRepository
import com.example.mvpproject.ui.UsersView
import com.example.mvpproject.ui.navigation.UserScreen
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import moxy.MvpPresenter

class UsersPresenter(private val usersRepo: UsersRepository,
                     private val router: Router,
                     private val schedulers : com.example.mvpproject.scheduler.Schedulers) : MvpPresenter<UsersView>() {


    private val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        disposables +=
            usersRepo
                .getUsers()
                .observeOn(schedulers.background())
                .observeOn(schedulers.main())
                .subscribeOn(schedulers.background())
                .subscribe(
                    viewState::showUsers,
                    viewState::showError
                )
    }

    fun displayUser(user: GitHubUser) =
        router.navigateTo(UserScreen(user.login))

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}