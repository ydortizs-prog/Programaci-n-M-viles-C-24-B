package com.tecsup.mibodega.ui.cliente.screens.categorias

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tecsup.mibodega.ui.cliente.modelo.Categoria
import com.tecsup.mibodega.ui.cliente.modelo.listaCategoriasCompleta
import com.tecsup.mibodega.ui.componentes.BarraNavegacionInferiorContent
import com.tecsup.mibodega.ui.componentes.OpcionNavegacion
import com.tecsup.mibodega.ui.theme.AzulTexto
import com.tecsup.mibodega.ui.theme.BodegaTheme
import com.tecsup.mibodega.ui.theme.GrisClaro
import com.tecsup.mibodega.ui.theme.GrisTexto
import com.tecsup.mibodega.ui.theme.VerdeBodega

/**
 * Pantalla principal de Categorías de la bodega.
 * Muestra las categorías disponibles en un Grid de 2 columnas.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriasScreen(
    categorias: List<Categoria> = listaCategoriasCompleta,
    onCategoriaClick: (Categoria) -> Unit = {},
    bottomBar: @Composable () -> Unit = {
        BarraNavegacionInferiorContent(
            rutaActual = OpcionNavegacion.Categorias.ruta,
        ) {}
    },
) {
    var textoBusqueda by remember { mutableStateOf("") }

    val categoriasFiltradas = categorias.filter { categoria ->
        categoria.nombre.contains(textoBusqueda, ignoreCase = true) ||
                categoria.descripcion.contains(textoBusqueda, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Categorías", fontWeight = FontWeight.Bold) },
            )
        },
        bottomBar = bottomBar,
    ) { paddingInterno ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingInterno)
                .padding(horizontal = 16.dp),
        ) {
            // Buscador de categorías
            OutlinedTextField(
                value = textoBusqueda,
                onValueChange = { textoBusqueda = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 12.dp),
                placeholder = { Text("Buscar categoría...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = GrisClaro,
                    focusedContainerColor = GrisClaro,
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = VerdeBodega,
                ),
            )

            Text(
                text = "Explora por sección",
                style = MaterialTheme.typography.titleMedium,
                color = AzulTexto,
                modifier = Modifier.padding(bottom = 12.dp),
            )

            // Cuadrícula (Grid) de 2 columnas con las tarjetas de categoría
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 16.dp),
                modifier = Modifier.fillMaxSize(),
            ) {
                items(categoriasFiltradas) { categoria ->
                    CategoriaCard(
                        categoria = categoria,
                    ) { onCategoriaClick(categoria) }
                }
            }
        }
    }
}

/**
 * Tarjeta individual para representar una Categoría en el Grid.
 */
@Composable
fun CategoriaCard(
    categoria: Categoria,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = GrisClaro,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp,
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                // Imagen de la categoría o Icono de respaldo
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(categoria.colorFondoIcono),
                    contentAlignment = Alignment.Center,
                ) {
                    if (categoria.imagenResId != null) {
                        Image(
                            painter = painterResource(id = categoria.imagenResId),
                            contentDescription = categoria.nombre,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(6.dp)
                        )
                    } else {
                        Icon(
                            imageVector = categoria.icono,
                            contentDescription = categoria.nombre,
                            tint = categoria.colorIcono,
                            modifier = Modifier.size(26.dp),
                        )
                    }
                }

                // Flecha indicadora suave
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    tint = GrisTexto.copy(alpha = 0.5f),
                    modifier = Modifier.size(18.dp),
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Nombre de la categoría
            Text(
                text = categoria.nombre,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                ),
                color = AzulTexto,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Resumen de productos
            Text(
                text = categoria.descripcion,
                style = MaterialTheme.typography.bodySmall,
                color = GrisTexto,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 16.sp,
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Chip con la cantidad de productos
            Surface(
                color = Color.White,
                shape = RoundedCornerShape(20.dp),
            ) {
                Text(
                    text = "${categoria.cantidadProductos} productos",
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Medium,
                    ),
                    color = VerdeBodega,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CategoriasScreenPreview() {
    BodegaTheme {
        CategoriasScreen()
    }
}