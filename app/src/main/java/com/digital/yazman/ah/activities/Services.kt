package com.digital.yazman.ah.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.digital.yazman.ah.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.digital.yazman.ah.classes.BusinessesClass
import com.digital.yazman.ah.datastore.StoreLightDarkData
import com.digital.yazman.ah.ui.theme.DigitalYazmanTheme
import com.google.firebase.firestore.FirebaseFirestore

class Services : ComponentActivity() {
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
            var textColor = Color(0xFF000000)
            DigitalYazmanTheme {
                if(dark){
                    backgroundColor = Color(0xFF14141f)
                    textColor = Color(0xFFFFFFFF)
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(backgroundColor)
                        .verticalScroll(rememberScrollState())


                ) {
                    //services start
                    AllTexts(
                        "Services",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 25,
                        modifier = Modifier.padding(top = 15.dp, start = 20.dp),
                        dark = dark
                    )


                    // first row with 3 cards
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
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
                                    val intent = Intent(this@Services, ServicesView::class.java)
                                    intent.putExtra("services", "TV Cable")
                                    startActivity(intent)
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp),
                            dark = dark
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
                                    val intent = Intent(this@Services, ServicesView::class.java)
                                    intent.putExtra("services", "Car Mechanics")
                                    startActivity(intent)
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp),
                            dark = dark
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
                                    val intent = Intent(this@Services, ServicesView::class.java)
                                    intent.putExtra("services", "Catering")
                                    startActivity(intent)
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp),
                            dark = dark
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
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
                                    val intent = Intent(this@Services, ServicesView::class.java)
                                    intent.putExtra("services", "Computer Repair")
                                    startActivity(intent)
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp),
                            dark = dark
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
                                    val intent = Intent(this@Services, ServicesView::class.java)
                                    intent.putExtra("services", "Electricians")
                                    startActivity(intent)
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp),
                            dark = dark
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
                                    val intent = Intent(this@Services, ServicesView::class.java)
                                    intent.putExtra("services", "Handyman")
                                    startActivity(intent)
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp),
                            dark = dark
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
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
                                    val intent = Intent(this@Services, ServicesView::class.java)
                                    intent.putExtra("services", "Home Cleaners")
                                    startActivity(intent)
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp),
                            dark = dark
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
                                    val intent = Intent(this@Services, ServicesView::class.java)
                                    intent.putExtra("services", "Home Security")
                                    startActivity(intent)
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp),
                            dark = dark
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
                                    val intent = Intent(this@Services, ServicesView::class.java)
                                    intent.putExtra("services", "HVACR")
                                    startActivity(intent)
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp),
                            dark = dark
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
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
                                    val intent = Intent(this@Services, ServicesView::class.java)
                                    intent.putExtra("services", "Internet")
                                    startActivity(intent)
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp),
                            dark = dark
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
                                    val intent = Intent(this@Services, ServicesView::class.java)
                                    intent.putExtra("services", "Movers")
                                    startActivity(intent)
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp),
                            dark = dark
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
                                    val intent = Intent(this@Services, ServicesView::class.java)
                                    intent.putExtra("services", "Painters")
                                    startActivity(intent)
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp),
                            dark = dark
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
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
                                    val intent = Intent(this@Services, ServicesView::class.java)
                                    intent.putExtra("services", "Pet Service")
                                    startActivity(intent)
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp),
                            dark = dark
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
                                    val intent = Intent(this@Services, ServicesView::class.java)
                                    intent.putExtra("services", "Photograph")
                                    startActivity(intent)
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp),
                            dark = dark
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
                                    val intent = Intent(this@Services, ServicesView::class.java)
                                    intent.putExtra("services", "Plumbers")
                                    startActivity(intent)
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp),
                            dark = dark
                        )
                    }


                }
            }
        }
    }
}
