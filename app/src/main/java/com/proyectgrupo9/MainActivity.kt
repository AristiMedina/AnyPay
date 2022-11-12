package com.proyectgrupo9

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity: AppCompatActivity() {
    var txtCorreo:EditText?=null
    var txtContraseña:EditText?=null
    var btnIngresar:Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtCorreo      = findViewById(R.id.txtCorreo)
        txtContraseña  = findViewById(R.id.txtContraseña)
        btnIngresar    = findViewById(R.id.btnIngresar)

        IngresarUsuario()

    }

    private fun IngresarUsuario() {
        title = "Ingreso de usuario"

        btnIngresar!!.setOnClickListener {
            if(txtCorreo!!.text.isNotEmpty() && txtContraseña!!.text.isNotEmpty()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(txtCorreo!!.text.toString(), txtContraseña!!.text.toString()).addOnCompleteListener {
                    if(it.isSuccessful){
                        val intent = Intent(this, Activity_Menu::class.java).apply {
                            putExtra("email", txtCorreo!!.text.toString())
                        }
                        startActivity(intent)
                    }else{Mensaje()}
                }
            }else{Toast.makeText(this, resources.getString(R.string.messVacio), Toast.LENGTH_SHORT).show() }
        }
    }

    fun CrearUsuario(view: View) {
        val intento = Intent(this, RegisterNewUser::class.java)
        startActivity(intento)
    }



    private fun Mensaje(){
        val mess = AlertDialog.Builder(this)
        mess.setTitle("¡¡¡ Error !!!")
        mess.setMessage("Se ha producido un error. \nVerifique los datos")
        mess.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = mess.create()
        dialog.show()
    }


}