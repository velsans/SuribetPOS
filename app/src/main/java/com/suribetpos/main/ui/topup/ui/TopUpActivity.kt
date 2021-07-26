package com.suribetpos.main.ui.topup.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.suribetpos.R

/**
 * Created by Velmurugan on 2/4/2021.
 */
class TopUpActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topup)
        supportActionBar?.hide()
        toolbar = findViewById(R.id.topup_toolbar)
        setSupportActionBar(toolbar)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.topup_bottonNav)
        val navController = findNavController(R.id.topup_fragment)
        // for actions
        val appConfiguration =
            AppBarConfiguration(setOf(R.id.createVoucherFragment, R.id.cancelledVoucherFragment))
        setupActionBarWithNavController(navController, appConfiguration)

        bottomNavigationView.setupWithNavController(navController)
    }

    //Navigation Back from fragments or activity
    override fun onSupportNavigateUp(): Boolean {
        val navigate = findNavController(R.id.topup_fragment)
        return navigate.navigateUp() || super.onSupportNavigateUp()
    }

}