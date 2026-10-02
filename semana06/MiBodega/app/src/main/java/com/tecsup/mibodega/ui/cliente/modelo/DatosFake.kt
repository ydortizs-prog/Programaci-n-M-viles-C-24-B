package com.tecsup.mibodega.ui.cliente.modelo

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CleaningServices
import androidx.compose.material.icons.filled.Cookie
import androidx.compose.material.icons.filled.Egg
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.ui.graphics.Color
import com.tecsup.mibodega.R
import com.tecsup.mibodega.ui.theme.VerdeBodega

/**
 * Datos de ejemplo (fake) para mostrar las categorías y productos de la bodega.
 */
val listaCategorias = listOf(
    "Todos",
    "Bebidas",
    "Abarrotes",
    "Lácteos y Huevos",
    "Snacks y Galletas",
    "Limpieza"
)

val listaCategoriasCompleta = listOf(
    Categoria(
        id = "bebidas",
        nombre = "Bebidas",
        descripcion = "Gaseosas, jugos, rehidratantes y agua",
        cantidadProductos = 15,
        icono = Icons.Default.LocalDrink,
        colorIcono = Color(0xFF0288D1),
        colorFondoIcono = Color(0xFFE1F5FE)
    ),
    Categoria(
        id = "abarrotes",
        nombre = "Abarrotes",
        descripcion = "Arroz, fideos, aceites, azúcar y conservas",
        cantidadProductos = 24,
        icono = Icons.Default.ShoppingBag,
        colorIcono = VerdeBodega,
        colorFondoIcono = Color(0xFFE8F5E9)
    ),
    Categoria(
        id = "lacteos",
        nombre = "Lácteos y Huevos",
        descripcion = "Leche, queso, yogur, mantequilla y huevos",
        cantidadProductos = 18,
        icono = Icons.Default.Egg,
        colorIcono = Color(0xFFF57C00),
        colorFondoIcono = Color(0xFFFFF3E0)
    ),
    Categoria(
        id = "snacks",
        nombre = "Snacks y Galletas",
        descripcion = "Galletas, papitas, chocolates y caramelos",
        cantidadProductos = 20,
        icono = Icons.Default.Cookie,
        colorIcono = Color(0xFF7B1FA2),
        colorFondoIcono = Color(0xFFF3E5F5)
    ),
    Categoria(
        id = "limpieza",
        nombre = "Limpieza",
        descripcion = "Detergentes, jabones, lejía y lavavajillas",
        cantidadProductos = 12,
        icono = Icons.Default.CleaningServices,
        colorIcono = Color(0xFF00796B),
        colorFondoIcono = Color(0xFFE0F2F1)
    )
)

val listaProductosFake = listOf(
    Producto(
        id = 1,
        nombre = "Arroz Costeño",
        descripcion = "Arroz extra, grano largo, ideal para el día a día.",
        precio = 4.50,
        categoria = "Abarrotes",
        imagenResId = R.drawable.arroz_costeno
    ),
    Producto(
        id = 2,
        nombre = "Aceite Primor",
        descripcion = "Aceite vegetal 1 L, alto en vitamina E.",
        precio = 8.90,
        categoria = "Abarrotes",
        imagenResId = R.drawable.aceite_primor
    ),
    Producto(
        id = 3,
        nombre = "Leche Gloria",
        descripcion = "Leche evaporada entera 1 L.",
        precio = 5.20,
        categoria = "Lácteos y Huevos",
        imagenResId = R.drawable.leche_gloria
    ),
    Producto(
        id = 4,
        nombre = "Galleta Oreo",
        descripcion = "Galletas de chocolate rellenas 126 g.",
        precio = 3.50,
        categoria = "Snacks y Galletas",
        imagenResId = R.drawable.galleta_oreo
    ),
    Producto(
        id = 5,
        nombre = "Coca-Cola Original",
        descripcion = "Bebida gaseosa sabor cola. Ideal para compartir en familia.",
        precio = 6.50,
        categoria = "Bebidas",
        imagenResId = R.drawable.coca_cola
    )
)
