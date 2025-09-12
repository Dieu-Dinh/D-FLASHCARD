package com.example.dflashcard.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dflashcard.View.IntroPages
import com.example.dflashcard.View.LoginScreen
import com.example.dflashcard.View.Register
import com.example.dflashcard.View.Home



sealed class Screen(val route: String) {
    object IntroPages: Screen("IntroPages")
    object Login: Screen("Login")
    object Register: Screen("Register")
    object Home: Screen("Home")
}

@Composable
fun App( navController: NavHostController ) {
    NavHost(
        navController = navController,
        startDestination = Screen.IntroPages.route
    ){
        composable(Screen.IntroPages.route) {
            IntroPages(navController = navController)
        }
        composable(Screen.Login.route){
            LoginScreen( navController = navController)
        }
        composable(Screen.Home.route){
            Home( navController = navController)
        }
        composable(Screen.Register.route){
            Register(navController = navController)
        }
    }

}
