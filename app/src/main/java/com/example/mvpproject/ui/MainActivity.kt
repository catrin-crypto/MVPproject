package com.example.mvpproject.ui

import android.content.Intent
import android.os.Bundle
import com.example.mvpproject.App
import com.example.mvpproject.App.Navigation.router
import com.example.mvpproject.ui.navigation.CustomNavigator
import moxy.MvpAppCompatActivity

class MainActivity : MvpAppCompatActivity() {

    private val navigator = CustomNavigator(activity = this, android.R.id.content)
    //private val presenter by moxyPresenter { MainPresenter(App.router, AndroidScreens()) }
    // private val viewBinding: ActivityMainBinding by viewBinding(createMethod = CreateMethod.INFLATE)


    override fun onResumeFragments() {
        super.onResumeFragments()
        App.navigatorHolder.setNavigator(navigator)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        router.openDeepLink(intent?.data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.context = applicationContext
        if (savedInstanceState == null) {
            router.newRootScreen(UsersScreen)
            router.openDeepLink(intent?.data)
        }
        //setContentView(viewBinding.root)
    }

//    override fun onResumeFragments() {
//        super.onResumeFragments()
//        App.navigatorHolder.setNavigator(navigator)
//    }

    override fun onPause() {
        App.navigatorHolder.removeNavigator()
        super.onPause()
    }

//    override fun onBackPressed() {
//        supportFragmentManager.fragments.forEach {
//            if (it is BackButtonListener && it.backPressed()) {
//                return
//            }
//        }
//        presenter.backClicked()
//    }
}