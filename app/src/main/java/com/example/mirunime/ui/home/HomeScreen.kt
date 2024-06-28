package com.example.mirunime.ui.home

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.mirunime.model.Anime
import com.example.mirunime.ui.components.ItemUI
import com.example.mirunime.ui.components.TopBar
import com.example.mirunime.viewmodel.AnimeViewModel

@Composable
fun HomeScreen(
    navController: NavHostController
) {
    val viewModel = viewModel<AnimeViewModel>()
    val state = viewModel.state
    Scaffold(
        modifier = Modifier.background(Color.Transparent),
        topBar = {
            TopBar(navController = navController, "Recommended Anime")
        },
        content = { paddingValues ->
            LazyVerticalGrid(columns = GridCells.Fixed(2), Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color.Transparent),
                content = {
                    items(state.animeList.size){
                        if(it >=state.animeList.size-1 && !state.endReached && !state.isLoading){
                            viewModel.loadNextItems()
                        }
                        ItemUI(
                            itemIndex = it,
                            animeList = state.animeList,
                            navController = navController
                        )
                    }
                    item(state.isLoading){
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator(color = ProgressIndicatorDefaults.circularColor)
                        }
                        if (!state.error.isNullOrEmpty()){
                        Toast.makeText(LocalContext.current, state.error, Toast.LENGTH_SHORT).show()
                        }
                    }

                }
            )
        },
        containerColor = Color.Transparent
    )
}
