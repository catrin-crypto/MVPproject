package com.example.mvpproject.ui.adapters

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.example.mvpproject.model.api.UserRepositories

object RepositoriesListDiff : DiffUtil.ItemCallback<UserRepositories>() {

    private val payload = Any()

    override fun areItemsTheSame(oldItem: UserRepositories, newItem: UserRepositories): Boolean {
        return oldItem.name == newItem.name
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: UserRepositories, newItem: UserRepositories): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: UserRepositories, newItem: UserRepositories) = payload

}
