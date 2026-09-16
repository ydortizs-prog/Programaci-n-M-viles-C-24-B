package com.ortiz.lab04carritotecsup

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaCarrito(modifier: Modifier = Modifier) {
    val listaProductos = remember { mutableStateListOf<Producto>() }
    var nombreInput by remember { mutableStateOf("") }
    var precioInput by remember { mutableStateOf("") }
    var cantidadInput by remember { mutableStateOf("") }


    val subtotal = listaProductos.sumOf { it.precio * it.cantidad }
    val igv = subtotal * 0.18
    val total = subtotal + igv
    val totalCantidadProductos = listaProductos.sumOf { it.cantidad }

    val purpleColor = Color(0xFF6200EE)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Mi Carrito TECSUP",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = purpleColor)
            )
        },
        bottomBar = {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF3EDF7)),
                shape = androidx.compose.ui.graphics.RectangleShape
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Productos: $totalCantidadProductos", color = Color.Gray, fontSize = 12.sp)

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Subtotal", color = Color.DarkGray)
                        Text(text = "S/ %.2f".format(subtotal), color = Color.DarkGray)
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "IGV (18%)", color = Color.DarkGray)
                        Text(text = "S/ %.2f".format(igv), color = Color.DarkGray)
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "TOTAL", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Text(
                            text = "S/ %.2f".format(total),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = purpleColor
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = nombreInput,
                onValueChange = { nombreInput = it },
                label = { Text("Nombre del producto") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = precioInput,
                    onValueChange = { precioInput = it },
                    label = { Text("Precio (S/)") },
                    modifier = Modifier.weight(1f)
                )

                OutlinedTextField(
                    value = cantidadInput,
                    onValueChange = { cantidadInput = it },
                    label = { Text("Cantidad") },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    val precio = precioInput.toDoubleOrNull() ?: 0.0
                    val cantidad = cantidadInput.toIntOrNull() ?: 1
                    if (nombreInput.isNotBlank() && precio > 0) {
                        listaProductos.add(Producto(nombreInput, precio, cantidad))
                        nombreInput = ""
                        precioInput = ""
                        cantidadInput = ""
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = purpleColor)
            ) {
                Text("AGREGAR", fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (listaProductos.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "Tu carrito está vacío", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Gray)
                        Text(text = "Agrega tu primer producto", color = Color.Gray, fontSize = 14.sp)
                    }
                }
            } else {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(listaProductos) { producto ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(2.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(text = producto.nombre, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                    Text(
                                        text = "S/ %.2f x %d".format(producto.precio, producto.cantidad),
                                        color = Color.Gray,
                                        fontSize = 12.sp
                                    )
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = "S/ %.2f".format(producto.precio * producto.cantidad),
                                        fontWeight = FontWeight.Bold,
                                        color = purpleColor,
                                        modifier = Modifier.padding(end = 8.dp)
                                    )

                                    TextButton(onClick = { listaProductos.remove(producto) }) {
                                        Text(
                                            text = "🗑",
                                            color = Color(0xFFB00020),
                                            fontSize = 18.sp
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}