package com.example.mirunime.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavHostController,
           title:String,
           showSearchIcon: Boolean = true) {
    TopAppBar(title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = {
                navController.navigate("home")
            }) {
                Icon(imageVector = Icons.Rounded.Home, contentDescription = "menu")
            }
        },

        actions = {
            if(showSearchIcon)
            IconButton(onClick = {navController.navigate("search")}) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "search")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.DarkGray,
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White,
            actionIconContentColor = Color.White
        )
    )
}

@Preview
@Composable
fun TopBarPrev(){
    TopBar(navController = rememberNavController(),"testing")
}