package com.ortiz.tecsupfit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ortiz.tecsupfit.ui.theme.GreenContainer
import com.ortiz.tecsupfit.ui.theme.GreenPrimary

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text("Mi perfil", style = MaterialTheme.typography.titleLarge)
        }

        Spacer(modifier = Modifier.height(32.dp))

        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(GreenContainer),
            contentAlignment = Alignment.Center
        ) {
            Text("DR", style = MaterialTheme.typography.headlineMedium, color = GreenPrimary)
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Diego Ramos", style = MaterialTheme.typography.titleMedium)
        Text("Plan Premium", style = MaterialTheme.typography.bodySmall, color = Color.Gray)

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                modifier = Modifier.weight(1f),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF2F2F2))
            ) {
                Column(
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("14", style = MaterialTheme.typography.titleLarge)
                    Text("Clases", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                }
            }

            Card(
                modifier = Modifier.weight(1f),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF2F2F2))
            ) {
                Column(
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("3", style = MaterialTheme.typography.titleLarge)
                    Text("Rachas", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                }
            }
        }
    }
}

@Composable
fun RoutinesScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Mis Rutinas", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Text("1. Hipertrofia Tren Superior", style = MaterialTheme.typography.bodyLarge)
        Text("2. Cardio HIIT 20 min", style = MaterialTheme.typography.bodyLarge)
    }
}