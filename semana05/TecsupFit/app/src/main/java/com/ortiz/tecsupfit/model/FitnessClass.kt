package com.ortiz.tecsupfit.model

data class FitnessClass(
    val id: String,
    val name: String,
    val time: String,
    val room: String,
    val duration: String,
    val description: String,
    val availableSpots: Int,
    val totalSpots: Int,
    val filterType: String // "Hoy" o "Esta semana"
)

data class Reservation(
    val id: String,
    val className: String,
    val time: String,
    val status: String // "Confirmada" o "Completada"
)

val sampleClasses = listOf(
    FitnessClass(
        id = "1",
        name = "Yoga funcional",
        time = "7:00 am",
        room = "Sala 2",
        duration = "50 min",
        description = "Clase orientada a la flexibilidad, postura y control respiratorio.",
        availableSpots = 5,
        totalSpots = 15,
        filterType = "Hoy"
    ),
    FitnessClass(
        id = "2",
        name = "Cross Training",
        time = "6:00 pm",
        room = "Sala 1",
        duration = "45 min",
        description = "Entrenamiento funcional de alta intensidad. Cupos limitados.",
        availableSpots = 8,
        totalSpots = 12,
        filterType = "Hoy"
    ),
    FitnessClass(
        id = "3",
        name = "Spinning",
        time = "7:30 pm",
        room = "Sala 3",
        duration = "45 min",
        description = "Entrenamiento cardiovascular sobre bicicleta estática.",
        availableSpots = 10,
        totalSpots = 20,
        filterType = "Esta semana"
    )
)

val sampleReservations = listOf(
    Reservation(
        id = "r1",
        className = "Cross Training",
        time = "Hoy, 6:00 pm",
        status = "Confirmada"
    ),
    Reservation(
        id = "r2",
        className = "Yoga funcional",
        time = "Ayer, 7:00 am",
        status = "Completada"
    )
)