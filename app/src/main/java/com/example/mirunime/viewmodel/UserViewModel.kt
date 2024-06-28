package com.example.mirunime.viewmodel

import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mirunime.data.DatabaseBuilder
import com.example.mirunime.model.User
import com.example.mirunime.repository.UserRepository
import com.toxicbakery.bcrypt.Bcrypt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Base64

class UserViewModel :ViewModel() {
    private val repository = UserRepository()

    private val _userInsertStatus = MutableLiveData<Result<Boolean>>()
    val userInsertStatus: LiveData<Result<Boolean>> = _userInsertStatus


    fun register(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val existingUser = repository.getUserByName(username)
            if (existingUser != null) {
                _userInsertStatus.postValue(Result.failure(Exception("Username already exists")))
            } else {

                val hashedPassword = Bcrypt.hash(password, 12).toString(Charsets.UTF_8)

                val result = repository.addUser(User(username = username, password =hashedPassword))
                _userInsertStatus.postValue(Result.success(result > 0))

            }
        }
    }

    fun login(username: String, password: String, onResult: (User?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val user = repository.getUserByName(username)
            if (user != null && Bcrypt.verify(password, user.password.toByteArray())) {
                withContext(Dispatchers.Main) {
                    onResult(user)
                }
            } else {
                withContext(Dispatchers.Main) {
                    onResult(null)
                }
            }
        }
    }
}
