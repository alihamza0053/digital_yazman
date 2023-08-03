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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.digital.yazman.ah.R
import com.digital.yazman.ah.nonScaledSp
import com.digital.yazman.ah.ui.theme.DigitalYazmanTheme

class Emergency : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            val fontFamily = FontFamily(
                Font(R.font.lexend_black, FontWeight.Bold),
                Font(R.font.lexend_bold, FontWeight.Bold),
                Font(R.font.lexend_extrabold, FontWeight.ExtraBold),
                Font(R.font.lexend_light, FontWeight.Light),
                Font(R.font.lexend_medium, FontWeight.Medium),
                Font(R.font.lexend_extralight, FontWeight.ExtraLight),
                Font(R.font.lexend_regular, FontWeight.Normal),
                Font(R.font.lexend_semibold, FontWeight.SemiBold),
                Font(R.font.lexend_thin, FontWeight.Thin),
            )
            DigitalYazmanTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFADD8E6))
                        .verticalScroll(rememberScrollState())


                ) {
                    Text(
                        text = "Emergency",
                        color = Color(0xFF000000),
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
                                backgroundColor = Color(0xFFFFFFFF),
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
                                    AllTexts("Ambulance", fontSize = 12, fontWeight = FontWeight.Light)
                                }
                            }

                            //first row second card
                            Card(
                                elevation = 6.dp,
                                backgroundColor = Color(0xFFFFFFFF),
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
                                    AllTexts("Blood Donors", fontSize = 12, fontWeight = FontWeight.Light)

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
                                backgroundColor = Color(0xFFFFFFFF),
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
                                    AllTexts("Child Protection", fontSize = 12, fontWeight = FontWeight.Light)

                                }
                            }

                            //second row second card
                            Card(
                                elevation = 6.dp,
                                backgroundColor = Color(0xFFFFFFFF),
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
                                    AllTexts("Fire Brigade", fontSize = 12, fontWeight = FontWeight.Light)

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
                                backgroundColor = Color(0xFFFFFFFF),
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
                                    AllTexts("Gas Leakage", fontSize = 12, fontWeight = FontWeight.Light)

                                }
                            }

                            //second row second card
                            Card(
                                elevation = 6.dp,
                                backgroundColor = Color(0xFFFFFFFF),
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
                                    AllTexts("Police", fontSize = 12, fontWeight = FontWeight.Light)

                                }
                            }
                        }
                    }

                }
            }
        }
    }
}
