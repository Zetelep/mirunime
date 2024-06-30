package com.example.mirunime.ui.search

import ScreenState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mirunime.ui.components.ItemUI
import com.example.mirunime.ui.components.TopBar
import com.example.mirunime.viewmodel.AnimeViewModel
import com.example.mirunime.viewmodel.SearchViewModel

@Composable
fun SearchScreen(
    navController: NavHostController
) {

    var query by rememberSaveable {
        mutableStateOf("")
    }
    val viewModel = viewModel<SearchViewModel>()

    val keyboardController = LocalSoftwareKeyboardController.current

    val  state = viewModel.state


    Scaffold(
        topBar = {
            TopBar(navController, "Search Anime",showSearchIcon = false)
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    OutlinedTextField(
                        modifier = Modifier.weight(1f),
                        value = query,
                        onValueChange = {
                            query = it
                        },
                        label = {
                            Text(text = "Search for any Anime")
                        }
                    )
                    IconButton(onClick = {
                        viewModel.searchAnime(query)
                        keyboardController?.hide()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search for any anime"
                        )
                    }
                }
                AnimeList(state = state, navController = navController)
            }
        }
    )
}

@Composable
fun AnimeList(state: ScreenState, navController: NavHostController) {
    LazyVerticalGrid(columns = GridCells.Fixed(2), Modifier
        .padding(10.dp)
        .fillMaxSize()
        .background(Color.Transparent),
        content = {
            items(state.animeList.size){
                ItemUI(
                    itemIndex = it,
                    animeList = state.animeList,
                    navController = navController
                )
            }
        }
    )
}


@Preview
@Composable
fun SearchScreenPrev(){
    SearchScreen(navController = rememberNavController() )

}
