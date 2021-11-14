package com.example.mvpproject.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvpproject.click
import com.example.mvpproject.databinding.RvItemBinding
import com.example.mvpproject.ui.IUserListPresenter
import com.example.mvpproject.ui.UserItemView

class UsersRecyclerViewAdapter(val presenter: IUserListPresenter) :
    RecyclerView.Adapter<UsersRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        RvItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    ).apply {
        itemView.setOnClickListener {
            presenter.itemClickListener?.invoke(this)
        }
    }

    inner class ViewHolder(val viewBinding: RvItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root),
        UserItemView {
        override var pos: Int = -1
        override fun setLogin(text: String) = with(viewBinding) {
            loginTv.text = text

        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    override fun getItemCount() = presenter.getCount()
}