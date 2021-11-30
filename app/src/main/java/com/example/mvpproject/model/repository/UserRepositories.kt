package com.example.mvpproject.model.api


import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "github_repositories")
data class UserRepositories (

    @PrimaryKey
    val id: Int,
    val login: String,
    val name: String,
    )
