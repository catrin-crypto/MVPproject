package com.example.mvpproject.presenters

import com.example.mvpproject.App
import com.example.mvpproject.App.Navigation.router
import com.example.mvpproject.model.entities.GitHubUser
import com.example.mvpproject.model.repository.GithubUsersRepo
import com.example.mvpproject.ui.IUserListPresenter
import com.example.mvpproject.ui.UserFragment
import com.example.mvpproject.ui.UserItemView
import com.example.mvpproject.ui.UsersView
import com.example.mvpproject.ui.navigation.CustomRouter
import com.example.mvpproject.ui.navigation.UserScreen
import moxy.MvpPresenter

class UsersPresenter(val usersRepo: GithubUsersRepo, val router: CustomRouter) : MvpPresenter<UsersView>(){
    class UsersListPresenter : IUserListPresenter{
        val users = mutableListOf<GitHubUser>()
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
            //usersListPresenter.bindView(itemView)
            displayUser(usersListPresenter.users[itemView.pos])
        }
    }
    fun loadData(){
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun displayUser(user: GitHubUser) =
        router.navigateTo(UserScreen(user.login))


    fun backPressed() : Boolean {
        router.exit()
        return true
    }
}