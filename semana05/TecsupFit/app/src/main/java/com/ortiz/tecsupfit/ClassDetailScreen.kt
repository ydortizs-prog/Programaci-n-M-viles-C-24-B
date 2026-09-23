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
                title = { Text("Detalle de clase", color = Color.Black) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás", tint = Color.Black)
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(GreenContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.FitnessCenter,
                        contentDescription = null,
                        tint = GreenPrimary,
                        modifier = Modifier.size(64.dp)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))


                Text(
                    text = fitnessClass.name,
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(8.dp))


                Text(
                    text = "${fitnessClass.time} · ${fitnessClass.room} · ${fitnessClass.duration}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(16.dp))


                Text(
                    text = fitnessClass.description,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFF212121)
                )

                Spacer(modifier = Modifier.height(24.dp))


                Text(
                    text = "${fitnessClass.availableSpots} de ${fitnessClass.totalSpots} cupos disponibles",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }


            Button(
                onClick = { onReserveClick(fitnessClass.id) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary),
                shape = RoundedCornerShape(25.dp)
            ) {
                Text("Reservar cupo", color = Color.White, style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}