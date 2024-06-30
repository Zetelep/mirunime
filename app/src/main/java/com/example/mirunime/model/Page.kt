package com.example.mirunime.model

data class Page(
    val current_page: String = "",
    val items: Items = Items(),
    val has_next_page: Boolean = false
)