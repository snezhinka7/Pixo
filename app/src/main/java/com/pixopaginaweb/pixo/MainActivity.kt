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

class MainActivity : AppCompatActivity() {
    // Declaración de las variables globales
    private lateinit var iniciarSesionBtn: Button
    private lateinit var correoInput: EditText
    private lateinit var contrasenaInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Inicialización de las vistas
        iniciarSesionBtn = findViewById(R.id.iniciar_sesion_btn)
        correoInput = findViewById(R.id.ingreso_correo_input)
        contrasenaInput = findViewById(R.id.ingreso_contrasena_input)

        // Manejo de insets para la barra de sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

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
        // Listener para el botón de iniciar sesión
        iniciarSesionBtn.setOnClickListener {
            if (correoInput.text.isNotEmpty() && contrasenaInput.text.isNotEmpty()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    correoInput.text.toString(), contrasenaInput.text.toString()
                ).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        showHome(correoInput.text.toString())
                    } else {
                        showAlert()
                    }
                }
            } else {
                showAlert()
            }
        }
    }

    private fun showAlert(message: String = "Se ha producido un error autenticando el usuario") {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage(message)
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(correoInput: String) {
        // Lógica para mostrar la pantalla principal después de la autenticación exitosa
        val homeIntent = Intent(this, HomeActivity::class.java).apply {
            putExtra("email", correoInput)
        }
        startActivity(homeIntent)
    }
}
