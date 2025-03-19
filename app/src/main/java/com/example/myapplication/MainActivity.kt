package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CarSlideshowApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CarSlideshowApp(modifier: Modifier = Modifier) {
    // List of car images and captions
    val cars = listOf(
        Pair(R.drawable.car1, "Ferrari SF90"),
        Pair(R.drawable.car2, "Lamborghini Aventador SVJ"),
        Pair(R.drawable.car3, "Porsche 911 GT3 RS"),
        Pair(R.drawable.car4, "Toyota Supra MK3"),
        Pair(R.drawable.car5, "Koenigsegg Gemera"),
        Pair(R.drawable.car6, "Ford Mustang RTR")
    )

    var currentIndex by remember { mutableIntStateOf(1) }
    var inputText by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Display Car Image and Caption
        Image(
            painter = painterResource(id = cars[currentIndex - 1].first),  // Adjusted to 1-based indexing
            contentDescription = "Car Image",
            modifier = Modifier
                .size(300.dp)
                .padding(8.dp)
        )
        Text(
            text = cars[currentIndex - 1].second,
            fontSize = 18.sp,
            modifier = Modifier.padding(8.dp)
        )

        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(onClick = {
                currentIndex = if (currentIndex > 1) currentIndex - 1 else cars.size
            }) {
                Text("Previous")
            }

            Button(onClick = {
                currentIndex = if (currentIndex < cars.size) currentIndex + 1 else 1
            }) {
                Text("Next")
            }
        }

        OutlinedTextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Go to car number") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )

        Button(onClick = {
            val index = inputText.toIntOrNull()
            if (index != null && index in 1..cars.size) {
                currentIndex = index
            }
        }) {
            Text("Go")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CarSlideshowPreview() {
    MyApplicationTheme {
        CarSlideshowApp()
    }
}
