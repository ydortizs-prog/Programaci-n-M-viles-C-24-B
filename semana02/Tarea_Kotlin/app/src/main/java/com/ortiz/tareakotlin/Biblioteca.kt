package com.ortiz.tareakotlin

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

// Formato de fecha que se usara al ingresar por consola: dd/MM/yyyy
val formato = DateTimeFormatter.ofPattern("dd/MM/yyyy")

data class Prestamo(
    val titulo: String,
    val tipoUsuario: String,
    val fechaPrestamo: LocalDate,
    val fechaEntrega: LocalDate,
    val fechaDevolucion: LocalDate
) {
    // Calcula el estado comparando fecha de entrega vs fecha de devolucion
    fun calcularEstado(): String {
        val diasDiferencia = ChronoUnit.DAYS.between(fechaEntrega, fechaDevolucion)
        return if (diasDiferencia <= 0) {
            "Devuelto a tiempo"
        } else {
            "Devuelto con $diasDiferencia dia(s) de atraso"
        }
    }
}

fun main() {
    val listaPrestamos = mutableListOf<Prestamo>()

    println("=== REGISTRO DE PRESTAMOS DE BIBLIOTECA ===")
    println("Las fechas deben ingresarse en formato dd/MM/yyyy (ejemplo: 15/03/2026)")

    var continuar = true
    while (continuar) {
        println("\n--- Nuevo prestamo ---")

        print("Titulo del libro: ")
        val titulo = readLine() ?: ""

        print("Tipo de usuario (estudiante/docente): ")
        val tipoUsuario = readLine() ?: ""

        print("Fecha de prestamo (dd/MM/yyyy): ")
        val fechaPrestamo = LocalDate.parse(readLine(), formato)

        print("Fecha de entrega acordada (dd/MM/yyyy): ")
        val fechaEntrega = LocalDate.parse(readLine(), formato)

        print("Fecha de devolucion real (dd/MM/yyyy): ")
        val fechaDevolucion = LocalDate.parse(readLine(), formato)

        val prestamo = Prestamo(titulo, tipoUsuario, fechaPrestamo, fechaEntrega, fechaDevolucion)
        listaPrestamos.add(prestamo)

        print("\nDesea registrar otro prestamo? (s/n): ")
        val respuesta = readLine()?.lowercase() ?: "n"
        continuar = respuesta == "s"
    }

    println("\n=== LISTA DE PRESTAMOS REGISTRADOS ===")
    for (p in listaPrestamos) {
        println(
            "Libro: ${p.titulo} | Usuario: ${p.tipoUsuario} | " +
                    "Prestamo: ${p.fechaPrestamo.format(formato)} | " +
                    "Entrega: ${p.fechaEntrega.format(formato)} | " +
                    "Devolucion: ${p.fechaDevolucion.format(formato)} | " +
                    "Estado: ${p.calcularEstado()}"
        )
    }
}