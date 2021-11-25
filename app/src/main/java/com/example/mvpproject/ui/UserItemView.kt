package com.example.mvpproject.ui

import android.net.Uri

interface UserItemView : IItemView {
    fun setLogin(text : String)
    fun setAvatar(url: String)
}