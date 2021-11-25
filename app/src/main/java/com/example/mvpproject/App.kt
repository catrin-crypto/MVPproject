package com.example.mvpproject

import android.app.Application
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.mvpproject.ui.navigation.CustomRouter
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class App: Application() {

    companion object Navigation {

        private val cicerone: Cicerone<CustomRouter> by lazy {
            Cicerone.create(CustomRouter())
        }

        val navigatorHolder = cicerone.getNavigatorHolder()
        val router = cicerone.router

    }

}

fun View.click(click: () -> Unit) = setOnClickListener { click() }

fun Fragment.arguments(vararg arguments: Pair<String, Any>): Fragment {
    this.arguments = bundleOf(*arguments)
    return this
}