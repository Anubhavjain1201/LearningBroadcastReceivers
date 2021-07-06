package com.example.learningbroadcastreceivers

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class MainActivity : AppCompatActivity() {

    private lateinit var customReceiver: MyCustomReceiver

    companion object {
        private const val ACTION_CUSTOM_BROADCAST = BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        customReceiver = MyCustomReceiver()

        //Intent Filter for power
        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED)
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED)

        //Intent Filter for Custom Broadcast
        val intentFilterForLocalCustomBroadcast = IntentFilter(ACTION_CUSTOM_BROADCAST)

        //Intent Filter for Headset
        val intentFilterForHeadset = IntentFilter()
        intentFilterForHeadset.addAction(Intent.ACTION_HEADSET_PLUG)

        findViewById<Button>(R.id.sendBroadcast).setOnClickListener {

            val customIntent = Intent(ACTION_CUSTOM_BROADCAST)
            LocalBroadcastManager.getInstance(this)
                    .sendBroadcast(customIntent)
        }

        //Register all the receivers
        this.registerReceiver(customReceiver, intentFilter)
        LocalBroadcastManager.getInstance(this).registerReceiver(customReceiver, intentFilterForLocalCustomBroadcast)
        this.registerReceiver(customReceiver, intentFilterForHeadset)
    }

    override fun onDestroy() {
        //Unregister all the receivers
        this.unregisterReceiver(customReceiver)
        LocalBroadcastManager.getInstance(this).unregisterReceiver(customReceiver)
        this.unregisterReceiver(customReceiver)
        super.onDestroy()
    }

}