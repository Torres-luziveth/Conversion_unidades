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
        val etContraseña = findViewById<EditText>(R.id.etPassword)
        val btnRegistrar = findViewById<Button>(R.id.btnRegister)

        btnRegistrar.setOnClickListener {

            val nombre = etNombre.text.toString().trim()
            val apellido = etApellido.text.toString().trim()
            val correo = etCorreo.text.toString().trim()
            val contraseña = etContraseña.text.toString().trim()

            var esValido = true

            if (nombre.isEmpty()) {
                etNombre.error = "Ingrese su nombre"
                esValido = false
            }

            if (apellido.isEmpty()) {
                etApellido.error = "Ingrese su apellido"
                esValido = false
            }

            if (correo.isEmpty()) {
                etCorreo.error = "Ingrese su correo"
                esValido = false
            }

            if (contraseña.isEmpty()) {
                etContraseña.error = "Ingrese su contraseña"
                esValido = false
            }

            if (!esValido) {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Si todo está correcto
            val intent = Intent(this, ConverterActivity::class.java)
            intent.putExtra("nombreUsuario", nombre)
            startActivity(intent)
            finish()
        }
    }
}