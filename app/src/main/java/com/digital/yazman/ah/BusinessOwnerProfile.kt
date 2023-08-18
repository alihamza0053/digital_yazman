package com.digital.yazman.ah

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.digital.yazman.ah.activities.menuActivity
import com.digital.yazman.ah.ui.theme.DigitalYazmanTheme

class BusinessOwnerProfile : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            val darkValue = getIntent().getBooleanExtra("dark", false)

            var dark by remember {
                mutableStateOf(darkValue)
            }
            var backgroundColor = Color(0xFFADD8E6)
            var textColor = Color(0xFFADD8E6)
            DigitalYazmanTheme {
                // A surface container using the 'background' color from the theme
                BackHandler(enabled = true, onBack = {
                    context.startActivity(Intent(this@BusinessOwnerProfile, menuActivity::class.java).putExtra("dark", dark))
                    finish()
                })
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting2("Business Owner")
                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    DigitalYazmanTheme {
        Greeting2("Android")
    }
}