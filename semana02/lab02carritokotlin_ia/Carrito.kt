package com.ortiz.lab02carritokotlin

// --- ABSTRACCIÓN ---
abstract class ElementoCarrito(
    val nombre: String,
    val precioBase: Double
) {
    abstract fun calcularImporte(): Double
}

// --- HERENCIA ---
class ProductoFisico(
    nombre: String,
    precioBase: Double,
    val cantidad: Int,
    val costoEnvio: Double
) : ElementoCarrito(nombre, precioBase) {
    // el override de calcularImporte se implementa en la Parte 3
}

class ServicioDigital(
    nombre: String,
    precioBase: Double,
    val mesesSuscripcion: Int
) : ElementoCarrito(nombre, precioBase) {
    // el override de calcularImporte se implementa en la Parte 3
}