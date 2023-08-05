package com.digital.yazman.ah.activities

import android.content.ClipDescription
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.digital.yazman.ah.R
import com.digital.yazman.ah.activities.ui.theme.DigitalYazmanTheme
import com.digital.yazman.ah.classes.BusinessesClass
import com.digital.yazman.ah.classes.NotificationClass
import com.digital.yazman.ah.classes.ServicesClass
import com.google.firebase.firestore.FirebaseFirestore
import com.valentinilk.shimmer.shimmer
import kotlinx.coroutines.tasks.await

class Notification : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val db = FirebaseFirestore.getInstance()
            val itemsState = remember { mutableStateOf(emptyList<NotificationClass>()) }
            val notification = intent.getStringExtra("notification")
            DigitalYazmanTheme {
                // A surface container using the 'background' color from the theme
                Toast.makeText(applicationContext, notification, Toast.LENGTH_SHORT).show()

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .background(
                            Color(0xFFADD8E6)
                        )

                ) {
                    AllTexts(
                        "Notifications",
                        fontSize = 25,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 15.dp, start = 20.dp)
                    )

                    LaunchedEffect(Unit) {
                        val querySnapshot = db.collection("Notification").get().await()
                        val userDataList = mutableListOf<NotificationClass>()
                        for (document in querySnapshot) {
                            val id = document.getString("id") ?: ""
                            val title = document.getString("title") ?: ""
                            val shortDes = document.getString("shortDes") ?: ""
                            val date = document.getString("date") ?: ""
                            userDataList.add(
                                NotificationClass(
                                    id, title, shortDes, date,
                                )
                            )

                        }
                        itemsState.value = userDataList
                    }

                    if (itemsState.value.isEmpty()) {
                        repeat(10) {
                            NotificationCard(
                                modifier = Modifier.shimmer(),
                                title = "Title",
                                shortDes = "Short Description",
                                date = "01-01-2000",
                                context = applicationContext
                            )
                        }
                    }

                    itemsState.value.forEach { data ->
                        NotificationCard(
                            modifier = Modifier,
                            title = data.title,
                            shortDes = data.shortDes,
                            date = data.date,
                            context = applicationContext
                        )
                    }

                }
            }
        }
    }
}


@Composable
fun NotificationCard(
    modifier: Modifier,
    title: String,
    shortDes: String,
    date: String,
    context: Context
) {

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 5.dp)
            .fillMaxSize(),

        ) {
        Column(modifier = modifier
            .fillMaxWidth()
            .clickable {
                Toast
                    .makeText(context, title, Toast.LENGTH_SHORT)
                    .show()
            }) {
            Row(
                verticalAlignment = Alignment.CenterVertically, modifier = modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = modifier.padding(10.dp)
                ) {
                    AllTexts(
                        title,
                        fontSize = 15,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.SemiBold,
                        modifier = modifier.padding(0.dp)
                    )
                    AllTexts(
                        shortDes,
                        fontSize = 13,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Normal,
                        modifier = modifier.padding(0.dp)
                    )
                    Row {
                        Spacer(modifier = modifier.weight(1f))
                        AllTexts(
                            date,
                            fontSize = 11,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Normal,
                            modifier = modifier
                        )
                    }
                }

            }


        }
    }
}