package com.digital.yazman.ah.activities

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.digital.yazman.ah.R
import com.digital.yazman.ah.ui.theme.DigitalYazmanTheme

class BusinessesViews : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DigitalYazmanTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {

                    repeat(5) {

                        BusinessCard(
                            "Pakistan Photo State",
                            "Muhammad Naeem",
                            "Old Court Road Yazman",
                            "03065600053\n03180150327",
                            applicationContext
                        )
                    }
                    repeat(5) {

                        BusinessCard(
                            "Jeo Royal Photo and Copy Shop",
                            "Adeel",
                            "Near Ranger Petrol Pump Yazman",
                            "03065600053\n03180150327",
                            applicationContext
                        )
                    }
                }

            }
        }
    }
}

@Composable
fun BusinessCard(shop: String, name: String, address: String, contact: String, context: Context) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        .clickable {
            Toast
                .makeText(context, name, Toast.LENGTH_SHORT)
                .show()
        }) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.hamza2),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(60.dp)
                        .width(60.dp)
                        .clip(CircleShape)
                )
            }
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                AllTexts(
                    shop,
                    fontSize = 17,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(0.dp)
                )
                AllTexts(
                    name,
                    fontSize = 15,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(0.dp)
                )
                AllTexts(
                    address,
                    fontSize = 13,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(0.dp)
                )
                AllTexts(
                    contact,
                    fontSize = 13,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(0.dp)
                )
            }

        }
        Divider(
            modifier = Modifier.padding(bottom = 4.dp)
        )

    }
}