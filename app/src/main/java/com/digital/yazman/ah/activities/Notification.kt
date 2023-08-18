package com.digital.yazman.ah.activities

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.digital.yazman.ah.R
import com.digital.yazman.ah.admin.Admin
import com.digital.yazman.ah.ui.theme.DigitalYazmanTheme
import com.digital.yazman.ah.classes.BusinessesClass
import com.digital.yazman.ah.classes.NotificationClass
import com.digital.yazman.ah.classes.ServicesClass
import com.digital.yazman.ah.datastore.StoreLightDarkData
import com.google.firebase.firestore.FirebaseFirestore
import com.valentinilk.shimmer.shimmer
import kotlinx.coroutines.tasks.await

class Notification : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            var id = getIntent().getStringExtra("id").toString()
            var userId = getIntent().getStringExtra("userId").toString()
            var darkValue = getIntent().getBooleanExtra("dark", false)
            var dark by remember {
                mutableStateOf(darkValue)
            }
            var backgroundColor = Color(0xFFADD8E6)
            val db = FirebaseFirestore.getInstance()
            val itemsState = remember { mutableStateOf(emptyList<NotificationClass>()) }

            val notification = intent.getStringExtra("notification")
            DigitalYazmanTheme {
                // A surface container using the 'background' color from the theme
                BackHandler(enabled = true, onBack = {
                    context.startActivity(Intent(this@Notification, menuActivity::class.java).putExtra("dark", dark))
                    finish()
                })
                if (dark) {
                    backgroundColor = Color(0xFF14141f)
                }

                Toast.makeText(context, "${userId}", Toast.LENGTH_SHORT).show()

                db.collection("Users").document(userId).update(
                    mapOf(
                        "notify" to id.trim()
                    )
                ).addOnSuccessListener {
                    Toast.makeText(context, "done", Toast.LENGTH_SHORT).show()
                }


                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            backgroundColor
                        )

                ) {
                    AllTexts(
                        "Notifications",
                        fontSize = 25,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 15.dp, start = 20.dp), dark = dark
                    )

                    LaunchedEffect(Unit) {

                        val querySnapshot = db.collection("Notification").get().await()
                        val userDataList = mutableListOf<NotificationClass>()
                        for (document in querySnapshot) {
                            val id = document.getString("id") ?: ""
                            val title = document.getString("title") ?: ""
                            val shortDes = document.getString("shortDes") ?: ""
                            val date = document.getString("date") ?: ""
                            val link = document.getString("link") ?: ""
                            userDataList.add(
                                NotificationClass(
                                    id, title, shortDes, date, link
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
                                link = "",
                                context = applicationContext, dark = dark

                            )
                        }
                    }

                    val sortedItems = itemsState.value.sortedBy { it.date }

                    LazyColumn {
                        items(sortedItems) { data ->
                            NotificationCard(
                                modifier = Modifier,
                                title = data.title,
                                shortDes = data.shortDes,
                                date = data.date,
                                link = data.link,
                                context = applicationContext, dark = dark
                            )
                        }
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
    link: String,
    dark: Boolean,
    context: Context
) {
    var cardBackgroundColor = Color(0xFFFFFFFF)
    if (dark) {
        cardBackgroundColor = Color(0xFF282834)
    }
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = cardBackgroundColor
        ),
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 5.dp)
            .fillMaxSize(),

        ) {
        Column(modifier = modifier
            .fillMaxWidth()
            .clickable {
                if (link != "") {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent)
                }

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
                        modifier = modifier.padding(0.dp), dark = dark
                    )
                    AllTexts(
                        shortDes,
                        fontSize = 13,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Normal,
                        modifier = modifier.padding(0.dp), dark = dark
                    )
                    Row {
                        Spacer(modifier = modifier.weight(1f))
                        AllTexts(
                            date,
                            fontSize = 11,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Normal,
                            modifier = modifier, dark = dark
                        )
                    }
                }

            }


        }
    }
}