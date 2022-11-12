package com.proyectgrupo9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class RegisterNewUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_new_user)
    }

    fun RegistrarUsuario(view: View) {
        Toast.makeText(this, resources.getString(R.string.messNuevoUsuario), Toast.LENGTH_SHORT).show()
    }

    fun Ingresar(view: View) {
        finish();
    }
}