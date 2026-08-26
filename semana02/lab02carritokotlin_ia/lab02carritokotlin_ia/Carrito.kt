package com.ortiz.lab02carritokotlin


// --- ABSTRACCIÓN ---
abstract class ElementoCarrito(
    val nombre: String,
    val precioBase: Double
) {
    abstract fun calcularImporte(): Double
}

// --- HERENCIA Y POLIMORFISMO ---
class ProductoFisico(
    nombre: String,
    precioBase: Double,
    val cantidad: Int,
    val costoEnvio: Double
) : ElementoCarrito(nombre, precioBase) {

    override fun calcularImporte(): Double {
        return (precioBase * cantidad) + costoEnvio
    }
}

class ServicioDigital(
    nombre: String,
    precioBase: Double,
    val mesesSuscripcion: Int
) : ElementoCarrito(nombre, precioBase) {

    override fun calcularImporte(): Double {
        val importeSinDescuento = precioBase * mesesSuscripcion
        val descuento = 0.10
        return importeSinDescuento * (1 - descuento)
    }
}

// --- ENCAPSULAMIENTO ---
class Carrito {
    private val elementos = mutableListOf<ElementoCarrito>()

    fun agregarElemento(elemento: ElementoCarrito) {
        elementos.add(elemento)
    }

    fun calcularSubtotal(): Double {
        var subtotal = 0.0
        for (elemento in elementos) {
            subtotal += elemento.calcularImporte()
        }
        return subtotal
    }

    fun calcularIGV(subtotal: Double): Double {
        return subtotal * 0.18
    }

    fun calcularTotal(subtotal: Double, igv: Double): Double {
        return subtotal + igv
    }
}