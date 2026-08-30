package com.ortiz.tareakotlin
import java.time.LocalDate


class Prestamo(
    val titulo: String,
    val tipoUsuario: String,
    val fechaPrestamo: LocalDate,
    val fechaEntrega: LocalDate,
    val fechaDevolucion: LocalDate
)