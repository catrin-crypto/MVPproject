package com.example.mvpproject.presenters

import com.example.mvpproject.model.repository.datasource.UsersRepository
import com.example.mvpproject.ui.UserView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class UserPresenter(
    private val userLogin: String,
    private val userRepository: UsersRepository
) : MvpPresenter<UserView>() {

    private val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        disposables +=
            Observable.merge(
                userRepository
                    .getUser(userLogin)
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext(viewState::showUser),
                userRepository
                    .getUserRepositories(userLogin)
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext(viewState::showRepositories)
            )
                .subscribeOn(Schedulers.io())
                .subscribe({}, viewState::showError)
    }

    override fun onDestroy() {
        disposables.clear()
    }
}