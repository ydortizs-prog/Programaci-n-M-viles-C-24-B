package com.ortiz.clinicasalud.model

data class Doctor(
    val id: String,
    val name: String,
    val specialty: String,
    val rating: Double,
    val description: String
)

val sampleDoctors = listOf(
    Doctor("1", "Dra. Ana Torres", "Cardiología", 4.8, "Especialista en arritmias e hipertensión, formación en la Clínica Mayo."),
    Doctor("2", "Dr. Luis Vega", "Pediatría", 4.7, "Atención integral infantil con más de 10 años de experiencia."),
    Doctor("3", "Dra. Rosa Díaz", "Dermatología", 4.3, "Especialista en dermatología clínica y estética.")
)

data class Appointment(
    val doctorName: String,
    val date: String,
    val time: String,
    val status: String
)

val sampleAppointments = listOf(
    Appointment("Dra. Ana Torres", "Viernes 27", "10:30 am", "Confirmada"),
    Appointment("Dr. Luis Vega", "Miércoles 15", "3:00 pm", "Completada")
)