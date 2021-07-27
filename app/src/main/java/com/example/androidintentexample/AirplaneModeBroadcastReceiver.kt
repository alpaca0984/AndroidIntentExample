package com.example.androidintentexample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AirplaneModeBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.getBooleanExtra("state", false)) {
            Toast.makeText(context, "You're in airplane mode now!", Toast.LENGTH_SHORT)
                .show()
        }
    }
}
