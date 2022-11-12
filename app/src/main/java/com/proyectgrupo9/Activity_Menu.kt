package com.proyectgrupo9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView

class Activity_Menu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        supportFragmentManager.beginTransaction().add(R.id.contenedorMenu, Fragment_home()).commit()

        val bottomNav = findViewById<BottomNavigationView>(R.id.nav_bottom)
        bottomNav.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {

                R.id.account_item -> {
                    val trans = supportFragmentManager.beginTransaction();
                    trans.replace(R.id.contenedorMenu, Fragment_home())
                    trans.commit()
                    true
                }
                R.id.payments_item -> {
                    val trans = supportFragmentManager.beginTransaction();
                    trans.replace(R.id.contenedorMenu, Fragment_pagos())
                    trans.commit()
                    true
                }
                R.id.resume_item -> {
                    val trans = supportFragmentManager.beginTransaction();
                    trans.replace(R.id.contenedorMenu, Fragment_resumen())
                    trans.commit()
                    true
                }

                else -> false
            }
        }

    }


}