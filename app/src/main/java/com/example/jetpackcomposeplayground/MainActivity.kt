package com.example.jetpackcomposeplayground

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.example.jetpackcomposeplayground.ui.theme.JetpackComposePlaygroundTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposePlaygroundTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val textState = remember { mutableStateOf(TextFieldValue()) }
                        Greeting("Here is MainActivity")
                        TextField(
                            value = textState.value,
                            onValueChange = { textState.value = it },
                            modifier = Modifier.padding(16.dp),
                            maxLines = 1,
                        )
                        Navigation(message = textState.value.text)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun Navigation(message: String) {
    val context = LocalContext.current
    Button(
        onClick = {
            val intent = Intent(
                context,
                FooActivity::class.java
            ).apply {
                putExtra(INTENT_KEY_MESSAGE, message)
            }
            context.startActivity(intent)
        }
    ) {
        Text(text = "Go to FooActivity!")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposePlaygroundTheme {
        Greeting("Android")
    }
}
