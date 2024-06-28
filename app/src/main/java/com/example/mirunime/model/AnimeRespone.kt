package com.example.mirunime.model

data class ListAnimeResponse(
    val data: List<Anime>,
    val pagination:page
)
data class AnimeResponse (
    val data: Anime
)

data class page(
  val current_page: String = "",
  val items: items = items(),
  val has_next_page: Boolean = false
)

data class items(
    val count: Int =0,
    val total:Int =0,
    val per_page:Int =0
)

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

data class AnimeImage(
    val image_url: String = "",
    val small_image_url: String = "",
    val large_image_url: String = ""
)

data class AnimeImages(
    val jpg: AnimeImage = AnimeImage()
)

data class Trailer(
    val youtube_id: String? = null
)

data class Genre(
    val name: String = ""
)



