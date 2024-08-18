package com.example.bmicalculator

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import java.util.Locale
import kotlin.text.*


@Composable
fun BMICalculatorScreen(navController: NavHostController? = null) {
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var bmi by remember { mutableStateOf<String?>(null) }
    var category by remember { mutableStateOf<String?>(null) }
    var showDialog by remember { mutableStateOf(false) }

    @SuppressLint("DefaultLocale")
    fun calculateBMI() {
        val weightValue = weight.toFloatOrNull()
        val heightValue = height.toFloatOrNull()

        if (weightValue != null && heightValue != null && heightValue > 0) {
            // Convert height from centimeters to meters
            val heightInMeters = heightValue / 100

            val bmiValue = weightValue / (heightInMeters * heightInMeters)

            bmi = String.format(Locale.US, "%.1f", bmiValue)

            category = when {
                bmiValue < 18.5 -> "Underweight"
                bmiValue < 24.9 -> "Normal weight"
                bmiValue < 29.9 -> "Overweight"
                bmiValue < 34.9 -> "Class I Obesity"
                bmiValue < 39.9 -> "Class II Obesity"
                else -> "Class III Obesity"
            }

            // Show the dialog if BMI is not in the normal range
            if (category != "Normal weight" && category != "Invalid input") {
                showDialog = true
            }
        } else {
            bmi = null
            category = "Invalid input"
        }
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "BMI Calculator",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        TextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Weight (kg)") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        TextField(

            value = height,
            onValueChange = { height = it },
            label = { Text("Height (cm)") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        )

        Button(
            onClick = { calculateBMI() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("Calculate BMI", color = MaterialTheme.colorScheme.onPrimary)
        }

        bmi?.let {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    "Your BMI is $it",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    "Category: $category",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Health Suggestions") },
            text = {
                Text("Your BMI category is $category. Would you like to see health suggestions?")
            },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                        // Navigate to SuggestionsScreen with the BMI category
                        navController?.navigate("suggestions/${category}")
                    }
                ) {
                    Text("See Suggestions")
                }
            },
            dismissButton = {
                Button(
                    onClick = { showDialog = false }
                ) {
                    Text("Dismiss")
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BMICalculatorScreen()
}
