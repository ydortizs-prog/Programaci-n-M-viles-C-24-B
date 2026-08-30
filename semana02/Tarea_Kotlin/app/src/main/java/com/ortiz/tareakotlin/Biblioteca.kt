package com.ortiz.tareakotlin

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit


val formato = DateTimeFormatter.ofPattern("dd/MM/yyyy")


const val MULTA_POR_DIA = 1.0


data class Prestamo(
    val titulo: String,
    val tipoUsuario: String,
    val fechaPrestamo: LocalDate,
    val fechaEntrega: LocalDate,
    val fechaDevolucion: LocalDate
) {

    fun diasDeAtraso(): Long {
        val dias = ChronoUnit.DAYS.between(fechaEntrega, fechaDevolucion)
        return if (dias > 0) dias else 0
    }


    fun calcularEstado(): String {
        val dias = diasDeAtraso()
        return if (dias <= 0) {
            "Devuelto a tiempo"
        } else {
            "Devuelto con $dias dia(s) de atraso"
        }
    }


    fun mostrarDetalleMulta() {
        val dias = diasDeAtraso()
        if (dias <= 0) {
            println("Sin multa (devuelto a tiempo)")
            return
        }

        println("\n=== DETALLE DE MULTA: $titulo ===")
        println("Dia  | Fecha       | Multa dia | Acumulado")

        var acumulado = 0.0
        for (i in 1..dias) {
            val fechaDia = fechaEntrega.plusDays(i)
            acumulado += MULTA_POR_DIA
            println(
                "%-4d | %-11s | S/ %.2f   | S/ %.2f".format(
                    i, fechaDia.format(formato), MULTA_POR_DIA, acumulado
                )
            )
        }
        println("\nMulta total: S/ %.2f".format(acumulado))
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

        print("Fecha de prestamo