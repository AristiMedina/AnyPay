package com.proyectgrupo9

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text
import java.security.Provider

class RegisterNewUser : AppCompatActivity() {
    val db = FirebaseFirestore.getInstance()

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

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(txtMail?.text.toString(), txtContraseña?.text.toString()).addOnCompleteListener {
                    if(it.isSuccessful){
                        // Guardamos los datos en Firestore
                        Save(txtMail?.text.toString(), txtUsuario?.text.toString(), txtTelefono?.text.toString(), txtContraseña?.text.toString())

                        //datosPerfil(txtMail?.text.toString(), txtUsuario?.text.toString(), txtTelefono?.text.toString(),txtContraseña?.text.toString())
                        txtTelefono!!.setText("")
                        txtUsuario!!.setText("")
                        txtContraseña!!.setText("")
                        txtMail!!.setText("")
                        Toast.makeText(this, resources.getString(R.string.messNuevoUsuario), Toast.LENGTH_SHORT).show()

                    }else{
                        if(txtContraseña!!.length() < 6){
                            Mensaje(resources.getString(R.string.mensajeContraseña))
                        }else{
                            try {
                                // Ver si el correo existe en Firestore
                                db.collection("Usuarios").document(txtMail?.text.toString()).get()
                                    .addOnSuccessListener {
                                        val correo: String = it.get("Correo") as String
                                        //Toast.makeText(this, correo.toString(), Toast.LENGTH_SHORT).show()

                                        if (txtMail?.text.toString() == correo) {
                                            Toast.makeText(
                                                this,
                                                resources.getString(R.string.mensajeCuenta),
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                            }finally {
                                Mensaje(resources.getString(R.string.mensajeConeccion))

                            }
                        }
                    }
                }

            }else{
                Toast.makeText(this, resources.getString(R.string.messVacio), Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun Ingresar(view: View) {
        finish();
    }

    private fun Mensaje(men:String){
        val mess = AlertDialog.Builder(this)
        mess.setTitle("¡¡¡ Error !!!")
        mess.setMessage(men)
        mess.setPositiveButton("Aceptar", null)
        val dialog:AlertDialog = mess.create()
        dialog.show()
    }

    private fun datosPerfil(email:String, nombre:String, telefono:String, contraseña:String){
        val perfilIntent = Intent(this, Activity_Menu::class.java).apply {
            putExtra("Email", email)
            putExtra("Nombre_Usuario", nombre)
            putExtra("Telefono", telefono)
            putExtra("Contraseña", contraseña)
        }
        startActivity(perfilIntent)
    }

    fun Save(email:String, nombre: String, telefono:String, contraseña:String){
        db.collection("Usuarios").document(email).set(hashMapOf(
                "Nombre" to nombre, "Correo" to email, "Teléfono" to telefono, "Contraseña" to contraseña
            )
        )
    }

}