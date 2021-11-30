package com.example.mvpproject.ui.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mvpproject.databinding.ViewUserRepositoryBinding
import com.example.mvpproject.model.api.UserRepositories

class RepositoriesListViewHolder (view: View): RecyclerView.ViewHolder(view) {

    private val viewBinding: ViewUserRepositoryBinding by viewBinding()

    fun bind(model: UserRepositories) {
        with(viewBinding) {
            userRepository.text = model.name
        }
    }

}


