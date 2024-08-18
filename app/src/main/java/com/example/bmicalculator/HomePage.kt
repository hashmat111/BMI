package com.example.bmicalculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomePage(navController: NavHostController? = null) {
    // Set up the background and main layout
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD)) // Light blue background color
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(Color.White, shape = RoundedCornerShape(16.dp))
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Title text
            Text(
                text = "What is BMI?",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1976D2), // Darker blue color
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Description text
            Text(
                text = "Body Mass Index (BMI) is a simple calculation using a person's height and weight. " +
                        "The formula is BMI = kg/m² where kg is a person's weight in kilograms and m² is their height in meters squared.",
                fontSize = 18.sp,
                color = Color(0xFF424242), // Dark gray text
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // BMI Categories header
            Text(
                text = "BMI Categories:",
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1976D2), // Darker blue color
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // BMI categories text
            Text(
                text = "• Underweight: BMI < 18.5\n" +
                        "• Normal weight: 18.5 ≤ BMI < 24.9\n" +
                        "• Overweight: 25 ≤ BMI < 29.9\n" +
                        "• Obesity: BMI ≥ 30",
                fontSize = 18.sp,
                color = Color(0xFF424242), // Dark gray text
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Button to navigate to BMI calculator
            Button(
                onClick = { navController?.navigate("bmi_calculator") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Calculate your BMI", color = Color.White, fontSize = 18.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePagePreview() {  // Renamed function
    HomePage()
}

