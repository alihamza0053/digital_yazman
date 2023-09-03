package com.digital.yazman.ah.admin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.digital.yazman.ah.activities.fontFamily
import com.digital.yazman.ah.nonScaledSp
import com.digital.yazman.ah.ui.theme.DigitalYazmanTheme
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class LocalDealsAdmin : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var id by remember {
                mutableStateOf("ID")
            }
            val db = FirebaseFirestore.getInstance()
            val context = LocalContext.current
            var darkValue = getIntent().getBooleanExtra("dark", false)
            var dark by remember {
                mutableStateOf(darkValue)
            }
            var nameCheck = 0
            var ids = 0

            db.collection("Local Deals").get().addOnSuccessListener { results ->
                for (document in results) {
                    ids = document.get("id").toString()
                        .subSequence(4, document.get("id").toString().length).toString().toInt()
                    if (nameCheck <= ids) {
                        nameCheck = ids + 1
                        id = "DYLD0" + nameCheck.toString()
                    }
                }
            }

            DigitalYazmanTheme {
                // A surface container using the 'background' color from the theme
                DataToUpload(
                    dark = dark,
                    id = id,
                    "Local Deals",
                    context
                )
            }
        }
    }
}

@Composable
fun DataToUpload(dark: Boolean, id: String, collectionName: String, context: Context,) {

    var id = id

    var category by remember {
        mutableStateOf("")
    }
    var title by remember {
        mutableStateOf("")
    }
    var shortDescription by remember {
        mutableStateOf("")
    }
    var source by remember {
        mutableStateOf("")
    }
    var date by remember {
        mutableStateOf("")
    }

    val data = hashMapOf(
        "id" to id.trim(),
        "category" to category.trim(),
        "title" to title.trim(),
        "shortDes" to shortDescription.trim(),
        "source" to source.trim(),
        "date" to date.trim()
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
            value = category, onValueChange = {
                category = it
            },
            label = { Text(text = "Category") },
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

        OutlinedTextField(
            value = source, onValueChange = {
                source = it
            },
            label = { Text(text = "Source") },
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


        Button(
            onClick = {
                val db = FirebaseFirestore.getInstance()
                val collection = db.collection(collectionName)
                if (id != "ID") {
                    if (category != "" && title != "" && shortDescription != "" && source != "" && date != "") {
                        collection.document(id).set(data)
                            .addOnSuccessListener {
                                category = "";
                                title = "";
                                shortDescription = "";
                                source = "";
                                date = "";
                                id = id
                            }
                        context.startActivity(Intent(context, Admin::class.java).putExtra("dark", dark))
                    }
                }

//                val database = Firebase.database
//                val data = LocalDealNewsOppor(id, category, title, shortDescription, source, date)
//                val myRef = database.getReference("Local Deals")
//                if (id != "ID") {
//                    if (category != "" && title != "" && shortDescription != "" && source != "" && date != "") {
//                        myRef.child(id).setValue(data).addOnSuccessListener {
//                            id = "";
//                            category = "";
//                            title = "";
//                            shortDescription = "";
//                            source = "";
//                            date = "";
//                        }
//                    }
//                }
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