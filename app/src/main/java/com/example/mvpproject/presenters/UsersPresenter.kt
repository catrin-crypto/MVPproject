package com.example.mvpproject.presenters

import com.example.mvpproject.model.entities.GitHubUser
import com.example.mvpproject.model.repository.UsersRepository
import com.example.mvpproject.ui.IUserListPresenter
import com.example.mvpproject.ui.UserItemView
import com.example.mvpproject.ui.UsersView
import com.example.mvpproject.ui.navigation.CustomRouter
import com.example.mvpproject.ui.navigation.UserScreen
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import moxy.MvpPresenter

class UsersPresenter(private val usersRepo: UsersRepository,
                     private val router: CustomRouter,
                     private val schedulers : com.example.mvpproject.scheduler.Schedulers) : MvpPresenter<UsersView>(){
    class UsersListPresenter : IUserListPresenter{
        val users = mutableListOf<GitHubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null
        override fun getCount() = users.size
        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login ?: "<No login>")
            view.setAvatar(user.avatar ?: "")
        }
    }

    private val disposables = CompositeDisposable()
    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        viewState.init()
        disposables +=
            usersRepo
                .getUsers()
                .observeOn(schedulers.background())
                .subscribe({ usersList ->
                    usersListPresenter.users.addAll(usersList)
                    viewState.showUsers()
                },{
                        error -> viewState.showError(error)})

//                )



//        super.onFirstViewAttach()
//        viewState.init()
//        loadData()

        usersListPresenter.itemClickListener = {itemView ->
            displayUser(usersListPresenter.users[itemView.pos])
        }
        //viewState.showUsers()
    }

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