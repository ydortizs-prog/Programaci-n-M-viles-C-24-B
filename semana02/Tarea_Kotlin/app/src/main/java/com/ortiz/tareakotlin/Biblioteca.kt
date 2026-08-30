package com.ortiz.tareakotlin

import java.time.LocalDate
import java.time.temporal.ChronoUnit

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