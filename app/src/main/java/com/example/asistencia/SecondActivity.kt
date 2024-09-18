package com.example.asistencia

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.asistencia.databinding.ActivityMainBinding
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.FileNameMap

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
            }
        }

        //lectura del archivo generado
        binding.mostrar.setOnClickListener {
            val usuarios = readUsers(this, FILENAME)
            usuarios.forEach { user -> Log.d(TAG, user.toString())}
        }
    }

    fun saveUser(context: Context, user: Usuario, fileName: String) {
        try {
            val fileOutputStream = context.openFileOutput(fileName, Context.MODE_APPEND)
            val objectOutputStream = ObjectOutputStream(fileOutputStream)

            // guardar objeto
            objectOutputStream.writeObject(user)
            objectOutputStream.close()
            fileOutputStream.close()
        } catch (e: Exception) {
            Log.d(TAG, "Error al guardar datos")
            e.printStackTrace()
        }
    }

    fun readUsers(context: Context, filename: String) : List<Usuario>{
        val usuarios = mutableListOf<Usuario>()
        try {
            val fileInputStream = context.openFileInput(filename)
            val objectInputStream = ObjectInputStream(fileInputStream)

            // leer objetos
            while (fileInputStream.available() > 0) {
                val usuario = objectInputStream.readObject() as Usuario
                usuarios.add(usuario)
            }
            objectInputStream.close()
            fileInputStream.close()
        } catch (e: Exception) {
            Log.d(TAG, "Error al leer datos")
            e.printStackTrace()
        }

        return usuarios
    }
}