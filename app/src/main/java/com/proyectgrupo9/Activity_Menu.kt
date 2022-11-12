package com.proyectgrupo9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class Activity_Menu : AppCompatActivity() {
    var email:String ?= null

    var btnPerfil:Button ?= null

    val frag_Cuenta = Fragment_cuenta();
    val frag_Perfil = Fragment_perfil();
    val frag_Resumen = Fragment_resumen();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        email = intent.getStringExtra("email")
        supportFragmentManager.beginTransaction().add(R.id.contenedorMenu, frag_Cuenta).commit()

    }

    fun unClick(view: View) {
        val trans = supportFragmentManager.beginTransaction();

        if(view.id == R.id.btnCuenta){
            trans.replace(R.id.contenedorMenu, frag_Cuenta)
        }
        if(view.id == R.id.btnPerfil){

            val manager = supportFragmentManager
            val transaction = manager.beginTransaction()
            val fragmento = Fragment_perfil()

            val datBundle = Bundle()
            datBundle.putString("correo", email)
            fragmento.arguments = datBundle
            transaction.add(R.id.contenedorMenu, fragmento).commit()

            trans.replace(R.id.contenedorMenu, frag_Perfil)
        }
        if(view.id == R.id.btnResumen){
            trans.replace(R.id.contenedorMenu, frag_Resumen)
        }

        trans.commit()
    }

}