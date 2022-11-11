package com.proyectgrupo9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import java.security.Provider

class RegisterNewUser : AppCompatActivity() {
    // Componentes
    var txtTelefono:EditText?=null
    var txtUsuario:EditText?=null
    var txtContraseña:EditText?=null
    var txtMail:EditText?=null
    // Boton registrar
    var btnRegistrar:Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_new_user)

        txtTelefono   = findViewById(R.id.txtTelefono)
        txtUsuario    = findViewById(R.id.txtUsuario)
        txtContraseña = findViewById(R.id.txtContraseña)
        txtMail       = findViewById(R.id.txtCorreo)
        btnRegistrar  = findViewById(R.id.btnRegistrar)

        // Configuracion
        RegistrarUsuario()

    }

    private fun RegistrarUsuario(){
        title = "Registrar usuario"

        btnRegistrar!!.setOnClickListener {
            if(txtTelefono!!.text.isNotEmpty() && txtUsuario!!.text.isNotEmpty() && txtContraseña!!.text.isNotEmpty() && txtMail!!.text.isNotEmpty()){

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(txtMail!!.text.toString(), txtContraseña!!.text.toString()).addOnCompleteListener {
                    if(it.isSuccessful){
                        txtTelefono!!.setText("")
                        txtUsuario!!.setText("")
                        txtContraseña!!.setText("")
                        txtMail!!.setText("")
                        Toast.makeText(this, resources.getString(R.string.messNuevoUsuario), Toast.LENGTH_SHORT).show()

                        // DatosUsuario(it.result?.user?.email?:"", ProviderType.BASIC)

                    }else{Mensaje()}
                }

            }else{
                Toast.makeText(this, resources.getString(R.string.messVacio), Toast.LENGTH_SHORT).show()
            }
        }

    }

    // El provider debe estar en la actividad donde se muestran los datos del usuario
    // private fun DatosUsuario(mail: String, provider:ProviderType){  }

    fun Ingresar(view: View) {
        finish();
    }

    private fun Mensaje(){
        val mess = AlertDialog.Builder(this)
        mess.setTitle("¡¡¡ Error !!!")
        mess.setMessage("Se ha producido un error al registrar usuario")
        mess.setPositiveButton("Aceptar", null)
        val dialog:AlertDialog = mess.create()
        dialog.show()
    }

}