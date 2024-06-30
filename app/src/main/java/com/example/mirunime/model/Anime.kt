package com.example.mirunime.model

data class Anime(
    val mal_id: Int = 0,
    val images: AnimeImages = AnimeImages(jpg = AnimeImage(
        image_url = "",
        small_image_url = "",
        large_image_url = ""
    )),
    val trailer: Trailer = Trailer(youtube_id = null),
    val title: String = "",
    val title_english: String = "",
    val title_japanese: String = "",
    val type: String = "",
    val source: String = "",
    val episodes: String = "",
    val status: String = "",
    val rating: String = "",
    val year: String = "",
    val genres: List<Genre> = emptyList(),
    val synopsis: String = "",
    val score: String = ""
)