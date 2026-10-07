package com.tecsup.mibodega.ui.cliente.modelo

data class Usuario(
    val nombre: String,
    val telefono: String,
    val clave: String,
    val direccion: String,
    val referencia: String
)

object UsuarioRepositorio {
    private val usuarios = mutableListOf<Usuario>()

    // Devuelve false si el teléfono ya estaba registrado
    fun registrar(usuario: Usuario): Boolean {
        if (usuarios.any { it.telefono == usuario.telefono }) return false
        usuarios.add(usuario)
        return true
    }

    // Devuelve true solo si teléfono y clave coinciden con un usuario registrado
    fun validarLogin(telefono: String, clave: String): Boolean =
        usuarios.any { it.telefono == telefono.trim() && it.clave == clave }
}