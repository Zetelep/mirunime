import com.example.mirunime.model.Anime
import com.example.mirunime.model.AnimeResponse
import com.example.mirunime.model.Page

data class ScreenState(
    val animeList: List<Anime> = emptyList(),
    val page: Int = 1,
    val detailData: AnimeResponse = AnimeResponse(Anime()),
    val endReached: Boolean = Page().has_next_page,
    val error: String? = null,
    val isLoading: Boolean = false
)