package com.suribetpos.main.ui.playabletickets.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.suribetpos.R

class PlayableTicketActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playable_ticket)
        getSupportActionBar()?.hide()//Ocultar ActivityBar anterior
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottonNav)
        val navController = findNavController(R.id.fragment)
        // for actions
        val appConfiguration =
            AppBarConfiguration(setOf(R.id.pt_SalesFragment, R.id.pt_CommissionFragment))
        setupActionBarWithNavController(navController, appConfiguration)

        bottomNavigationView.setupWithNavController(navController)
    }

    //Navigation Back from fragments or activity
    override fun onSupportNavigateUp(): Boolean {
        val navigate = findNavController(R.id.fragment)
        return navigate.navigateUp() || super.onSupportNavigateUp()
    }

}