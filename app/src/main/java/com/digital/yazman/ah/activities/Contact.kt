package com.digital.yazman.ah.activities

import android.graphics.Paint
import android.graphics.Paint.Align
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.digital.yazman.ah.R
import com.digital.yazman.ah.nonScaledSp
import com.digital.yazman.ah.ui.theme.DigitalYazmanTheme

class Contact : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DigitalYazmanTheme {
                // A surface container using the 'background' color from the theme
                adminContact()
            }
        }
    }
}


@Composable
fun adminContact() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .background(Color(0xFF14141f))
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.weight(6f)

        ) {
            Image(
                painter = painterResource(id = R.drawable.hamza2),
                contentDescription = "Hamza",
                modifier = Modifier
                    .clip(
                        CircleShape
                    )
                    .width(100.dp)
                    .height(100.dp)
            )
            AllTexts(
                color = Color.White,
                text = "Ali Hamza",
                fontWeight = FontWeight.Bold,
                fontSize = 20
            )
            AllTexts(
                color = Color.White,
                text = "Developer",
                fontWeight = FontWeight.Normal,
                fontSize = 15
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(10.dp)
            ) {
                Spacer(modifier = Modifier.weight(3f))
                Image(
                    painter = painterResource(id = R.drawable.telephone),
                    contentDescription = "Phone",
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp)
                        .padding(5.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(id = R.drawable.whatsapp),
                    contentDescription = "Phone",
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp)
                        .padding(5.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(id = R.drawable.facebook),
                    contentDescription = "Phone",
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp)
                        .padding(5.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(id = R.drawable.gmail),
                    contentDescription = "Phone",
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp)
                        .padding(5.dp)
                )
                Spacer(modifier = Modifier.weight(3f))
            }
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {

                AllTexts(
                    text = "Information",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18,
                    modifier =
                    Modifier.padding(top = 10.dp, bottom = 10.dp)
                )
                Row {
                    Card(
                        modifier = Modifier
                            .padding(end = 5.dp)
                            .height(150.dp)
                            .width(150.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF1a1a27)
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 100.dp
                        )
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(20.dp)
                        ) {
                            AllTexts(
                                text = "Address",
                                fontWeight = FontWeight.Normal,
                                color = Color.White,
                                fontSize = 14

                            )
                            AllTexts(
                                text = "Hassan Colony, Tehsil Yazman, Bahawalpur",
                                color = Color.White, fontSize = 12,
                                fontWeight = FontWeight.Light
                            )

                        }
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Card(
                        modifier = Modifier
                            .height(150.dp)
                            .width(150.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF1a1a27)
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 50.dp
                        )


                    ) {

                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(20.dp)
                        ) {
                            AllTexts(
                                text = "Availability",
                                fontWeight = FontWeight.Normal,
                                color = Color.White,
                                fontSize = 14
                            )
                            AllTexts(
                                text = "Monday to Friday\n10:00am to 04:00pm",
                                fontWeight = FontWeight.Light,
                                color = Color.White,
                                fontSize = 12
                            )

                        }
                    }
                }
                AllTexts(
                    text = "About",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18,
                    color = Color.White,
                    modifier =
                    Modifier.padding(top = 10.dp, bottom = 10.dp)
                )
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF1a1a27)
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 50.dp
                    )
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(20.dp)
                    ) {
                        AllTexts(
                            text = "I'm Ali Hamza Android Developer, Complete BS CS form IUB." +
                                    "\nContact me in case of any issue.\nThanks❤️",
                            color = Color.White,
                            fontSize = 12,
                            textAlign = TextAlign.Left
                        )
                    }
                }
            }
        }
    }
}