package com.example.mirunime.repository

import com.example.mirunime.data.DatabaseBuilder
import com.example.mirunime.data.local.UserDao
import com.example.mirunime.model.User

class UserRepository {

    suspend fun getUserByName(name: String): User? {

        return DatabaseBuilder.userDatabase.getUserDao().getUser(name)
    }

    suspend fun addUser(user:User): Long{

        return DatabaseBuilder.userDatabase.getUserDao().addUser(user)
    }
}