package com.example.mvpproject.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mvpproject.arguments
import com.example.mvpproject.R.layout.fragment_users
import com.example.mvpproject.databinding.FragmentUsersBinding
import com.example.mvpproject.model.api.GitHubUser
import com.example.mvpproject.model.repository.datasource.UsersRepository
import com.example.mvpproject.presenters.UsersPresenter
import com.example.mvpproject.scheduler.Schedulers
import com.example.mvpproject.ui.adapters.UsersAdapter
import com.example.mvpproject.ui.presentation.AbsFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject


class UsersFragment: AbsFragment(fragment_users), UsersView, UsersAdapter.Delegate {

    companion object {

        fun newInstance(): Fragment =
            UsersFragment()
                .arguments()
    }

    @Inject
    lateinit var gitHubUserRepository: UsersRepository

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(
            router = router,
            usersRepo = gitHubUserRepository,
            schedulers = schedulers
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
