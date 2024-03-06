package com.example.dayfiveunitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.dayfiveunitconverter.ui.theme.DayFiveUnitConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DayFiveUnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    VisualComponentConstructor().ShowTriangleCalculatorMainView()
                }
            }
        }
    }

    val dayFiveSummary = "https://tutorials.eu/the-power-of-jetpack-compose-and-ui-customization-day-5-android-14-masterclass/"
    val daySixSummary = "https://tutorials.eu/mastering-state-management-and-essential-kotlin-syntax-day-6-android-14-masterclass/"
}

