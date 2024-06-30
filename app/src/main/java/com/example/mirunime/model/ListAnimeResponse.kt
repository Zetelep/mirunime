package com.example.mirunime.model

data class ListAnimeResponse(
    val data: List<Anime>,
    val pagination:Page
)