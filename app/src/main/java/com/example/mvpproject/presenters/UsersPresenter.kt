package com.example.mvpproject.presenters

import com.example.mvpproject.model.entities.GitHubUser
import com.example.mvpproject.model.repository.GithubUsersRepoImpl
import com.example.mvpproject.ui.IUserListPresenter
import com.example.mvpproject.ui.UserItemView
import com.example.mvpproject.ui.UsersView
import com.example.mvpproject.ui.navigation.CustomRouter
import com.example.mvpproject.ui.navigation.UserScreen
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

class UsersPresenter(private val usersRepo: GithubUsersRepoImpl, private val router: CustomRouter) : MvpPresenter<UsersView>(){
    class UsersListPresenter : IUserListPresenter{
        val users = mutableListOf<GitHubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null
        override fun getCount() = users.size
        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)

        }
    }

    private val disposables = CompositeDisposable()
    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        viewState.init()
        disposables.add(
            usersRepo
                .getUsers()
                .subscribe(
                    usersListPresenter.users::addAll
                    //viewState::showUsers
                )
        )


//        super.onFirstViewAttach()
//        viewState.init()
//        loadData()

        usersListPresenter.itemClickListener = {itemView ->
            //usersListPresenter.bindView(itemView)
            displayUser(usersListPresenter.users[itemView.pos])
        }
        viewState.showUsers()
    }
//    fun loadData(){
//
//       usersListPresenter.users.addAll(usersRepo
//           .getUsers().)
//        viewState.updateList()
//    }

    fun displayUser(user: GitHubUser) =
        router.navigateTo(UserScreen(user.login))


    fun backPressed() : Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}