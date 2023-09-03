package com.digital.yazman.ah.admin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.digital.yazman.ah.activities.AllTexts
import com.digital.yazman.ah.activities.NotificationCard
import com.digital.yazman.ah.activities.fontFamily
import com.digital.yazman.ah.activities.menuActivity
import com.digital.yazman.ah.ui.theme.DigitalYazmanTheme
import com.digital.yazman.ah.classes.NotificationClass
import com.digital.yazman.ah.nonScaledSp
import com.google.firebase.firestore.FirebaseFirestore
import com.valentinilk.shimmer.shimmer
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class UpdateAdmin : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            var darkValue = getIntent().getBooleanExtra("dark", false)
            var dark by remember {
                mutableStateOf(darkValue)
            }
            DigitalYazmanTheme {
                // A surface container using the 'background' color from the theme
                UpdateData(
                    dark,
                    "App Update",
                    context,
                    endActivity = {
                        finish()
                    }
                )
            }
        }
    }
}

@Composable
fun UpdateData(dark: Boolean, collectionName: String, context: Context, endActivity: () -> Unit) {

    var update = "Update"

    var title by remember {
        mutableStateOf("Update Available")
    }
    var shortDescription by remember {
        mutableStateOf("")
    }

    var version by remember {
        mutableStateOf("")
    }
    var link by remember {
        mutableStateOf("https://dy.alihamza.me")
    }

    val data = hashMapOf(
        "title" to title.trim(),
        "shortDes" to shortDescription.trim(),
        "version" to version.trim(),
        "link" to link.trim()
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFADD8E6))
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = collectionName,
            color = Color(0xFF000000),
            fontSize = 20.nonScaledSp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 20.dp, top = 10.dp)
        )

        OutlinedTextField(
            value = title, onValueChange = {
                null
            },
            label = { Text(text = "Title") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
            ),
            textStyle = TextStyle.Default.copy(
                fontFamily = fontFamily,
                fontWeight = FontWeight.SemiBold,
            )
        )

        OutlinedTextField(
            value = shortDescription, onValueChange = {
                shortDescription = it
            },
            label = { Text(text = "Short Description") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
            ),
            textStyle = TextStyle.Default.copy(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
            )
        )


        OutlinedTextField(
            value = version, onValueChange = {
                version = it
            },
            label = { Text(text = "Version") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
            ),
            textStyle = TextStyle.Default.copy(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
            )
        )

        OutlinedTextField(
            value = link, onValueChange = {
                link = it
            },
            label = { Text(text = "Link") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
            ),
            textStyle = TextStyle.Default.copy(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
            )
        )


        Button(
            onClick = {
                val db = FirebaseFirestore.getInstance()
                val collection = db.collection(collectionName)
                if (shortDescription != "" && version != "" && link != "") {
                    collection.document(update).set(data)
                        .addOnSuccessListener {
                            shortDescription = "";
                            version = "";
                            link = "";
                        }
                    context.startActivity(Intent(context, Admin::class.java).putExtra("dark", dark))
                    endActivity()
                }
            },
            shape = RoundedCornerShape(3.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color(0xFF800080))
        ) {
            Text(
                text = "Upload", fontSize = 20.nonScaledSp, color = Color(0xFFFFFFFF),
                fontFamily = fontFamily,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}