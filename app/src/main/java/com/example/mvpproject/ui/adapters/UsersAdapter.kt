package com.example.mvpproject.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.mvpproject.model.api.GitHubUser
import com.example.mvpproject.R.layout.rv_item
class UsersAdapter(private val delegate: Delegate?): ListAdapter<GitHubUser, UserViewHolder>(UserDiff) {

    interface Delegate {
        fun onUserPicked(user: GitHubUser)
    }

    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): UserViewHolder =
        UserViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(rv_item, parent, false)
        )

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) =
     holder.bind(getItem(position), delegate)

}