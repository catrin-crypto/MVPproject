package com.example.mvpproject.ui.navigation

import android.net.Uri
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Forward

class OpenDeepLink(private val deepLink: Uri?) : CustomRouter.Command, Command {
    override fun execute(navigator: CustomNavigator) {
        when (deepLink?.authority) {
            "user" ->
                deepLink
                    .let(Uri::getLastPathSegment)
                    ?.let(::UserScreen)
                    ?.let(::Forward)
                    ?.let(navigator::applyCommand)
            else -> Unit
        }
    }
}