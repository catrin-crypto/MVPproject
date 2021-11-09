package com.example.mvpproject.ui.presenters

import com.example.mvpproject.model.entities.GithubUser
import com.example.mvpproject.model.repository.GithubUsersRepo
import com.example.mvpproject.ui.IUserListPresenter
import com.example.mvpproject.ui.UserItemView
import com.example.mvpproject.ui.UsersView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UsersPresenter(val usersRepo: GithubUsersRepo, val router: Router) : MvpPresenter<UsersView>(){
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
    fun backPressed() : Boolean {
        router.exit()
        return true
    }
}