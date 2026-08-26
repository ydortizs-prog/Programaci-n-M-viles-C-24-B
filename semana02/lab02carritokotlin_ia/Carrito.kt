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

    fun mostrarDetalle() {
        println("--------- DETALLE DEL CARRITO ---------")
        var i = 1
        for (elemento in elementos) {
            val importe = elemento.calcularImporte()
            println(String.format("%d. %-25s S/ %8.2f", i, elemento.nombre, importe))
            i++
        }
        println("----------------------------------------")
    }
}

// --- EJECUCIÓN ---
fun main() {
    println("=========================================")
    println("     CARRITO DE COMPRAS - TIENDA TECSUP")
    println("=========================================")

    val carrito = Carrito()

    val laptop = ProductoFisico(
        nombre = "Laptop HP",
        precioBase = 2500.0,
        cantidad = 1,
        costoEnvio = 50.0
    )

    val streaming = ServicioDigital(
        nombre = "Netflix Premium",
        precioBase = 45.0,
        mesesSuscripcion = 3
    )

    carrito.agregarElemento(laptop)
    carrito.agregarElemento(streaming)

    println("Producto agregado: ${laptop.nombre}")
    println("Servicio agregado: ${streaming.nombre}")
    println()

    carrito.mostrarDetalle()

    val subtotal = carrito.calcularSubtotal()
    val igv = carrito.calcularIGV(subtotal)
    val total = carrito.calcularTotal(subtotal, igv)

    println(String.format("%-20s S/ %8.2f", "Subtotal:", subtotal))
    println(String.format("%-20s S/ %8.2f", "IGV (18%):", igv))
    println(String.format("%-20s S/ %8.2f", "TOTAL A PAGAR:", total))
}