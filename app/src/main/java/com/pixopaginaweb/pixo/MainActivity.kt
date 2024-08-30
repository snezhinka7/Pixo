package com.pixopaginaweb.pixo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var ingresarUsuarioInput: EditText
    lateinit var ingresarContrasenaInput: EditText
    lateinit var iniciarSesionBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ingresarUsuarioInput = findViewById(R.id.ingreso_usuario_input)
        ingresarContrasenaInput = findViewById(R.id.ingreso_contrasena_input)
        iniciarSesionBtn = findViewById(R.id.iniciar_sesion_btn)


    }
}

