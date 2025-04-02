package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

data class Car(val imageResId: Int, val name: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CarList(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CarList(modifier: Modifier = Modifier) {
    val cars = listOf(
        Car(R.drawable.car1, "Ferrari SF90"),
        Car(R.drawable.car2, "Lamborghini Aventador SVJ"),
        Car(R.drawable.car3, "Porsche 911 GT3 RS"),
        Car(R.drawable.car4, "Toyota Supra MK3"),
        Car(R.drawable.car5, "Koenigsegg Gemera"),
        Car(R.drawable.car6, "Ford Mustang RTR"),
        Car(R.drawable.car7, "Audi RS6 ABT"),
        Car(R.drawable.car8, "BMW M4 CSL"),
        Car(R.drawable.car9, "Mercedes-Benz AMG GT"),
        Car(R.drawable.car10, "Nissan GT-R Nismo GT3 Race Car")
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(cars) { car ->
            CarItem(car)
        }
    }
}

@Composable
fun CarItem(car: Car) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = car.imageResId),
            contentDescription = car.name,
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        )
        Text(text = car.name, fontSize = 18.sp, modifier = Modifier.padding(top = 8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCarList() {
    MyApplicationTheme {
        CarList()
    }
}
