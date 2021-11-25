package com.example.mvpproject.model.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserRepositories (
    @SerializedName("url")
    val url : String,
    @SerializedName("git_url")
    val gitUrl : String,
    @SerializedName("ssh_url")
    val sshUrl : String,
    ) : Parcelable
