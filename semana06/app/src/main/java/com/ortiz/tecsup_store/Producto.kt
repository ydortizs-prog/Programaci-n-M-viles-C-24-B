package com.ortiz.tecsup_store

data class Producto(
    val id: String = java.util.UUID.randomUUID().toString(),
    val nombre: String,
    val precio: Double,
    val cantidad: Int
)