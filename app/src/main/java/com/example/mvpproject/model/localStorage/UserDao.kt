package com.example.mvpproject.model.localStorage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvpproject.model.api.GitHubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

@Dao
interface UserDao {

    @Query("SELECT * FROM github_users")
    fun getUsers(): Observable<List<GitHubUser>>

    @Query("SELECT * FROM github_users WHERE login LIKE :login LIMIT 1")
    fun getUserByLogin(login: String): Observable<GitHubUser>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun retain(users: List<GitHubUser>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun retain(user: GitHubUser): Completable

}