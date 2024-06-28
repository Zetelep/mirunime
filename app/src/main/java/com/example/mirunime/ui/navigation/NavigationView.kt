package com.example.mirunime.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mirunime.ui.detail.DetailScreen
import com.example.mirunime.ui.home.HomeScreen
import com.example.mirunime.ui.login.LoginScreen
import com.example.mirunime.ui.register.RegisterScreen
import com.example.mirunime.ui.search.SearchScreen
import com.example.mirunime.ui.welcome.WelcomeScreen
import com.example.mirunime.viewmodel.AnimeViewModel
import com.example.mirunime.viewmodel.SearchViewModel
import com.example.mirunime.viewmodel.UserViewModel


@Composable
fun NavigationView() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") { WelcomeScreen(navController) }
        composable("login") { LoginScreen(navController, UserViewModel()) }
        composable("register") { RegisterScreen(UserViewModel(), navController) }


        composable("home") {
            HomeScreen(navController)
        }
        composable("detail/{mal_id}",
            arguments = listOf(
                navArgument(
                    name = "mal_id"
                ){
                    type = NavType.IntType
                }
            )
        ) {id->
            id.arguments?.getInt("mal_id")?.let { id1->
                DetailScreen(id =id1,navController)
            }
        }

        composable("search"){
            SearchScreen(navController = navController, viewModel = SearchViewModel())
        }
    }
}