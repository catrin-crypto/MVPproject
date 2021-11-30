package com.example.mvpproject.ui

import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState

interface MainView : MvpView{
    @SingleState
    fun showError(error: Throwable)
}