package com.example.wazitoecommerce.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wazitoecommerce.ui.theme.screens.favorites.FavoritesScreen
import com.example.wazitoecommerce.ui.theme.screens.home.HomeScreen
import com.example.wazitoecommerce.ui.theme.screens.login.LoginScreen
import com.example.wazitoecommerce.ui.theme.screens.property.AddPropertyScreen
import com.example.wazitoecommerce.ui.theme.screens.property.ViewPropertyScreen

import com.example.wazitoecommerce.ui.theme.screens.signup.SignupScreen
import com.example.wazitoecommerce.ui.theme.screens.splash.SplashScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController:NavHostController = rememberNavController(),
    startDestination:String = SPLASH_URL
){
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier)
    {
        composable(LOGIN_URL){
            LoginScreen(navController = navController)
        }
        composable(SIGNUP_URL){
            SignupScreen(navController = navController)
        }
        composable(HOME_URL){
            HomeScreen(navController = navController)
        }
            //added
        composable(FAVORITES_URL){
            FavoritesScreen(navController = navController)
        }
        composable(ADD_PROPERTY_URL){
            AddPropertyScreen(navController = navController)
        }
        composable(VIEW_PROPERTY_URL){
            ViewPropertyScreen(navController = navController)
        }

        composable(SPLASH_URL){
            SplashScreen(navController = navController)
        }
    }
}