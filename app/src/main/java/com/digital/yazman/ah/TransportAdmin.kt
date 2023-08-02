package com.digital.yazman.ah

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.digital.yazman.ah.ui.theme.DigitalYazmanTheme
import com.google.firebase.firestore.FirebaseFirestore
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.google.firebase.ktx.Firebase

class TransportAdmin : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DigitalYazmanTheme {
                // A surface container using the 'background' color from the theme
                val db = FirebaseFirestore.getInstance()
                var id by remember {
                    mutableStateOf("ID")
                }
                TransportData(id = "DYT01")
                var idCheck = 0
//                db.collection("Transport").get().addOnSuccessListener {result ->
//                    for (document in result){
//                        idCheck = document.get("id").toString().subSequence(3,document.get("id").toString().length).toString().toInt() + 1
//                        id = "DYT" + idCheck.toString()
//                    }
//                }
            }
        }
    }
}

@Composable
fun TransportData(id:String) {

    var id = id

    var busName by remember {
        mutableStateOf("")
    }
    var busNumber by remember {
        mutableStateOf("")
    }
    var startTime by remember {
        mutableStateOf("")
    }
    var arivalTime by remember {
        mutableStateOf("")
    }
    var startPoint by remember {
        mutableStateOf("")
    }
    var destination by remember {
        mutableStateOf("")
    }
    var ticketPrice by remember {
        mutableStateOf("")
    }
    var distance by remember {
        mutableStateOf("")
    }
    var timeTaken by remember {
        mutableStateOf("")
    }

    val data = hashMapOf(
        "id" to id,
        "busName" to busName.trim(),
        "busNumber" to busNumber.trim(),
        "startTime" to startTime.trim(),
        "arivalTime" to arivalTime.trim(),
        "startPoint" to startPoint.trim(),
        "destination" to destination.trim(),
        "ticketPrice" to ticketPrice.trim() + "\nRs",
        "distance" to distance.trim(),
        "timeTaken" to "Time " + timeTaken.trim(),
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
            text = "Transport",
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
            value = busName, onValueChange = {
                busName = it
            },
            label = { Text(text = "Bus Name") },
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
            value = busNumber, onValueChange = {
                busNumber = it
            },
            label = { Text(text = "Bus Contact Number") },
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
            value = startTime, onValueChange = {
                startTime = it
            },
            label = { Text(text = "Start Time") },
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
            value = arivalTime, onValueChange = {
                arivalTime = it
            },
            label = { Text(text = "Arival Time") },
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
            value = startPoint, onValueChange = {
                startPoint = it
            },
            label = { Text(text = "Start Point") },
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
            value = destination, onValueChange = {
                destination = it
            },
            label = { Text(text = "Destination") },
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
            value = ticketPrice, onValueChange = {
                ticketPrice = it
            },
            label = { Text(text = "Ticket Price") },
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
            value = distance, onValueChange = {
                distance = it
            },
            label = { Text(text = "Distance") },
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
            value = timeTaken, onValueChange = {
                timeTaken = it
            },
            label = { Text(text = "Time Taken") },
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
                val collection = db.collection("Transport")
                if (id != "ID") {
                    if (busName != "" && busNumber != "" && startTime != "" && arivalTime != "" && startPoint != ""&& destination != "" && ticketPrice != "" && distance != "" && timeTaken != "") {
                        collection.document(id).set(data)
                            .addOnSuccessListener {
                                busName = "";
                                busNumber = "";
                                startTime = "";
                                arivalTime = "";
                                startPoint = "";
                                destination = "";
                                ticketPrice = "";
                                distance = "";
                                timeTaken = "";
                                id = id
                            }
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



@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun GreetingPreview() {
    DigitalYazmanTheme {
        TransportData("DYT01")
    }
}