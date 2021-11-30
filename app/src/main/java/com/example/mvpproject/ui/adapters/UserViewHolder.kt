package com.example.mvpproject.ui.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.mvpproject.click
import com.example.mvpproject.databinding.RvItemBinding
import com.example.mvpproject.model.api.GitHubUser

class UserViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val viewBinding: RvItemBinding by viewBinding()

    fun bind(user: GitHubUser, delegate: UsersAdapter.Delegate?) {
        with(viewBinding) {
            loginTv.text = user.login
            Glide.with(this.avatarIv)
                .load(user.avatar)
                .into(avatarIv)
             root.click { delegate?.onUserPicked(user) }
        }
    }

}