package com.example.mirunime.data

import android.app.Application
import androidx.room.Room
import com.example.mirunime.data.local.UserDatabase

class DatabaseBuilder : Application() {
        companion object{
            lateinit var userDatabase: UserDatabase
        }

    override fun onCreate() {
        super.onCreate()
        userDatabase = Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java,
            UserDatabase.NAME
        ).build()
    }
}