package com.example.mvpproject.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.mvpproject.R.layout.view_user_repository
import com.example.mvpproject.model.api.UserRepositories

class RepositoriesListAdapter : ListAdapter<UserRepositories, RepositoriesListViewHolder>(RepositoriesListDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoriesListViewHolder =
        RepositoriesListViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(view_user_repository, parent, false)
        )

    override fun onBindViewHolder(holder: RepositoriesListViewHolder, position: Int) =
        holder.bind(getItem(position))

}
