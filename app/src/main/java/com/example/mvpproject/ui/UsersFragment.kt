package com.example.mvpproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvpproject.App
import com.example.mvpproject.databinding.FragmentUsersBinding
import com.example.mvpproject.model.repository.GithubUsersRepo
import com.example.mvpproject.ui.adapters.UsersRecyclerViewAdapter
import com.example.mvpproject.presenters.UsersPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(GithubUsersRepo(), App.Navigation.router)
    }
    var adapter: UsersRecyclerViewAdapter? = null
    private var viewUsersBinding: FragmentUsersBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentUsersBinding.inflate(inflater, container, false).also { viewUsersBinding = it }.root

    override fun onDestroyView() {
        super.onDestroyView()
        viewUsersBinding = null
    }

    override fun init() {
        viewUsersBinding?.rvUsers?.layoutManager = LinearLayoutManager(context)
        adapter = UsersRecyclerViewAdapter(presenter.usersListPresenter)
        viewUsersBinding?.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}