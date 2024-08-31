package com.pixopaginaweb.pixo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.FirebaseApp

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)  // Inicializa Firebase
        setContentView(R.layout.activity_main)

        // Configuración del TextView para redirigir al registro
        val irARegistro = findViewById<TextView>(R.id.ir_a_registro)
        irARegistro.setOnClickListener {
            irRegistro()
        }
    }

    private fun irRegistro() {
        // Intención para ir a la actividad de registro
        val intent = Intent(this, RegistroLoginActivity::class.java)
        startActivity(intent)
    }

}
