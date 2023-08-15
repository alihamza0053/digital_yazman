package com.digital.yazman.ah.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.digital.yazman.ah.R
import com.digital.yazman.ah.datastore.StoreLightDarkData
import com.digital.yazman.ah.nonScaledSp
import com.digital.yazman.ah.ui.theme.DigitalYazmanTheme

class Emergency : ComponentActivity() {
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
            var textColor = Color(0xFF000000)
            var cardColor = Color(0xFFFFFFFF)
            var backgroundColor = Color(0xFFADD8E6)
            DigitalYazmanTheme {
                // A surface container using the 'background' color from the theme
                if (dark) {
                    backgroundColor = Color(0xFF14141f)
                    textColor = Color(0xFFFFFFFF)
                    cardColor = Color(0xFF282834)
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(backgroundColor)
                        .verticalScroll(rememberScrollState())


                ) {
                    Text(
                        text = "Emergency",
                        color = textColor,
                        fontSize = 25.nonScaledSp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 15.dp, start = 20.dp)
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 40.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.sos),
                            contentDescription = null,
                            modifier = Modifier
                                .width(200.dp)
                                .height(200.dp)
                                .padding(bottom = 20.dp)
                                .clickable {
                                    context.startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+"1122")))
                                },

                            )


                        //first row with 2 cards
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            //first row first card
                            Card(
                                elevation = 6.dp,
                                backgroundColor = cardColor,
                                modifier = Modifier
                                    .padding(20.dp)
                                    .weight(1f)
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(100.dp)
                                        .weight(1f)
                                        .clickable {
                                            context.startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+"1122")))
                                        },

                                    ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.ambulance),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .height(70.dp)
                                            .width(70.dp)
                                    )
                                    AllTexts("Ambulance", fontSize = 12, fontWeight = FontWeight.Light,dark = dark)
                                }
                            }

                            //first row second card
                            Card(
                                elevation = 6.dp,
                                backgroundColor = cardColor,
                                modifier = Modifier
                                    .padding(20.dp)
                                    .weight(1f)
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(100.dp)
                                        .weight(1f)
                                        .clickable {
                                            Toast
                                                .makeText(
                                                    applicationContext,
                                                    "Blood Donors",
                                                    Toast.LENGTH_SHORT
                                                )
                                                .show()
                                        },

                                    ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.blood_donation),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .height(70.dp)
                                            .width(70.dp)
                                    )
                                    AllTexts("Blood Donors", fontSize = 12, fontWeight = FontWeight.Light, dark = dark)

                                }
                            }
                        }

                        //second row with 2 cards
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            //second row first card
                            Card(
                                elevation = 6.dp,
                                backgroundColor = cardColor,
                                modifier = Modifier
                                    .padding(20.dp)
                                    .weight(1f)
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(100.dp)
                                        .weight(1f)
                                        .clickable {
                                            context.startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+"1121")))
                                        },

                                    ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.child_protection),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .height(70.dp)
                                            .width(70.dp)
                                    )
                                    AllTexts("Child Protection", fontSize = 12, fontWeight = FontWeight.Light, dark = dark)

                                }
                            }

                            //second row second card
                            Card(
                                elevation = 6.dp,
                                backgroundColor = cardColor,
                                modifier = Modifier
                                    .padding(20.dp)
                                    .weight(1f)
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(100.dp)
                                        .weight(1f)
                                        .clickable {
                                            context.startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+"1122")))
                                        },

                                    ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.fire_brigade),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .height(70.dp)
                                            .width(70.dp)
                                    )
                                    AllTexts("Fire Brigade", fontSize = 12, fontWeight = FontWeight.Light, dark = dark)

                                }
                            }
                        }

                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            //second row first card
                            Card(
                                elevation = 6.dp,
                                backgroundColor = cardColor,
                                modifier = Modifier
                                    .padding(20.dp)
                                    .weight(1f)
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(100.dp)
                                        .weight(1f)
                                        .clickable {
                                            context.startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+"1199")))
                                        },

                                    ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.gase_leakage),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .height(70.dp)
                                            .width(70.dp)
                                    )
                                    AllTexts("Gas Leakage", fontSize = 12, fontWeight = FontWeight.Light, dark = dark)

                                }
                            }

                            //second row second card
                            Card(
                                elevation = 6.dp,
                                backgroundColor = cardColor,
                                modifier = Modifier
                                    .padding(20.dp)
                                    .weight(1f)
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(100.dp)
                                        .weight(1f)
                                        .clickable {
                                            context.startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+"15")))
                                        },

                                    ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.policeman),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .height(70.dp)
                                            .width(70.dp)
                                    )
                                    AllTexts("Police", fontSize = 12, fontWeight = FontWeight.Light, dark = dark)

                                }
                            }
                        }
                    }

                }
            }
        }
    }
}
