package com.example.bmicalculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SuggestionsScreen(navController: NavHostController? = null, bmiCategory: String?) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Health Suggestions",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        when (bmiCategory) {
            "Underweight" -> {
                Text(
                    text = "You are underweight. Consider consulting a healthcare provider for personalized advice. In general, aim to eat a balanced diet with sufficient calories and nutrients.",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 24.dp)
                )
            }

            "Overweight" -> {
                Text(
                    text = "You are overweight. Incorporating regular physical activity and a balanced diet can help manage your weight. Consulting a healthcare provider or a dietitian for a personalized plan is recommended.",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 24.dp)
                )
            }

            "Class I Obesity", "Class II Obesity", "Class III Obesity" -> {
                Text(
                    text = "You are classified as obese. It's crucial to consult a healthcare provider for a comprehensive weight management plan. Adopting a healthier lifestyle through diet, exercise, and possibly medical interventions can be beneficial.",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 24.dp)
                )
            }

            else -> {
                Text(
                    text = "Your BMI is within the normal range. Keep up with a balanced diet and regular exercise to maintain a healthy weight.",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 24.dp)
                )
            }
        }

        Button(
            onClick = { navController?.popBackStack() },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Back to Calculator")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SuggestionsScreenObesityPreview() {
    SuggestionsScreen(bmiCategory = "Class I Obesity")
}
