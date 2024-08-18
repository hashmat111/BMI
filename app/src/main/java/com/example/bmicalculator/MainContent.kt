package com.example.bmicalculator

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun MainContent() {
    val navController = rememberNavController()
    Surface(modifier = Modifier.fillMaxSize()) {


        NavHost(navController = navController, startDestination = "HomePage") {
            composable("HomePage") { HomePage(navController) }
            composable("bmi_calculator") { BMICalculatorScreen(navController) }

            // Define a route with a parameter for the BMI category
            composable("suggestions/{category}") { backStackEntry ->
                // Retrieve the BMI category from the backStackEntry arguments
                val bmiCategory = backStackEntry.arguments?.getString("category")
                SuggestionsScreen(
                    navController = navController,
                    bmiCategory = bmiCategory
                )
            }
        }
    }


}

@Preview
@Composable
fun MainPreview() {
    MainContent()
}