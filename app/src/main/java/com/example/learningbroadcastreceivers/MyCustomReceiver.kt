package com.example.learningbroadcastreceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyCustomReceiver : BroadcastReceiver() {

    companion object {
        private const val ACTION_CUSTOM_BROADCAST = BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST"
    }

    override fun onReceive(context: Context, intent: Intent) {

        when(intent.action){

            Intent.ACTION_POWER_CONNECTED -> {
                Toast.makeText(context, context.getString(R.string.power_connected), Toast.LENGTH_SHORT).show()
            }

            Intent.ACTION_POWER_DISCONNECTED -> {
                Toast.makeText(context, context.getString(R.string.power_disconnected), Toast.LENGTH_SHORT).show()
            }

            ACTION_CUSTOM_BROADCAST -> {
                Toast.makeText(context, context.getString(R.string.custom_broadcast), Toast.LENGTH_SHORT).show()
            }
        }
    }
}