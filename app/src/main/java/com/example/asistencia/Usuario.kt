package com.example.asistencia

data class Usuario(val nombres: String, val apellidos: String, val correo: String, val telefono: String, val grupoSanguineo: String) {
    override fun toString(): String {
        return "\nNombres: ${nombres}\nApellidos: ${apellidos}\nCorreo: ${correo}\nTelefono: ${telefono}\nGrupo Sanguineo: ${grupoSanguineo}"
    }
}
