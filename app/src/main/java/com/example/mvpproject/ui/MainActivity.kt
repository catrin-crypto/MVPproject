package com.example.mvpproject.ui

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvpproject.databinding.ActivityMainBinding
import com.example.mvpproject.model.repository.GithubUsersRepo
import com.example.mvpproject.ui.adapters.UsersRecyclerViewAdapter
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    private val presenter by moxyPresenter { MainPresenter(GithubUsersRepo()) }
    private var adapter: UsersRecyclerViewAdapter? = null
    private var viewBinding : ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding?.root)
    }

    override fun init() {
        viewBinding?.rvUsers?.layoutManager = LinearLayoutManager(this)
        adapter = UsersRecyclerViewAdapter(presenter.usersListPresenter)
        viewBinding?.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }
}