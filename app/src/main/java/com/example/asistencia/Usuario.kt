package com.example.asistencia

import java.io.Serializable

data class Usuario(val nombres: String, val apellidos: String, val correo: String, val telefono: String, val grupoSanguineo: String) : Serializable {
    override fun toString(): String {
        return "Nombres: ${nombres}\nApellidos: ${apellidos}\nCorreo: ${correo}\nTelefono: ${telefono}\nGrupo Sanguineo: ${grupoSanguineo}\n"
    }
}
