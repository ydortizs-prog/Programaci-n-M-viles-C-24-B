package com.ortiz.tecsupfit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ortiz.tecsupfit.model.sampleClasses
import com.ortiz.tecsupfit.ui.theme.GreenContainer
import com.ortiz.tecsupfit.ui.theme.GreenPrimary

@Composable
fun ConfirmationScreen(
    classId: String,
    onViewReservationsClick: () -> Unit
) {
    val fitnessClass = sampleClasses.find { it.id == classId } ?: sampleClasses.first()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(GreenContainer, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = GreenPrimary,
                modifier = Modifier.size(40.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text("¡Cupo reservado!", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))
        Text(fitnessClass.name, style = MaterialTheme.typography.titleMedium)
        Text("Hoy, ${fitnessClass.time} · ${fitnessClass.room}", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)

        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = onViewReservationsClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray.copy(alpha = 0.4f))
        ) {
            Text("Ver mis reservas", color = Color.Black)
        }
    }
}