package com.example.mirunime.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mirunime.model.User


@Dao
interface UserDao {
    @Query("SELECT * FROM user WHERE username = :username LIMIT 1")
    fun getUser(username:String): User?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun addUser(user: User): Long
}