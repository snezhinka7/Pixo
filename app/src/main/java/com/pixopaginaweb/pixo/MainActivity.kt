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
    // Declaración de las variables globales
    private lateinit var registrarBtn: Button
    private lateinit var registroCorreoInput: EditText
    private lateinit var registroContrasenaInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)  // Inicializa Firebase
        setContentView(R.layout.activity_main)



        // Llama a setup() después de inicializar las vistas
        setup()

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

    private fun setup() {
        title = "Autenticación"
        // Listener para el botón de registrar
        registrarBtn.setOnClickListener {
            if (registroCorreoInput.text.isNotEmpty() && registroContrasenaInput.text.isNotEmpty()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    registroCorreoInput.text.toString(), registroContrasenaInput.text.toString()
                ).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        showHome(registroCorreoInput.text.toString())
                    } else {
                        showAlert("Error al crear la cuenta")
                    }
                }
            } else {
                showAlert("Por favor, complete todos los campos")
            }
        }
    }

    private fun showAlert(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage(message)
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(registroCorreoInput: String) {
        // Lógica para mostrar la pantalla principal después de la autenticación exitosa
        val homeIntent = Intent(this, HomeActivity::class.java).apply {
            putExtra("email", registroCorreoInput)
        }
        startActivity(homeIntent)
    }
}
