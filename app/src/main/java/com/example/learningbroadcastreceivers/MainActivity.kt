package com.example.learningbroadcastreceivers

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private lateinit var customReceiver: MyCustomReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        customReceiver = MyCustomReceiver()

        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED)
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED)

        this.registerReceiver(customReceiver, intentFilter)
    }

    override fun onDestroy() {
        this.unregisterReceiver(customReceiver)
        super.onDestroy()
    }
}