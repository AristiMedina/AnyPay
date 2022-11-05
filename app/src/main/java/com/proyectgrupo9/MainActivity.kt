package com.proyectgrupo9

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    // Creamos variables para extraer lo que hay en los editText
    private var txtUsuario: EditText? = null
    private var txtContraseña: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Guardamos lo que hay en los editTexts
        txtUsuario = findViewById(R.id.txtUsuario)
        txtContraseña = findViewById(R.id.txtContraseña)
    }

    fun IngresarUsuario(botonIngresar: View) {
        // Verificamos si la informacion es correcta
        if(txtUsuario!!.text.toString() == "Ejemplo"){
            if(txtContraseña!!.text.toString()== "1234"){
                val intento = Intent(this, Activity_Menu::class.java)
                startActivity(intento)
            }else{
                Toast.makeText(this, resources.getString(R.string.messContraseñaIncorrecta), Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this, resources.getString(R.string.messContraseñaIncorrecta), Toast.LENGTH_SHORT).show()
        }
    }

    fun CrearUsuario(view: View) {
        val intento = Intent(this, RegisterNewUser::class.java)
        startActivity(intento)
    }

}