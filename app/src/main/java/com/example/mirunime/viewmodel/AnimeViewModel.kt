package com.example.mirunime.viewmodel

import ScreenState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mirunime.paging.PaginationFactory
import com.example.mirunime.repository.AnimeRepository
import kotlinx.coroutines.launch

class AnimeViewModel: ViewModel(){

    private val repository = AnimeRepository()
    var state by mutableStateOf(ScreenState())

    private val pagination = PaginationFactory(
        initialPage = state.page,
        onLoadUpdated = {
            state = state.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            repository.getAnimeList(nextPage)
        },
        getNextKey = {
            state.page +1
        },
        onError = {
            state = state.copy(error = it?.localizedMessage)
        },
        onSuccess = { newAnimeList, newPage ->
            state = state.copy(
                animeList = state.animeList + newAnimeList.data,
                page = newPage,
                endReached = state.endReached
            )
        }
    )
    init {
        loadNextItems()
    }

//      Without Pagination
//    init {
//        viewModelScope.launch {
//            val response = repository.getAnimeList(state.page)
//            state = state.copy(
//                animeList = response.body()!!.data
//            )
//        }
//    }

    fun loadNextItems(){
        viewModelScope.launch {
            pagination.loadNextPage()
        }
    }

    fun getAnimeDetailById(id: Int){
        viewModelScope.launch {
            try {
                val response = repository.getAnimeDetailById(id = id)
                if (response.isSuccessful){
                state = state.copy(
                    detailData = response.body()!!
                )
                }
            }catch (e:Exception){
                state = state.copy(
                    error = e.message
                )
            }
        }
    }
}
