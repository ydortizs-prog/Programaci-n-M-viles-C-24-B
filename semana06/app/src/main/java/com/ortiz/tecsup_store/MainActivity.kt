package com.ortiz.tecsup_store

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ortiz.tecsup_store.ui.theme.TECSUP_StoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TECSUP_StoreTheme {
                PantallaCarrito()
            }
        }
    }
}