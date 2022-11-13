package com.proyectgrupo9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.widget.Button
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Activity_Menu : AppCompatActivity() {
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        supportFragmentManager.beginTransaction().add(R.id.contenedorMenu, Fragment_home()).commit()

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
                    // Agarramos el email
                    val email = intent.getStringExtra("email")

                    // Recuperamos los datos guardados
                    val manager = supportFragmentManager
                    val tranferencia = manager.beginTransaction()
                    val fragmento = Fragment_cuenta()
                    val dataBundle = Bundle()
                    db.collection("Usuarios").document(email.toString()).get().addOnSuccessListener {
                        val nombre = it.get("Nombre") as String?
                        val telefono = it.get("Teléfono") as String?
                        val contraseña = it.get("Contraseña") as String?

                        dataBundle.putString("nombre", nombre)
                        dataBundle.putString("correo", email)
                        dataBundle.putString("telefono", telefono)
                        dataBundle.putString("contraseña", contraseña)

                        fragmento.arguments = dataBundle
                        tranferencia.add(R.id.contenedorMenu, fragmento).commit()
                    }

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