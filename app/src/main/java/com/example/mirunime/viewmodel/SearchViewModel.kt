package com.example.mirunime.viewmodel

import ScreenState
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mirunime.repository.AnimeRepository
import kotlinx.coroutines.launch

class SearchViewModel:ViewModel() {

    private val repository = AnimeRepository()
    var state by mutableStateOf(ScreenState())


    fun searchAnime(name: String) {
        viewModelScope.launch {
            try {
                val response = repository.searchAnime(name)
                if (response.isSuccessful) {
                    state = state.copy(
                        animeList = response.body()!!.data
                    )
                    Log.d("searchAnime", "Anime ${state.detailData} $name")
                }
            } catch (e: Exception) {
                state = state.copy(
                    error = e.message
                )
            }
        }
    }
}