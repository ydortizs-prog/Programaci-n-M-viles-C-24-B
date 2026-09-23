package com.ortiz.lab05navegacion.screens.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ProfileScreen(navController: NavController) {
    val purpleHeader = Color(0xFF5A4494)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .background(
                    color = purpleHeader,
                    shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)
                )
        )


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 0.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Surface(
                    modifier = Modifier
                        .size(110.dp)
                        .clip(CircleShape)
                        .border(4.dp, Color.White, CircleShape),
                    color = Color.LightGray
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text("👨‍🎓", fontSize = 50.sp)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Juan León",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )

                Text(
                    text = "Ingeniería de Sistemas",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color(0xFF6750A4),
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))


        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F2F8))
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                ProfileItem(
                    emoji = "🪪",
                    title = "ID Estudiante",
                    value = "2024-0001"
                )

                Spacer(modifier = Modifier.height(16.dp))

                ProfileItem(
                    emoji = "✉️",
                    title = "Correo Electrónico",
                    value = "juan.leon@example.com"
                )

                Spacer(modifier = Modifier.height(16.dp))

                ProfileItem(
                    emoji = "🎓",
                    title = "Facultad",
                    value = "Ingeniería y Tecnología"
                )

                Spacer(modifier = Modifier.height(16.dp))

                HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Biografía",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Estudiante destacado con interés en desarrollo Android.",
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.DarkGray)
                )
            }
        }
    }
}

@Composable
fun ProfileItem(emoji: String, title: String, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = emoji, fontSize = 22.sp)
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall.copy(color = Color.Gray)
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}