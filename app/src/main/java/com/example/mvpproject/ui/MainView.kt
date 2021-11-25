package com.example.mvpproject.ui

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.SingleState

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView{
    @SingleState
    fun showError(error: Throwable)
}