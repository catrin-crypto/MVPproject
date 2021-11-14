package com.example.mvpproject.presenters

import com.example.mvpproject.ui.IScreens
import com.example.mvpproject.ui.MainView
import com.example.mvpproject.ui.navigation.CustomRouter
import moxy.MvpPresenter

class MainPresenter(val router: CustomRouter, val screens: IScreens) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }
    fun backClicked(){
        router.exit()
    }
}