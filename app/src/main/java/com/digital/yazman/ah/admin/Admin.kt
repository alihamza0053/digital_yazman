package com.digital.yazman.ah.admin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.digital.yazman.ah.R
import com.digital.yazman.ah.activities.AllTexts
import com.digital.yazman.ah.activities.fontFamily
import com.digital.yazman.ah.activities.menuActivity
import com.digital.yazman.ah.classes.NotificationClass
import com.digital.yazman.ah.nonScaledSp
import com.digital.yazman.ah.ui.theme.DigitalYazmanTheme
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class Admin : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            val darkValue = getIntent().getBooleanExtra("dark", false)
            val name = getIntent().getStringExtra("name")
            val email = getIntent().getStringExtra("email")
            val verify = getIntent().getStringExtra("verify")
            val id = getIntent().getStringExtra("id")

            val db = FirebaseFirestore.getInstance()
            var userNumber by remember {
                mutableStateOf(0)
            }
            var userId by remember {
                mutableStateOf(0)
            }
            var businessesNumber by remember {
                mutableStateOf(0)
            }
            var businessesId by remember {
                mutableStateOf(0)
            }
            var adsNumber by remember {
                mutableStateOf(0)
            }
            var adsId by remember {
                mutableStateOf(0)
            }
            var serviceNumber by remember {
                mutableStateOf(0)
            }
            var serviceId by remember {
                mutableStateOf(0)
            }
            var dark by remember {
                mutableStateOf(darkValue)
            }
            var backgroundColor = Color(0xFFADD8E6)
            var textColor = Color(0xFFADD8E6)
            val context = LocalContext.current
            DigitalYazmanTheme {
                // A surface container using the 'background' color from the theme
                if (dark) {
                    backgroundColor = Color(0xFF14141f)
                    textColor = Color(0xFFFFFFFF)
                } else {
                    backgroundColor = Color(0xFFADD8E6)
                    textColor = Color(0xFF000000)
                }
                BackHandler(enabled = true, onBack = {
                    context.startActivity(
                        Intent(
                            this@Admin,
                            menuActivity::class.java
                        ).putExtra("dark", dark)
                    )
                    finish()
                })

                db.collection("Users").get().addOnSuccessListener { notifyResults ->
                    for (document in notifyResults) {
                        userId = document.get("id").toString()
                            .subSequence(3, document.get("id").toString().length).toString().toInt()
                        if (userNumber <= userId) {
                            userNumber = userId
                        }
                    }
                }

                db.collection("Businesses").get().addOnSuccessListener { notifyResults ->
                    for (document in notifyResults) {
                        businessesId = document.get("id").toString()
                            .subSequence(3, document.get("id").toString().length).toString().toInt()
                        if (businessesNumber <= businessesId) {
                            businessesNumber = businessesId
                        }
                    }
                }

//                db.collection("Users").get().addOnSuccessListener { notifyResults ->
//                    for (document in notifyResults) {
//                        adsId = document.get("id").toString()
//                            .subSequence(3, document.get("id").toString().length).toString().toInt()
//                        if (adsNumber <= adsId) {
//                            adsNumber = adsId
//                        }
//                    }
//                }

                db.collection("Services").get().addOnSuccessListener { notifyResults ->
                    for (document in notifyResults) {
                        serviceId = document.get("id").toString()
                            .subSequence(3, document.get("id").toString().length).toString().toInt()
                        if (serviceNumber <= serviceId) {
                            serviceNumber = serviceId
                        }
                    }
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .fillMaxSize()
                        .background(backgroundColor)
                ) {
                    Text(
                        text = name.toString(),
                        fontSize = 20.nonScaledSp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Bold,
                        color = textColor,
                        modifier = Modifier.padding(top = 10.dp)
                    )

                    allData(
                        context = context,
                        users = userNumber.toString(),
                        business = businessesNumber.toString(),
                        ads = adsNumber.toString(),
                        services = serviceNumber.toString(),
                        dark = dark
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                20.dp
                            )
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        Hexagon(
                            "Profile",
                            modifier = Modifier.clickable {
                                context.startActivity(
                                    Intent(context, AdminProfile::class.java)
                                        .putExtra("dark", dark)
                                        .putExtra("name", name)
                                        .putExtra("email", email)
                                        .putExtra("verify", verify)
                                        .putExtra("id", id)
                                )
                            },
                            dark = dark
                        )
                        Spacer(modifier = Modifier.weight(1f))
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                20.dp
                            )
                    ) {
                        Hexagon(
                            "Business",
                            modifier = Modifier.clickable {
                                context.startActivity(
                                    Intent(context, BusinessesAdmin::class.java)
                                )
                            },
                            dark = dark
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Hexagon(
                            "Local Deals",
                            modifier = Modifier.clickable {
                                context.startActivity(
                                    Intent(context, LocalDealsAdmin::class.java)
                                )
                            },
                            dark = dark
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                20.dp
                            )
                    ) {

                        Hexagon(
                            "Local News",
                            modifier = Modifier.clickable {
                                context.startActivity(
                                    Intent(context, LocalNewsAdmin::class.java)
                                )
                            },
                            dark = dark
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Hexagon(
                            "MS ads",
                            modifier = Modifier.clickable {
                                context.startActivity(
                                    Intent(context, AdsAdmin::class.java).putExtra("dark", dark)
                                )
                            },
                            dark = dark
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                20.dp
                            )
                    ) {

                        Hexagon(
                            "Notification",
                            modifier = Modifier.clickable {
                                context.startActivity(
                                    Intent(context, NotificationAdmin::class.java)
                                )
                            },
                            dark = dark
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Hexagon(
                            "Opportunities",
                            modifier = Modifier.clickable {
                                context.startActivity(
                                    Intent(context, OpportunitiesAdmin::class.java)
                                )
                            },
                            dark = dark
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                20.dp
                            )
                    ) {

                        Hexagon(
                            "Services",
                            modifier = Modifier.clickable {
                                context.startActivity(
                                    Intent(context, ServicesAdmin::class.java)
                                )
                            },
                            dark = dark
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Hexagon(
                            "Transport",
                            modifier = Modifier.clickable {
                                context.startActivity(
                                    Intent(context, TransportAdmin::class.java)
                                )
                            },
                            dark = dark
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                20.dp
                            )
                    ) {

                        Hexagon(
                            "Event Gallery",
                            modifier = Modifier.clickable {
                                context.startActivity(
                                    Intent(context, EventGalleryAdmin::class.java)
                                )
                            },
                            dark = dark
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Hexagon(
                            "Update",
                            modifier = Modifier.clickable {
                                context.startActivity(
                                    Intent(context, UpdateAdmin::class.java)
                                )
                            },
                            dark = dark
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Hexagon(text: String, modifier: Modifier, dark: Boolean) {
    var boxColor = Color(0xFFFFFFFF)
    if (dark) {
        boxColor = Color(0xFF282834)
    }
    Box(
        modifier = modifier
            .clip(CutCornerShape(8.dp))
            .background(boxColor)
            .height(70.dp)
            .width(130.dp),
        contentAlignment = Alignment.Center
    ) {
        AllTexts(
            text = text,
            fontSize = 16,
            fontWeight = FontWeight.Medium,
            dark = dark
        )
    }
}


@Composable
fun allData(context:Context, users: String, business: String, ads: String, services: String, dark: Boolean) {

    var cardColor = Color(0xFFFFFFFF)
    var textColor = Color(0xFF000000)
    if (dark) {
        cardColor = Color(0xFF282834)
        textColor = Color(0xFFFFFFFF)
    }
    Card(
        modifier = Modifier.padding(16.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = cardColor
        )
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Column {
                Row {
                    AllTexts(
                        text = "Users",
                        fontSize = 12,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(0.dp).clickable {
                          context.startActivity(Intent(context,UserDataViewAdmin::class.java).putExtra("dark", dark))
                        },
                        dark = dark
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    AllTexts(
                        text = "Ads",
                        fontSize = 12,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(0.dp),
                        dark = dark
                    )
                }
                Row {
                    AllTexts(
                        text = users,
                        fontSize = 12,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(0.dp),

                        dark = dark
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    AllTexts(
                        text = ads,
                        fontSize = 12,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(0.dp),
                        dark = dark
                    )
                }
            }

            Spacer(modifier = Modifier.height(15.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AllTexts(
                    text = "Revenue",
                    fontSize = 12,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(0.dp),
                    dark = dark
                )
                AllTexts(
                    text = "00",
                    fontSize = 12,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(0.dp),
                    dark = dark
                )
            }
            Spacer(modifier = Modifier.height(15.dp))

            Column {
                Row {
                    AllTexts(
                        text = "Businesses",
                        fontSize = 12,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(0.dp),
                        dark = dark
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    AllTexts(
                        text = "Services",
                        fontSize = 12,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(0.dp),
                        dark = dark
                    )
                }
                Row {
                    AllTexts(
                        text = business,
                        fontSize = 12,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(0.dp),

                        dark = dark
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    AllTexts(
                        text = services,
                        fontSize = 12,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(0.dp),
                        dark = dark
                    )
                }
            }
        }
    }
}