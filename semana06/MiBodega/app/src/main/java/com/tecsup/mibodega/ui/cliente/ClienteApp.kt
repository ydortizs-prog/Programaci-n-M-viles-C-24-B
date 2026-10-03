package com.tecsup.mibodega.ui.cliente

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tecsup.mibodega.ui.cliente.modelo.ItemCarrito
import com.tecsup.mibodega.ui.cliente.modelo.Producto
import com.tecsup.mibodega.ui.cliente.modelo.listaProductosFake
import com.tecsup.mibodega.ui.cliente.screens.bienvenida.BienvenidaScreen
import com.tecsup.mibodega.ui.cliente.screens.carrito.CarritoScreen
import com.tecsup.mibodega.ui.cliente.screens.categorias.CategoriasScreen
import com.tecsup.mibodega.ui.cliente.screens.detalle.DetalleProductoScreen
import com.tecsup.mibodega.ui.cliente.screens.inicio.InicioScreen
import com.tecsup.mibodega.ui.cliente.screens.pedidos.PedidosScreen
import com.tecsup.mibodega.ui.cliente.screens.perfil.PerfilScreen
import com.tecsup.mibodega.ui.cliente.screens.registro.RegistroScreen
import com.tecsup.mibodega.ui.componentes.BarraNavegacionInferior
import com.tecsup.mibodega.ui.componentes.OpcionNavegacion

private object Rutas {
    const val DETALLE = "detalle/{productoId}"
    const val CARRITO = "carrito"

    fun detalle(productoId: Int) = "detalle/$productoId"
}

@Composable
fun ClienteApp() {
    val navController = rememberNavController()

    var carrito by remember { mutableStateOf<List<ItemCarrito>>(emptyList()) }

    NavHost(
        navController = navController,
        startDestination = OpcionNavegacion.Bienvenida.ruta,
    ) {
        // Pantalla de Bienvenida muestras  (Punto de entrada obligatorio)
        composable(OpcionNavegacion.Bienvenida.ruta) {
            BienvenidaScreen(
                onRegistrarse = {
                    navController.navigate(OpcionNavegacion.Registro.ruta)
                },
                onIniciarSesion = {
                    // Ingreso directo a Inicio descartando la pantalla de bienvenida
                    navController.navigate(OpcionNavegacion.Inicio.ruta) {
                        popUpTo(OpcionNavegacion.Bienvenida.ruta) { inclusive = true }
                    }
                },
                onTerminos = { },
            )
        }

        // Pantalla de Registro
        composable(OpcionNavegacion.Registro.ruta) {
            RegistroScreen(
                onVolver = { navController.popBackStack() },
                onCrearCuenta = { _, _, _, _ ->
                    navController.navigate(OpcionNavegacion.Inicio.ruta) {
                        popUpTo(OpcionNavegacion.Bienvenida.ruta) { inclusive = true }
                    }
                },
            )
        }

        // Pantalla Principal (Inicio)
        composable(OpcionNavegacion.Inicio.ruta) {
            InicioScreen(
                cantidadCarrito = carrito.sumOf { it.cantidad },
                onVerCarrito = { navController.navigate(Rutas.CARRITO) },
                onProductoClick = { producto ->
                    navController.navigate(Rutas.detalle(producto.id))
                },
                onAgregarProducto = { producto ->
                    carrito = agregarOSumarProducto(carrito, producto, 1)
                },
                bottomBar = {
                    BarraNavegacionInferior(navController = navController)
                },
            )
        }

        // Pantalla de Categorías
        composable(OpcionNavegacion.Categorias.ruta) {
            CategoriasScreen(
                onCategoriaClick = {
                    navController.navigate(OpcionNavegacion.Inicio.ruta) {
                        popUpTo(OpcionNavegacion.Inicio.ruta) { inclusive = true }
                    }
                },
                bottomBar = {
                    BarraNavegacionInferior(navController = navController)
                },
            )
        }

        // Pantalla de Mis Pedidos
        composable(OpcionNavegacion.Pedidos.ruta) {
            PedidosScreen(
                bottomBar = {
                    BarraNavegacionInferior(navController = navController)
                },
            )
        }

        // Pantalla de Mi Perfil
        composable(OpcionNavegacion.Perfil.ruta) {
            PerfilScreen(
                bottomBar = {
                    BarraNavegacionInferior(navController = navController)
                },
            )
        }

        // Detalle de Producto
        composable(
            route = Rutas.DETALLE,
            arguments = listOf(navArgument("productoId") { type = NavType.IntType }),
        ) { backStackEntry ->
            val productoId = backStackEntry.arguments?.getInt("productoId") ?: 0
            val producto = listaProductosFake.first { it.id == productoId }

            DetalleProductoScreen(
                producto = producto,
                onVolver = { navController.popBackStack() },
                onAgregarAlCarrito = { productoSeleccionado, cantidad ->
                    carrito = agregarOSumarProducto(carrito, productoSeleccionado, cantidad)
                    navController.popBackStack()
                },
            )
        }

        // Carrito de compras
        composable(Rutas.CARRITO) {
            CarritoScreen(
                carrito = carrito,
                onVolver = { navController.popBackStack() },
                onIncrementar = { producto ->
                    carrito = carrito.map {
                        if (it.producto.id == producto.id) it.copy(cantidad = it.cantidad + 1) else it
                    }
                },
                onDecrementar = { producto ->
                    carrito = carrito.mapNotNull {
                        when {
                            it.producto.id != producto.id -> it
                            it.cantidad > 1 -> it.copy(cantidad = it.cantidad - 1)
                            else -> null
                        }
                    }
                },
                onEliminar = { producto ->
                    carrito = carrito.filterNot { it.producto.id == producto.id }
                },
                onContinuarPedido = { },
            )
        }
    }
}

private fun agregarOSumarProducto(
    carrito: List<ItemCarrito>,
    producto: Producto,
    cantidad: Int,
): List<ItemCarrito> {
    val itemExistente = carrito.find { it.producto.id == producto.id }
    return if (itemExistente != null) {
        carrito.map {
            if (it.producto.id == producto.id) it.copy(cantidad = it.cantidad + cantidad) else it
        }
    } else {
        carrito + ItemCarrito(producto = producto, cantidad = cantidad)
    }
}