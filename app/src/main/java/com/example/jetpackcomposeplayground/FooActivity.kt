package com.example.jetpackcomposeplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.example.jetpackcomposeplayground.ui.theme.JetpackComposePlaygroundTheme

class FooActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposePlaygroundTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val message = intent.getStringExtra(INTENT_KEY_MESSAGE).orEmpty()
                    Greeting("FooActivity: $message")
                }
            }
        }
    }
}
