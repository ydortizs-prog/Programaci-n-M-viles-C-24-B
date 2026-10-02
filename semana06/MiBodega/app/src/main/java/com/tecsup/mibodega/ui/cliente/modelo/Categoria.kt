package com.tecsup.mibodega.ui.cliente.modelo

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Modelo de datos para representar una categoría de productos en 'Mi Bodega'.
 *
 * @param id Identificador único de la categoría.
 * @param nombre Nombre visible de la categoría.
 * @param descripcion Resumen de los productos que contiene.
 * @param cantidadProductos Cantidad aproximada de productos disponibles.
 * @param icono Ícono de Material Icons representativo.
 * @param colorIcono Color primario del ícono.
 * @param colorFondoIcono Fondo suave circular/cuadrado del ícono.
 * @param imagenResId Recurso opcional de drawable para la imagen de la categoría.
 */
data class Categoria(
    val id: String,
    val nombre: String,
    val descripcion: String,
    val cantidadProductos: Int,
    val icono: ImageVector,
    val colorIcono: Color,
    val colorFondoIcono: Color,
    val imagenResId: Int? = null
)