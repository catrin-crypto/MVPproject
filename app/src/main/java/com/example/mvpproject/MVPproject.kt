package com.example.mvpproject

import com.example.mvpproject.di.DaggerMVPprojectComponent
import com.example.mvpproject.scheduler.SchedulersFactory
import com.example.mvpproject.ui.navigation.CustomRouter
import com.github.terrakok.cicerone.Cicerone
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MVPproject : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerMVPprojectComponent
            .builder()
            .withContext(applicationContext)
            .withSchedulers(SchedulersFactory.create())
            .apply {
                val cicerone = Cicerone.create(CustomRouter())
                withRouter(cicerone.router)
                withNavigatorHolder(cicerone.getNavigatorHolder())
            }
            .build()

}