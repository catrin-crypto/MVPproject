package com.example.mvpproject.model.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUser(
    val login : String
) : Parcelable
