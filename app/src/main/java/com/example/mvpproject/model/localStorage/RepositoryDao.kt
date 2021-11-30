package com.example.mvpproject.model.localStorage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvpproject.model.api.UserRepositories
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

@Dao
interface RepositoryDao {

    @Query("SELECT * FROM github_repositories WHERE login LIKE :login")
    fun getRepositoriesByLogin(login: String): Observable<List<UserRepositories>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun retain(users: List<UserRepositories>): Completable

}