package com.ortiz.tareakotlin

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

val formato = DateTimeFormatter.ofPattern("dd/MM/yyyy")

data class Prestamo(
    val titulo: String,
    val tipoUsuario: String,
    val fechaPrestamo: LocalDate,
    val fechaEntrega: LocalDate,
    val fechaDevolucion: LocalDate
) {

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
    println("=== REGISTRO DE PRESTAMOS DE BIBLIOTECA ===")
    println("Las fechas deben ingresarse en formato dd/MM/yyyy (ejemplo: 15/03/2026)")

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

    println("\nPrestamo registrado:")
    println("Titulo: ${prestamo.titulo}")
    println("Usuario: ${prestamo.tipoUsuario}")
    println("Fecha prestamo: ${prestamo.fechaPrestamo.format(formato)}")
    println("Fecha entrega: ${prestamo.fechaEntrega.format(formato)}")
    println("Fecha devolucion: ${prestamo.fechaDevolucion.format(formato)}")
}