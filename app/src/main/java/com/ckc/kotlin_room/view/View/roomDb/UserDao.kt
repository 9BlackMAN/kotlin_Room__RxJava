package com.ckc.kotlin_room.view.View.roomDb

import androidx.room.*
import com.ckc.kotlin_room.view.View.model.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface UserDao {

    @Query("SELECT * FROM User ")
    fun getall() : Flowable<List<User>>

    @Insert
    fun insertAll(user: User) : Completable

    @Delete
    fun delete(user: User) : Completable

    @Update
    fun update(user: User) : Completable
}