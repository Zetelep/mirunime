package com.example.mirunime.ui.detail

import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.VideoView
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mirunime.viewmodel.AnimeViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.mirunime.R
import com.example.mirunime.model.Anime
import com.example.mirunime.model.AnimeResponse
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun DetailScreen(
    id:Int,
    navController: NavHostController
){
    val viewModel = viewModel<AnimeViewModel>()
     viewModel.getAnimeDetailById(id)
    val  state = viewModel.state

    val details = state.detailData

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        BackGroundPoster(details = details)
        Column(
            Modifier
                .padding(start = 20.dp, end = 20.dp, bottom = 50.dp)
                .align(Alignment.BottomCenter)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            ForegroundPoster(details = details)
            Text(
                text = details.data.title,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 38.sp,
                color = Color.White,
                lineHeight = 40.sp,
                textAlign = TextAlign.Center
            )
            Rating(details = details, modifier = Modifier)

            //find a better way
            //use data mapper
            if (details.data.trailer.youtube_id!=null) {
                TrailerView(
                    urlId = details.data.trailer.youtube_id,
                    lifecycleOwner = LocalLifecycleOwner.current
                )
            }

            TextBuilder(icon = Icons.Filled.Info, title = "Synopsis:", bodyText = details.data.synopsis)
            TextBuilder(icon = Icons.Filled.Check, title = "Genre:", bodyText = details.data.genres.joinToString(", "){it.name})
        }
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(30.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.leftarrow),
                contentDescription = "Back",
                tint = Color.White
            )
        }
    }
}

@Composable
fun ForegroundPoster(details: AnimeResponse) {
    Box(
        modifier = Modifier
            .height(500.dp)
            .width(500.dp)
            .padding(top = 10.dp)
            .clip(RoundedCornerShape(16.dp)),
        contentAlignment = Alignment.TopCenter
    ) {
        AsyncImage(
            model = details.data.images.jpg.image_url, contentDescription = details.data.title,
            Modifier
                .width(750.dp)
                .clip(RoundedCornerShape(16.dp))
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .width(100.dp)
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color.Transparent,
                            Color(0xB91A1B1B),
                        )
                    ), shape = RoundedCornerShape(16.dp)
                )
        )
    }
}

@Composable
fun BackGroundPoster(details: AnimeResponse) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
    ) {
        AsyncImage(
            model = details.data.images.jpg.large_image_url, contentDescription = details.data.title,
            modifier = Modifier
                .fillMaxWidth()
                .alpha(0.6f)
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color.DarkGray
                        )
                    ),
                    shape = RoundedCornerShape(16.dp)
                )
        )
    }

}


@Composable
fun TextBuilder(icon: ImageVector, title: String, bodyText: String) {
    Row {
        Icon(
            imageVector = icon,
            contentDescription = "Person",
            tint = Color.White
        )
        Text(
            text = title,
            Modifier.padding(start = 10.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
    Text(text = bodyText, color = Color.White)
}

@Composable
fun Rating(details: AnimeResponse, modifier: Modifier) {
    Column(modifier.fillMaxWidth()) {
        Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
            Icon(imageVector = Icons.Filled.Warning, contentDescription = "", tint = Color.White)
            Text(
                text = details.data.rating,
                modifier.padding(start = 6.dp),
                color = Color.White
            )

        }
        Spacer(modifier = modifier.width(25.dp))

        Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "",
                tint = Color.White
            )
            Text(
                text = details.data.score,
                modifier.padding(start = 6.dp),
                color = Color.White
            )
        }
        Spacer(modifier = modifier.width(25.dp))

        Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
            Icon(imageVector = Icons.Filled.Info, contentDescription = "", tint = Color.White)
            Text(
                text = details.data.type,
                modifier.padding(start = 6.dp),
                color = Color.White
            )
        }
        Spacer(modifier = modifier.width(25.dp))

    }
}
@Composable
fun TrailerView(urlId: String,lifecycleOwner: LifecycleOwner) {
    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp)),
        factory = { context ->
            YouTubePlayerView(context = context).apply {
                lifecycleOwner.lifecycle.addObserver(this)

                addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.cueVideo(urlId, 0f)
                    }
                })
            }
        })

}