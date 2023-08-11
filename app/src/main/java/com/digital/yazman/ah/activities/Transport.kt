package com.digital.yazman.ah.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.digital.yazman.ah.R
import com.digital.yazman.ah.classes.TransportClass
import com.digital.yazman.ah.datastore.StoreLightDarkData
import com.digital.yazman.ah.nonScaledSp
import com.digital.yazman.ah.ui.theme.DigitalYazmanTheme
import com.google.firebase.firestore.FirebaseFirestore
import com.valentinilk.shimmer.shimmer
import kotlinx.coroutines.tasks.await

class Transport : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            val dataStore = StoreLightDarkData(context)
            val darkBool = dataStore.getDark.collectAsState(initial = false)
            var dark by remember {
                mutableStateOf(false)
            }
            dark = darkBool.value
            var backgroundColor = Color(0xFFADD8E6)
            val db = FirebaseFirestore.getInstance()
            val itemsState = remember {
                mutableStateOf(emptyList<TransportClass>())
            }
            DigitalYazmanTheme {
                // A surface container using the 'background' color from the theme
                if (dark) {
                    backgroundColor = Color(0xFF14141f)
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(backgroundColor)
                        .verticalScroll(rememberScrollState())

                ) {
                    AllTexts(
                        text = "Transportation",
                        fontSize = 25,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 15.dp, start = 20.dp),
                        dark = dark
                    )

                    LaunchedEffect(Unit) {
                        val querySnapshot = db.collection("Transport").get().await()
                        val userDataList = mutableListOf<TransportClass>()
                        for (document in querySnapshot) {
                            val id = document.getString("id") ?: ""
                            val busName = document.getString("busName") ?: ""
                            val busNumber = document.getString("busNumber") ?: ""
                            val startTime = document.getString("startTime") ?: ""
                            val arivalTime = document.getString("arivalTime") ?: ""
                            val startPoint = document.getString("startPoint") ?: ""
                            val destination = document.getString("destination") ?: ""
                            val ticketPrice = document.getString("ticketPrice") ?: ""
                            val distance = document.getString("distance") ?: ""
                            val timeTaken = document.getString("timeTaken") ?: ""
                            userDataList.add(
                                TransportClass(
                                    id,
                                    busName,
                                    busNumber,
                                    startTime,
                                    arivalTime,
                                    startPoint,
                                    destination,
                                    ticketPrice,
                                    distance,
                                    timeTaken
                                )
                            )
                        }
                        itemsState.value = userDataList
                    }

                    if (itemsState.value.isEmpty()) {
                        repeat(10) {
                            Bus(modifier = Modifier.shimmer(),
                                busName = "Bus Name",
                                busNumber = "Contact Number",
                                startTime = "Start Time",
                                arivalTime = "Arival Time",
                                startPoint = "Start Point",
                                destination = "Destination",
                                ticketPrice = "000",
                                distance = "000km",
                                timeTaken = "Time 0 Hours",
                                dark = dark
                            )
                        }
                    }

                    itemsState.value.forEach{data->
                        Bus(modifier= Modifier,
                            busName = data.busName,
                            busNumber = data.busNumber,
                            startTime = data.startTime,
                            arivalTime = data.arivalTime,
                            startPoint = data.startPoint,
                            destination = data.destination,
                            ticketPrice = data.ticketPrice,
                            distance = data.distance,
                            timeTaken = data.timeTaken,
                            dark = dark
                        )
                    }


                }
            }
        }
    }
}


@Composable
fun Bus(
    modifier: Modifier,
    busName: String,
    busNumber: String,
    startTime: String,
    arivalTime: String,
    startPoint: String,
    destination: String,
    ticketPrice: String,
    distance: String,
    timeTaken: String,
    dark: Boolean
) {
    var cardBackgroundColor = Color(0xFFFFFF)
    if(dark){
        cardBackgroundColor = Color(0xFF282834)
    }
    Column(
        modifier = modifier
            .padding(20.dp)
            .fillMaxWidth()
            .background(cardBackgroundColor),
    ) {

        //first row
        Row(
            modifier = modifier
                .padding(start = 20.dp, top = 5.dp, end = 20.dp, bottom = 5.dp)
        ) {
            AllTexts(
                text = busName,
                fontWeight = FontWeight.Normal,
                fontSize = 15,
                modifier = modifier,
                dark = dark
            )
            Spacer(modifier = modifier.weight(1f))
            AllTexts(
                text = busNumber,
                fontWeight = FontWeight.Normal,
                fontSize = 15,
                modifier = modifier,
                dark = dark
            )
        }

        Spacer(
            modifier = modifier
                .background(Color(0xFFADD8E6))
                .fillMaxWidth()
                .height(2.dp)
        )


        //second row
        Row(
            modifier = modifier
                .padding(start = 20.dp, top = 5.dp, end = 20.dp, bottom = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                AllTexts(
                    text = startTime,
                    fontWeight = FontWeight.Medium,
                    fontSize = 15,
                    modifier = modifier
                        .padding(bottom = 17.dp),
                    dark = dark
                )
                AllTexts(
                    text = arivalTime,
                    fontWeight = FontWeight.Medium,
                    fontSize = 15,
                    modifier = modifier,
                    dark = dark
                )
            }
            Image(
                painter = painterResource(id = R.drawable.circles),
                contentDescription = null,
                modifier = modifier
                    .width(55.dp)
                    .height(55.dp)
                    .padding(top = 4.dp)
            )
            Spacer(modifier = modifier.weight(1f))
            Column {
                AllTexts(
                    text = startPoint,
                    fontWeight = FontWeight.Medium,
                    fontSize = 15,
                    modifier = modifier
                        .padding(bottom = 17.dp),
                    dark = dark
                )
                AllTexts(
                    text = destination,
                    fontWeight = FontWeight.Medium,
                    fontSize = 15,
                    modifier = modifier,
                    dark = dark
                )
            }
            Spacer(modifier = modifier.weight(1f))
            Card(
                colors = CardDefaults.cardColors(Color(0xFF800080)),
                shape = RoundedCornerShape(10.dp),
                modifier = modifier

                ) {

                Text(
                    text = ticketPrice + "\nRs",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    fontSize = 15.nonScaledSp,
                    modifier = modifier
                        .padding(10.dp)
                )
            }
        }
        Spacer(
            modifier = modifier
                .background(Color(0xFFADD8E6))
                .fillMaxWidth()
                .height(2.dp)
        )


        //third row
        Row(
            modifier = modifier
                .padding(start = 20.dp, top = 5.dp, end = 20.dp, bottom = 5.dp)
        ) {
            AllTexts(
                text = distance + "km",
                fontWeight = FontWeight.Normal,
                fontSize = 12,
                modifier = modifier,
                dark = dark
            )
            Spacer(modifier = modifier.weight(1f))
            AllTexts(
                text = "Time $timeTaken",
                fontWeight = FontWeight.Normal,
                fontSize = 12,
                modifier = modifier,
                dark = dark
            )
        }
    }
}


