package com.proyectgrupo9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.widget.Button
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class Activity_Menu : AppCompatActivity() {
    var email:String ?= null
    var btnPerfil:Button ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        supportFragmentManager.beginTransaction().add(R.id.contenedorMenu, Fragment_home()).commit()
        email = intent.getStringExtra("email")

        val bottomNav = findViewById<BottomNavigationView>(R.id.nav_bottom)
        bottomNav.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {

                R.id.home_item -> {
                    val trans = supportFragmentManager.beginTransaction();
                    trans.replace(R.id.contenedorMenu, Fragment_home())
                    trans.commit()
                    true
                }
                R.id.resume_item -> {
                    val trans = supportFragmentManager.beginTransaction();
                    trans.replace(R.id.contenedorMenu, Fragment_resumen())
                    trans.commit()
                    true
                }
                R.id.account_item -> {
                    val trans = supportFragmentManager.beginTransaction();
                    trans.replace(R.id.contenedorMenu, Fragment_cuenta())
                    trans.commit()
                    true
                }

                else -> false
            }
        }

    }


}