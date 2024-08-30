package com.pixopaginaweb.pixo

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var ingresarUsuarioInput: EditText
    private lateinit var ingresarContrasenaInput: EditText
    private lateinit var iniciarSesionBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ingresarUsuarioInput = findViewById(R.id.ingreso_usuario_input)
        ingresarContrasenaInput = findViewById(R.id.ingreso_contrasena_input)
        iniciarSesionBtn = findViewById(R.id.iniciar_sesion_btn)

        iniciarSesionBtn.setOnClickListener {
            val usuario = ingresarUsuarioInput.text.toString()
            val contrasena = ingresarContrasenaInput.text.toString()
            Log.i("Testeo Inicio Sesion", "Usuario : $usuario and Contrasena : $contrasena")
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }
}

