package com.tecsup.mibodega.ui.cliente.screens.pedidos

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.tecsup.mibodega.ui.componentes.BarraNavegacionInferiorContent
import com.tecsup.mibodega.ui.componentes.OpcionNavegacion
import com.tecsup.mibodega.ui.theme.BodegaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PedidosScreen(
    bottomBar: @Composable () -> Unit = {
        BarraNavegacionInferiorContent(
            rutaActual = OpcionNavegacion.Pedidos.ruta,
        ) {}
    },
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Mis Pedidos",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                    )
                },
            )
        },
        bottomBar = bottomBar,
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "Pantalla de Mis Pedidos",
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PedidosScreenPreview() {
    BodegaTheme {
        PedidosScreen()
    }
}
