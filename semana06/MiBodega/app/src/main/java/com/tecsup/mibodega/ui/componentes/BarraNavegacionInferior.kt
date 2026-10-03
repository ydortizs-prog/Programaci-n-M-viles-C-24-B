package com.tecsup.mibodega.ui.componentes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.tecsup.mibodega.ui.theme.BodegaTheme
import com.tecsup.mibodega.ui.theme.GrisTexto
import com.tecsup.mibodega.ui.theme.VerdeBodega

/**
 * Representa los destinos de navegación en la app 'Mi Bodega'.
 *
 * @param ruta Identificador de ruta para Navigation Compose.
 * @param titulo Etiqueta visible bajo el ícono (si aplica).
 * @param icono Ícono de la opción (Material Icons).
 */
sealed class OpcionNavegacion(
    val ruta: String,
    val titulo: String,
    val icono: ImageVector? = null,
) {
    object Bienvenida : OpcionNavegacion("bienvenida", "Bienvenida")
    object Registro : OpcionNavegacion("registro", "Registro")
    object Inicio : OpcionNavegacion("inicio", "Inicio", Icons.Default.Home)
    object Categorias : OpcionNavegacion("categorias", "Categorías", Icons.AutoMirrored.Filled.List)
    object Pedidos : OpcionNavegacion("pedidos", "Pedidos", Icons.Default.Receipt)
    object Perfil : OpcionNavegacion("perfil", "Perfil", Icons.Default.Person)

    companion object {
        val listaOpciones = listOf(Inicio, Categorias, Pedidos, Perfil)
    }
}

/**
 * Componente de la Barra de Navegación Inferior (Bottom Navigation Bar)
 * implementado con Material 3 e integrado con [NavController].
 *
 * @param navController Controlador de navegación del NavHost.
 */
@Composable
fun BarraNavegacionInferior(
    navController: NavController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val rutaActual = navBackStackEntry?.destination?.route

    BarraNavegacionInferiorContent(
        rutaActual = rutaActual,
    ) { opcion ->
        navController.navigate(opcion.ruta) {
            // Guarda y restaura el estado al cambiar entre pestañas
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}

/**
 * Diseño visual puro de la Barra de Navegación Inferior (Material 3).
 *
 * @param rutaActual Identificador de la ruta activa.
 * @param onOpcionSeleccionada Callback emitido al presionar una opción.
 */
@Composable
fun BarraNavegacionInferiorContent(
    rutaActual: String?,
    onOpcionSeleccionada: (OpcionNavegacion) -> Unit,
) {
    NavigationBar(
        containerColor = Color.White,
    ) {
        OpcionNavegacion.listaOpciones.forEach { opcion ->
            val seleccionado = rutaActual == opcion.ruta

            NavigationBarItem(
                selected = seleccionado,
                onClick = { onOpcionSeleccionada(opcion) },
                icon = {
                    opcion.icono?.let { icono ->
                        Icon(
                            imageVector = icono,
                            contentDescription = opcion.titulo,
                        )
                    }
                },
                label = { Text(text = opcion.titulo) },
                colors = NavigationBarItemDefaults.colors(
                    // Ícono y texto seleccionados en color verde (VerdeBodega)
                    selectedIconColor = VerdeBodega,
                    selectedTextColor = VerdeBodega,
                    // Indicador (pill / fondo suave) de la opción seleccionada
                    indicatorColor = VerdeBodega.copy(alpha = 0.15f),
                    // Ícono y texto no seleccionados en color gris (GrisTexto)
                    unselectedIconColor = GrisTexto,
                    unselectedTextColor = GrisTexto,
                ),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BarraNavegacionInferiorPreview() {
    BodegaTheme {
        BarraNavegacionInferiorContent(
            rutaActual = OpcionNavegacion.Inicio.ruta,
        ) {}
    }
}
