package com.example.mvpproject.ui.presenters

import com.example.mvpproject.ui.IScreens
import com.example.mvpproject.ui.MainView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class MainPresenter(val router: Router, val screens: IScreens) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }
    fun backClicked(){
        router.exit()
    }
}