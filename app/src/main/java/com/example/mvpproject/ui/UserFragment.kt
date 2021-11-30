package com.example.mvpproject.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import com.example.mvpproject.R.layout.view_user
import com.example.mvpproject.arguments
import com.example.mvpproject.databinding.ViewUserBinding
import com.example.mvpproject.model.api.GitHubUser
import com.example.mvpproject.model.api.UserRepositories
import com.example.mvpproject.model.repository.datasource.GitHubUserRepositoryFactory
import com.example.mvpproject.presenters.UserPresenter
import com.example.mvpproject.ui.adapters.RepositoriesListAdapter


class UserFragment : MvpAppCompatFragment(view_user), UserView {

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
            userRepository = GitHubUserRepositoryFactory.create()
        )
    }

    private var repositoriesAdapter: RepositoriesListAdapter? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repositoriesAdapter = RepositoriesListAdapter()
    }


    private val viewBinding: ViewUserBinding by viewBinding()
    private val c = RepositoriesListAdapter()

    override fun showUser(user: GitHubUser) {
        viewBinding.userLogin.text = user.login
        Glide.with(viewBinding.userAvatarIv)
            .load(user.avatar)
            .into(viewBinding.userAvatarIv)
    }

    override fun showRepositories(repositories: List<UserRepositories>) {
        with(viewBinding.userRepositories) {
            adapter = repositoriesAdapter
        }
        repositoriesAdapter
            ?.submitList(repositories)
    }

    override fun showError(error: Throwable) {
        Toast.makeText(context, "Error : $error", Toast.LENGTH_LONG).show()
    }

}