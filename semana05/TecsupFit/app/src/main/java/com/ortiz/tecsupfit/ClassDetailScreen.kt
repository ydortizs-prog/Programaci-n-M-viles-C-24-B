package com.ortiz.tecsupfit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ortiz.tecsupfit.model.sampleClasses
import com.ortiz.tecsupfit.ui.theme.GreenContainer
import com.ortiz.tecsupfit.ui.theme.GreenPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClassDetailScreen(
    classId: String,
    onBackClick: () -> Unit,
    onReserveClick: (String) -> Unit
) {
    val fitnessClass = sampleClasses.find { it.id == classId } ?: sampleClasses.first()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle de clase") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(GreenContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.FitnessCenter,
                        contentDescription = null,
                        modifier = Modifier.size(56.dp),
                        tint = GreenPrimary
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(fitnessClass.name, style = MaterialTheme.typography.headlineSmall)
                Text(
                    text = "${fitnessClass.time} · ${fitnessClass.room} · ${fitnessClass.duration}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text(fitnessClass.description, style = MaterialTheme.typography.bodyLarge)

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "${fitnessClass.availableSpots} de ${fitnessClass.totalSpots} cupos disponibles",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }

            Button(
                onClick = { onReserveClick(fitnessClass.id) },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary)
            ) {
                Text("Reservar cupo", color = Color.White)
            }
        }
    }
}