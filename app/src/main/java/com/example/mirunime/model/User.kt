package com.example.mirunime.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "user", indices = [Index(value = ["username"], unique = true)])
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var username: String,
    var email: String,
    var password: String
)
