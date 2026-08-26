package com.ortiz.lab02carritokotlin

// --- ABSTRACCIÓN ---
abstract class ElementoCarrito(
    val nombre: String,
    val precioBase: Double
) {
    abstract fun calcularImporte(): Double
}