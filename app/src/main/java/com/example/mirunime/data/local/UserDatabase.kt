package com.example.mirunime.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mirunime.model.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    companion object{
        const val NAME = "mirunime_user_db"
    }

    abstract fun getUserDao():UserDao
}