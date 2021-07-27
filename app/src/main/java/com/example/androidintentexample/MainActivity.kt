package com.example.androidintentexample

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.provider.AlarmClock
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidintentexample.ui.theme.AndroidIntentExampleTheme
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidIntentExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Greeting("Here is MainActivity")
                        ExplicitIntentNavigation(
                            modifier = Modifier.padding(top = 64.dp)
                        )
                        ImplicitIntentNavigation(
                            modifier = Modifier.padding(top = 64.dp)
                        )
                    }
                }
            }
        }

        // register broadcast receivers
        registerReceiver(
            AirplaneModeBroadcastReceiver(),
            IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        )
    }
}

@Composable
fun Greeting(name: String) {
    Text(
        text = "Hello $name!",
        fontSize = 24.sp
    )
}

@Composable
fun ExplicitIntentNavigation(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val textState = remember { mutableStateOf(TextFieldValue()) }

    Text(
        text = "An example of explicit intent",
        modifier = modifier,
    )
    TextField(
        value = textState.value,
        onValueChange = { textState.value = it },
        modifier = Modifier.padding(top = 16.dp),
        label = { Text("The message will be sent to the activity") },
        maxLines = 1,
    )
    Button(
        onClick = {
            val intent = Intent(
                context,
                FooActivity::class.java
            ).apply {
                putExtra(Intent.EXTRA_TEXT, textState.value.text)
            }
            context.startActivity(intent)
        },
        modifier = Modifier.padding(top = 16.dp)
    ) {
        Text(text = "Go to another activity")
    }
}

@Composable
fun ImplicitIntentNavigation(
    modifier: Modifier = Modifier
) {
    val activity = LocalContext.current as AppCompatActivity
    val timePicker = MaterialTimePicker.Builder()
        .setTimeFormat(TimeFormat.CLOCK_12H)
        .setTitleText("Select Alarm time")
        .build()
    timePicker.addOnPositiveButtonClickListener {
        val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_HOUR, timePicker.hour)
            putExtra(AlarmClock.EXTRA_MINUTES, timePicker.minute)
        }
        activity.startActivity(intent)
    }

    Text(
        text = "An example of implicit intent",
        modifier = modifier,
    )
    Button(
        onClick = {
            timePicker.show(activity.supportFragmentManager, "tag")
        },
        modifier = Modifier.padding(top = 16.dp)
    ) {
        Text(text = "Set Alarm")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AndroidIntentExampleTheme {
        Greeting("Android")
    }
}
