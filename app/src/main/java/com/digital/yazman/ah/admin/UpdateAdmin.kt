package com.digital.yazman.ah.admin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.digital.yazman.ah.activities.fontFamily
import com.digital.yazman.ah.admin.ui.theme.DigitalYazmanTheme
import com.digital.yazman.ah.nonScaledSp
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class UpdateAdmin : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun UpdateData(id: String, collectionName: String, context: Context) {

    var id = id

    var title by remember {
        mutableStateOf("")
    }
    var shortDescription by remember {
        mutableStateOf("")
    }

    var date by remember {
        mutableStateOf("")
    }
    var link by remember {
        mutableStateOf("")
    }

    val data = hashMapOf(
        "id" to id.trim(),
        "title" to title.trim(),
        "shortDes" to shortDescription.trim(),
        "date" to date.trim(),
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
            value = id, onValueChange = {
                id = it
            },
            label = { Text(text = "ID") },
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
            value = title, onValueChange = {
                title = it
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

        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        date = dateFormat.format(Date())


        OutlinedTextField(
            value = date, onValueChange = {
                date = it
            },
            label = { Text(text = "Date") },
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
                if (id != "ID") {
                    if (title != "" && shortDescription != "" && date != "" && link != "") {
                        collection.document(id).set(data)
                            .addOnSuccessListener {
                                title = "";
                                shortDescription = "";
                                date = "";
                                link = "";
                                id = id
                            }
                        context.startActivity(Intent(context, Admin::class.java))
                    }
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


@Composable
fun UpdateDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onUpdateClick: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = { Text(text = "Update Apps") },
            text = { Text(text = "Send updates to all apps?") },
            confirmButton = {
                Button(
                    onClick = {
                        onUpdateClick()
                        onDismiss()
                    }
                ) {
                    Text(text = "Update")
                }
            },
            dismissButton = {
                Button(
                    onClick = { onDismiss() }
                ) {
                    Text(text = "Cancel")
                }
            }
        )
    }
}


@Composable
fun MainScreen() {
    var showDialog by remember { mutableStateOf(false) }
    val firestore = FirebaseFirestore.getInstance()

    BackHandler(
        enabled = showDialog,
        onBack = {
            showDialog = false
        }
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                showDialog = true
            }
        ) {
            Text(text = "Show Update Dialog")
        }

        UpdateDialog(
            showDialog = showDialog,
            onDismiss = { showDialog = false },
            onUpdateClick = {
                // Perform Firestore update here
                firestore.collection("apps").document("")
            }
        )
    }
}
