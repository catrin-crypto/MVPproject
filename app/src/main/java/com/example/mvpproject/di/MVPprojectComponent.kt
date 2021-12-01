package com.example.mvpproject.di

import android.content.Context
import com.example.mvpproject.MVPproject
import com.example.mvpproject.scheduler.Schedulers
import com.example.mvpproject.ui.navigation.CustomRouter
import com.github.terrakok.cicerone.NavigatorHolder
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, UserModule::class])
interface MVPprojectComponent: AndroidInjector<MVPproject> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun withContext(context: Context): Builder

        @BindsInstance
        fun withSchedulers(schedulers: Schedulers): Builder

        @BindsInstance
        fun withRouter(router: CustomRouter): Builder

        @BindsInstance
        fun withNavigatorHolder(navigatorHolder: NavigatorHolder): Builder

        fun build(): MVPprojectComponent

    }
}