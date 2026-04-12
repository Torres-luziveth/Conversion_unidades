package com.example.parcialmovil

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val etNombre = findViewById<EditText>(R.id.etFirstName)
        val etApellido = findViewById<EditText>(R.id.etLastName)
        val etCorreo = findViewById<EditText>(R.id.etEmail)
        val etContrasena = findViewById<EditText>(R.id.etPassword)
        val etConfirmar = findViewById<EditText>(R.id.etConfirmPassword)
        val btnRegistrar = findViewById<Button>(R.id.btnRegister)

        btnRegistrar.setOnClickListener {

            val nombre = etNombre.text.toString().trim()
            val apellido = etApellido.text.toString().trim()
            val correo = etCorreo.text.toString().trim()
            val contrasena = etContrasena.text.toString().trim()
            val confirmar = etConfirmar.text.toString().trim()

            if (nombre.isNotEmpty() && apellido.isNotEmpty() &&
                correo.isNotEmpty() && contrasena.isNotEmpty() && confirmar.isNotEmpty()) {

                if (contrasena == confirmar) {

                    Toast.makeText(this, "Registro exitoso 👌", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, ConverterActivity::class.java)
                    intent.putExtra("nombreUsuario", nombre)
                    startActivity(intent)
                    finish()

                } else {
                    Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}