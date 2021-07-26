package com.suribetpos.main.ui.commission.ui

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
 * Created by Velmurugan on 2/27/2021.
 */
class CommissionPage : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_commission)
        getSupportActionBar()?.hide()
        toolbar = findViewById(R.id.commission_toolbar)
        setSupportActionBar(toolbar)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.commission_bottonNav)
        val navController = findNavController(R.id.commission_fragment)
        // for actions
        val appConfiguration =
            AppBarConfiguration(
                setOf(
                    R.id.breakupdetailsFragment,
                    R.id.cashoutCommissionFragment,
                    R.id.topupCommissionFragment
                )
            )
        setupActionBarWithNavController(navController, appConfiguration)

        bottomNavigationView.setupWithNavController(navController)
    }

    //Navigation Back from fragments or activity
    override fun onSupportNavigateUp(): Boolean {
        val navigate = findNavController(R.id.commission_fragment)
        return navigate.navigateUp() || super.onSupportNavigateUp()
    }
}