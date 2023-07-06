package com.digital.yazman.ah

import android.content.Intent
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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.digital.yazman.ah.ui.theme.DigitalYazmanTheme

class Services : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val context = LocalContext.current
            DigitalYazmanTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFADD8E6))
                        .verticalScroll(rememberScrollState())


                ) {
                    //services start
                    AllTexts(
                        "Services",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 25,
                        modifier = Modifier.padding(top = 15.dp, start = 20.dp)
                    )


                    // first row with 3 cards
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth().padding( 20.dp)
                    ) {
                        //first row first card

                        CardColumn(
                            painter = painterResource(id = R.drawable.cable_tv),
                            title = "TV Cable",
                            fontSize = 12,
                            fontWeight = FontWeight.Light,
                            cardModifier = Modifier,
                            columnModifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                                .clickable {
                                    context.startActivity(
                                        Intent(
                                            context, BusinessesViews::class.java
                                        )
                                    )
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))

                        //first row second card

                        CardColumn(
                            painter = painterResource(id = R.drawable.car_mechanic),
                            title = "Car Mechanics",
                            fontSize = 12,
                            fontWeight = FontWeight.Light,
                            cardModifier = Modifier,
                            columnModifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                                .clickable {
                                    context.startActivity(
                                        Intent(
                                            context, BusinessesViews::class.java
                                        )
                                    )
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))

                        //first row third card

                        CardColumn(
                            painter = painterResource(id = R.drawable.catering),
                            title = "Catering",
                            fontSize = 12,
                            fontWeight = FontWeight.Light,
                            cardModifier = Modifier,
                            columnModifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                                .clickable {
                                    context.startActivity(
                                        Intent(
                                            context, BusinessesViews::class.java
                                        )
                                    )
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth().padding( 20.dp)
                    ) {
                        //first row first card

                        CardColumn(
                            painter = painterResource(id = R.drawable.computer_repair),
                            title = "Computer Repair",
                            fontSize = 12,
                            fontWeight = FontWeight.Light,
                            cardModifier = Modifier,
                            columnModifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                                .clickable {
                                    context.startActivity(
                                        Intent(
                                            context, BusinessesViews::class.java
                                        )
                                    )
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))

                        //first row second card

                        CardColumn(
                            painter = painterResource(id = R.drawable.electricians),
                            title = "Electricians",
                            fontSize = 12,
                            fontWeight = FontWeight.Light,
                            cardModifier = Modifier,
                            columnModifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                                .clickable {
                                    context.startActivity(
                                        Intent(
                                            context, BusinessesViews::class.java
                                        )
                                    )
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))

                        //first row third card

                        CardColumn(
                            painter = painterResource(id = R.drawable.handyman),
                            title = "Handyman",
                            fontSize = 12,
                            fontWeight = FontWeight.Light,
                            cardModifier = Modifier,
                            columnModifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                                .clickable {
                                    context.startActivity(
                                        Intent(
                                            context, BusinessesViews::class.java
                                        )
                                    )
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth().padding( 20.dp)
                    ) {
                        //first row first card

                        CardColumn(
                            painter = painterResource(id = R.drawable.home_cleaners),
                            title = "Home Cleaners",
                            fontSize = 12,
                            fontWeight = FontWeight.Light,
                            cardModifier = Modifier,
                            columnModifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                                .clickable {
                                    context.startActivity(
                                        Intent(
                                            context, BusinessesViews::class.java
                                        )
                                    )
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))

                        //first row second card

                        CardColumn(
                            painter = painterResource(id = R.drawable.home_security),
                            title = "Home Security",
                            fontSize = 12,
                            fontWeight = FontWeight.Light,
                            cardModifier = Modifier,
                            columnModifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                                .clickable {
                                    context.startActivity(
                                        Intent(
                                            context, BusinessesViews::class.java
                                        )
                                    )
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))

                        //first row third card

                        CardColumn(
                            painter = painterResource(id = R.drawable.hvacr),
                            title = "HVACR",
                            fontSize = 12,
                            fontWeight = FontWeight.Light,
                            cardModifier = Modifier,
                            columnModifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                                .clickable {
                                    context.startActivity(
                                        Intent(
                                            context, BusinessesViews::class.java
                                        )
                                    )
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth().padding( 20.dp)
                    ) {
                        //first row first card

                        CardColumn(
                            painter = painterResource(id = R.drawable.internet_service),
                            title = "Internet",
                            fontSize = 12,
                            fontWeight = FontWeight.Light,
                            cardModifier = Modifier,
                            columnModifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                                .clickable {
                                    context.startActivity(
                                        Intent(
                                            context, BusinessesViews::class.java
                                        )
                                    )
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))

                        //first row second card

                        CardColumn(
                            painter = painterResource(id = R.drawable.movers),
                            title = "Movers",
                            fontSize = 12,
                            fontWeight = FontWeight.Light,
                            cardModifier = Modifier,
                            columnModifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                                .clickable {
                                    context.startActivity(
                                        Intent(
                                            context, BusinessesViews::class.java
                                        )
                                    )
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))

                        //first row third card

                        CardColumn(
                            painter = painterResource(id = R.drawable.painter),
                            title = "Painters",
                            fontSize = 12,
                            fontWeight = FontWeight.Light,
                            cardModifier = Modifier,
                            columnModifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                                .clickable {
                                    context.startActivity(
                                        Intent(
                                            context, BusinessesViews::class.java
                                        )
                                    )
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth().padding( 20.dp)
                    ) {
                        //first row first card

                        CardColumn(
                            painter = painterResource(id = R.drawable.pet_service),
                            title = "Pet Service",
                            fontSize = 12,
                            fontWeight = FontWeight.Light,
                            cardModifier = Modifier,
                            columnModifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                                .clickable {
                                    context.startActivity(
                                        Intent(
                                            context, BusinessesViews::class.java
                                        )
                                    )
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))

                        //first row second card

                        CardColumn(
                            painter = painterResource(id = R.drawable.photography),
                            title = "Photograph",
                            fontSize = 12,
                            fontWeight = FontWeight.Light,
                            cardModifier = Modifier,
                            columnModifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                                .clickable {
                                    context.startActivity(
                                        Intent(
                                            context, BusinessesViews::class.java
                                        )
                                    )
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))

                        //first row third card

                        CardColumn(
                            painter = painterResource(id = R.drawable.plumbers),
                            title = "Plumbers",
                            fontSize = 12,
                            fontWeight = FontWeight.Light,
                            cardModifier = Modifier,
                            columnModifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                                .clickable {
                                    context.startActivity(
                                        Intent(
                                            context, BusinessesViews::class.java
                                        )
                                    )
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                        )
                    }





                }
            }
        }
    }
}
