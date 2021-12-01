package com.example.mvpproject.ui

import android.content.Intent
import android.os.Bundle
import com.example.mvpproject.ui.navigation.CustomNavigator
import com.example.mvpproject.ui.navigation.CustomRouter
import com.example.mvpproject.ui.presentation.AbsActivity
import com.github.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

class MainActivity : AbsActivity() {

    private val navigator = CustomNavigator(activity = this, android.R.id.content)

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: CustomRouter

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        router.openDeepLink(intent?.data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            router.newRootScreen(UsersScreen)
            router.openDeepLink(intent?.data)
        }
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

}