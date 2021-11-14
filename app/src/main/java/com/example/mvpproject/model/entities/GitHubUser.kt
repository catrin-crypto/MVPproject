package com.example.mvpproject.model.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GitHubUser(
    val login : String
) : Parcelable
