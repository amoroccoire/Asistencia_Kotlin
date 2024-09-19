package com.example.asistencia

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.asistencia.databinding.ActivityMainBinding

class SecondActivity : AppCompatActivity() {
    private val TAG = "SecondActivity"
    private val FILENAME = "data.txt"
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)

        //vinculacion del layout con la actividad usando el binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //datos default
        var nombre = ""
        var apellido = ""
        var correo = ""
        var telefono = ""
        var gSangre = ""

        //Comienzo del acceso a los componentes del layout
        binding.registrar.setOnClickListener {
            nombre = binding.nombre.text.toString()
            apellido = binding.apellido.text.toString()
            correo = binding.correo.text.toString()
            telefono = binding.telefono.text.toString()
            gSangre = binding.sangre.text.toString()

            if (nombre == "" || apellido == "" || correo == "" || telefono == "" || gSangre == "") {
                Toast.makeText(applicationContext, "Llene todos los campos", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "Error en la extraccion de datos")
            }
            else {
                //crear objeto usuario
                val usuario = Usuario(nombre, apellido, correo, telefono, gSangre)
                saveUser(this, usuario, FILENAME)
                Toast.makeText(applicationContext, "Registro guardado", Toast.LENGTH_SHORT).show()
            }
        }

        //lectura del archivo generado
        binding.mostrar.setOnClickListener {
            readUsers(this, FILENAME)
            Toast.makeText(applicationContext, "Registros mostrados", Toast.LENGTH_SHORT).show()
        }
    }

    fun saveUser(context: Context, user: Usuario, fileName: String) {
        try {
            var fileOutputStream = context.openFileOutput(fileName, Context.MODE_APPEND)
            //guardar objeto
            fileOutputStream.write((user.toString()+"\n").toByteArray())
            fileOutputStream.close()
            Toast.makeText(applicationContext, "Registro guardado exitosamente", Toast.LENGTH_SHORT).show()

        } catch (e: Exception) {
            Log.d(TAG, "Error al guardar datos")
            e.printStackTrace()
        }
    }

    fun readUsers(context: Context, filename: String) {
        try {

            var fileInputStream = context.openFileInput(filename)
            var inputStreamReader = fileInputStream.bufferedReader()

            val content = inputStreamReader.use { it.readText() }
            fileInputStream.close()
            Log.d(TAG, content)

        } catch (e: Exception) {
            Log.d(TAG, "Error al leer datos")
            e.printStackTrace()
        }
    }
}