package com.example.mvpproject.ui

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import com.example.mvpproject.R.layout.view_user
import com.example.mvpproject.arguments
import com.example.mvpproject.databinding.ViewUserBinding
import com.example.mvpproject.model.entities.GitHubUser
import com.example.mvpproject.model.repository.GithubUsersRepoImpl
import com.example.mvpproject.presenters.UserPresenter


class UserFragment: MvpAppCompatFragment(view_user), UserView {

    companion object {

        private const val ARG_USER_LOGIN = "arg_user_login"

        fun newInstance(userId: String): Fragment =
            UserFragment()
                .arguments(ARG_USER_LOGIN to userId)

    }

    private val userLogin: String by lazy {
        arguments?.getString(ARG_USER_LOGIN).orEmpty()
    }

    @Suppress("unused")
    private val presenter: UserPresenter by moxyPresenter {
        UserPresenter(
            userLogin = userLogin,
            userRepository = GithubUsersRepoImpl()
        )
    }

    private val viewBinding: ViewUserBinding by viewBinding()

    override fun showUser(user: GitHubUser) {
        viewBinding.userLogin.text = user.login
    }

}