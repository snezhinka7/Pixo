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
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class RegistroLoginActivity : AppCompatActivity() {

    private lateinit var registrarBtn: Button
    private lateinit var registroCorreoInput: EditText
    private lateinit var registroContrasenaInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro_login)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializa las vistas
        registrarBtn = findViewById(R.id.registrarse_btn)
        registroCorreoInput = findViewById(R.id.registro_correo_input)
        registroContrasenaInput = findViewById(R.id.registro_contrasena_input)

        // Llama a setup() después de inicializar las vistas
        setup()

        val volverLogin = findViewById<TextView>(R.id.volver_registro_login)
        volverLogin.setOnClickListener {
            volverAlLogin()
        }
    }

    private fun volverAlLogin() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
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
