package com.ortiz.tecsupfit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ortiz.tecsupfit.model.FitnessClass
import com.ortiz.tecsupfit.model.sampleClasses
import com.ortiz.tecsupfit.ui.theme.GreenContainer
import com.ortiz.tecsupfit.ui.theme.GreenPrimary

@Composable
fun HomeScreen(onClassClick: (String) -> Unit) {
    var selectedFilter by remember { mutableStateOf("Hoy") }
    val filters = listOf("Hoy", "Esta semana")

    val filteredList = sampleClasses.filter {
        if (selectedFilter == "Hoy") it.filterType == "Hoy" else true
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(GreenPrimary)
                .padding(16.dp)
        ) {
            Column {
                Text("TECSUP Fit", style = MaterialTheme.typography.titleLarge, color = Color.White)
                Text("Hola, Diego", style = MaterialTheme.typography.bodyMedium, color = Color.White.copy(alpha = 0.8f))
            }
        }

        Column(modifier = Modifier.padding(16.dp)) {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(filters) { filter ->
                    FilterChip(
                        selected = selectedFilter == filter,
                        onClick = { selectedFilter = filter },
                        label = { Text(filter) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text("Clases disponibles", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(filteredList) { item ->
                    ClassCard(fitnessClass = item, onClick = { onClassClick(item.id) })
                }
            }
        }
    }
}

@Composable
fun ClassCard(fitnessClass: FitnessClass, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF2F2F2))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(GreenContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.FitnessCenter, contentDescription = null, tint = GreenPrimary)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(fitnessClass.name, style = MaterialTheme.typography.titleMedium)
                Text("${fitnessClass.time} · ${fitnessClass.room}", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }
        }
    }
}