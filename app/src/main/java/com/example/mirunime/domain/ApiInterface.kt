package com.example.mirunime.domain

import com.example.mirunime.model.AnimeResponse
import com.example.mirunime.model.ListAnimeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("top/anime")
    suspend fun getAnimeList(
        @Query("page")page: Int,
        @Query("sfw")sfw:Boolean = true,
        @Query("limit")limit: Int = 20
    ): Response<ListAnimeResponse>

    @GET("anime/{mal_id}")
    suspend fun getAnimeDetailById(
        @Path("mal_id")id:Int
    ):Response<AnimeResponse>

    @GET("anime")
    suspend fun searchAnime(
        @Query("q") query: String,
        @Query("page")page: Int=1,
        @Query("sfw")sfw:Boolean = true,
        ):Response<ListAnimeResponse>


}