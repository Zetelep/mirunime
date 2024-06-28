package com.example.mirunime.paging


interface Pagination<Page,AnimeList> {
    suspend fun loadNextPage()

    fun reset()
}