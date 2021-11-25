package com.example.mvpproject.ui

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view : V)
    fun getCount() : Int
}
