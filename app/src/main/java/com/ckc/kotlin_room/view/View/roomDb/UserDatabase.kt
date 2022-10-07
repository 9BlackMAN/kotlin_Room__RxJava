package com.ckc.kotlin_room.view.View.roomDb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ckc.kotlin_room.view.View.model.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao
}