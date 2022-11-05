package com.proyectgrupo9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Activity_Menu : AppCompatActivity() {

    val frag_Cuenta = Fragment_cuenta();
    val frag_Perfil = Fragment_perfil();
    val frag_Resumen = Fragment_resumen();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        supportFragmentManager.beginTransaction().add(R.id.contenedorMenu, frag_Cuenta).commit()

    }

    fun unClick(view: View) {
        val trans = supportFragmentManager.beginTransaction();

        if(view.id == R.id.FragCuenta){
            trans.replace(R.id.contenedorMenu, frag_Cuenta)
        }
        if(view.id == R.id.FragPerfil){
            trans.replace(R.id.contenedorMenu, frag_Perfil)
        }
        if(view.id == R.id.FragResumen){
            trans.replace(R.id.contenedorMenu, frag_Resumen)
        }

        trans.commit()
    }

}