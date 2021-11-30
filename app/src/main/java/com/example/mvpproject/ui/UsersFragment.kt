package com.example.mvpproject.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mvpproject.arguments
import com.example.mvpproject.App.Navigation.router
import com.example.mvpproject.R.layout.fragment_users
import com.example.mvpproject.databinding.FragmentUsersBinding
import com.example.mvpproject.model.api.GitHubUser
import com.example.mvpproject.model.repository.datasource.GitHubUserRepositoryFactory
import com.example.mvpproject.presenters.UsersPresenter
import com.example.mvpproject.scheduler.SchedulersFactory
import com.example.mvpproject.ui.adapters.UsersAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class UsersFragment: MvpAppCompatFragment(fragment_users), UsersView, UsersAdapter.Delegate {

    companion object {

        fun newInstance(): Fragment =
            UsersFragment()
                .arguments()
    }

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(
            usersRepo = GitHubUserRepositoryFactory.create(),
            router = router,
            schedulers = SchedulersFactory.create()
        )
    }

    private val viewBinding: FragmentUsersBinding by viewBinding()
    private var usersAdapter : UsersAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        usersAdapter = UsersAdapter(delegate = this)
        viewBinding.rvUsers.adapter = usersAdapter
    }

    override fun showUsers(users: List<GitHubUser>) {
        usersAdapter?.submitList(users)
    }

    override fun showError(error: Throwable) {
        Toast.makeText(requireContext(), error.message, Toast.LENGTH_LONG).show()
    }

    override fun onUserPicked(user: GitHubUser) =
        presenter.displayUser(user)

}
