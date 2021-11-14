package com.example.mvpproject.ui

import android.os.Bundle
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mvpproject.App
import com.example.mvpproject.R
import com.example.mvpproject.databinding.ActivityMainBinding
import com.example.mvpproject.presenters.MainPresenter
import com.example.mvpproject.ui.navigation.CustomNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    private val navigator = CustomNavigator(this, R.id.container)
    private val presenter by moxyPresenter { MainPresenter(App.router, AndroidScreens()) }
    private val viewBinding: ActivityMainBinding by viewBinding(createMethod = CreateMethod.INFLATE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backClicked()
    }
}