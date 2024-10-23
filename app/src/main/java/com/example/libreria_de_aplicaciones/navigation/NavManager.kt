package com.example.libreria_de_aplicaciones.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.app_container_apps.dog_age_calculator_app.DogAgeCalculatorScreen
import com.example.libreria_de_aplicaciones.discounts_app.DiscountsScreen
import com.example.libreria_de_aplicaciones.loto.LoteriaScreen
import com.example.libreria_de_aplicaciones.views.HomeView

@Composable
fun NavManager() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "Home") {
        composable("Home") {
            HomeView(navController)
        }
        composable("DogAgeCalculator") {
            DogAgeCalculatorScreen(navController)
        }
        composable("Discounts") {
            DiscountsScreen(navController)
        }
        composable("Loteria") {
            LoteriaScreen(navController)
        }
    }
}