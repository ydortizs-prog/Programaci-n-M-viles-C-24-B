package com.ortiz.lab04manejoestados

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ortiz.lab04manejoestados.ui.theme.Lab04ManejoEstadosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab04ManejoEstadosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TemperatureDisplay(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun TemperatureDisplay(modifier: Modifier = Modifier) {
    var temperatura by remember { mutableStateOf(20) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Temperatura: $temperatura°C",
            fontSize = 24.sp,
            color = when {
                temperatura > 30 -> Color.Red
                temperatura < 10 -> Color.Blue
                else -> Color.Black
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Button(onClick = { temperatura++ }) {
                Text("Subir")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { temperatura-- }) {
                Text("Bajar")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { temperatura = 20 }) {
                Text("Resetear")
            }
        }
    }
}