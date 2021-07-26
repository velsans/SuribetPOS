package com.suribetpos.main.ui.view

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import com.suribetpos.R

class PrintActivity : AppCompatActivity() {
    var textSize = 24
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_transaction_footer)
        val intent = Intent()
        intent.setClassName(
            "recieptservice.com.recieptservice",
            "recieptservice.com.recieptservice.service.PrinterService"
        )
        bindService(intent, object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName, service: IBinder) {

            }
            override fun onServiceDisconnected(name: ComponentName) {}
        }, BIND_AUTO_CREATE)
    }
}