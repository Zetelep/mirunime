package com.example.mirunime.repository

import com.example.mirunime.model.Anime
import com.example.mirunime.model.AnimeResponse
import com.example.mirunime.model.ListAnimeResponse
import com.example.mirunime.utils.RetrofitInstance
import retrofit2.Response

class AnimeRepository {

    suspend fun getAnimeList(page:Int): Response<ListAnimeResponse>{
        return RetrofitInstance.api.getAnimeList(page)
    }

    suspend fun getAnimeDetailById(id:Int):Response<AnimeResponse>{
        return RetrofitInstance.api.getAnimeDetailById(id)
    }

    suspend fun searchAnime(name:String):Response<ListAnimeResponse>{
        return RetrofitInstance.api.searchAnime(name)
    }

}