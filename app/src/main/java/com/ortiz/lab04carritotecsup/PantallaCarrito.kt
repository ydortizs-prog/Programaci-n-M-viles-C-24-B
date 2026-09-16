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

    // Estados para el Reto 1: AlertDialog de confirmación
    var productoAEliminar by remember { mutableStateOf<Producto?>(null) }
    var mostrarDialogo by remember { mutableStateOf(false) }

    // Lógica de Totales
    val subtotal = listaProductos.sumOf { it.precio * it.cantidad }

    // Reto 2: Descuento dinámico usando 'when'
    val porcentajeDescuento = when {
        subtotal > 5000 -> 0.10
        subtotal > 3000 -> 0.05
        else -> 0.0
    }
    val montoDescuento = subtotal * porcentajeDescuento
    val subtotalConDescuento = subtotal - montoDescuento

    val igv = subtotalConDescuento * 0.18
    val total = subtotalConDescuento + igv
    val totalCantidadProductos = listaProductos.sumOf { it.cantidad }

    val purpleColor = Color(0xFF6200EE)

    // Modal de Confirmación (Reto 1)
    if (mostrarDialogo && productoAEliminar != null) {
        AlertDialog(
            onDismissRequest = {
                mostrarDialogo = false
                productoAEliminar = null
            },
            title = { Text(text = "¿Eliminar este producto?", fontWeight = FontWeight.Bold) },
            text = { Text(text = "¿Estás seguro de que deseas eliminar '${productoAEliminar?.nombre}' del carrito?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        productoAEliminar?.let { listaProductos.remove(it) }
                        mostrarDialogo = false
                        productoAEliminar = null
                    }
                ) {
                    Text("Eliminar", color = Color(0xFFB00020), fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        mostrarDialogo = false
                        productoAEliminar = null
                    }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }

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
                actions = {
                    if (listaProductos.isNotEmpty()) {
                        TextButton(onClick = { listaProductos.clear() }) {
                            Text("VACIAR", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                    }
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
                    Text(text = "Productos en total: $totalCantidadProductos", color = Color.Gray, fontSize = 12.sp)

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Subtotal", color = Color.DarkGray)
                        Text(text = "S/ %.2f".format(subtotal), color = Color.DarkGray)
                    }

                    if (porcentajeDescuento > 0) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Descuento (${(porcentajeDescuento * 100).toInt()}%)",
                                color = Color(0xFF388E3C),
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = "-S/ %.2f".format(montoDescuento),
                                color = Color(0xFF388E3C),
                                fontWeight = FontWeight.Medium
                            )
                        }
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
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
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
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )

                OutlinedTextField(
                    value = cantidadInput,
                    onValueChange = { cantidadInput = it },
                    label = { Text("Cantidad") },
                    modifier = Modifier.weight(1f),
                    singleLine = true
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
                Text("AGREGAR AL CARRITO", fontWeight = FontWeight.Bold)
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
                                        text = "S/ %.2f x %d unidades".format(producto.precio, producto.cantidad),
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
                                    TextButton(onClick = {
                                        productoAEliminar = producto
                                        mostrarDialogo = true
                                    }) {
                                        Text(text = "🗑", color = Color(0xFFB00020), fontSize = 18.sp)
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