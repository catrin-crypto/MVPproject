package com.example.mvpproject.ui

import com.example.mvpproject.model.entities.GithubUser
import com.example.mvpproject.model.repository.GithubUsersRepo
import moxy.MvpPresenter

class MainPresenter(val usersRepo: GithubUsersRepo) : MvpPresenter<MainView>() {
    class UsersListPresenter : IUserListPresenter{
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null
        override fun getCount() = users.size
        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = {itemView ->
            TODO()
        }
    }
    fun loadData(){
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }
}