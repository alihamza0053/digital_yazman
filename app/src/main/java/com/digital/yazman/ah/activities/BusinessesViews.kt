package com.digital.yazman.ah.activities

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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.digital.yazman.ah.R
import com.digital.yazman.ah.classes.BusinessesClass
import com.digital.yazman.ah.classes.LocalDealNewsOppor
import com.digital.yazman.ah.ui.theme.DigitalYazmanTheme
import com.google.firebase.firestore.FirebaseFirestore
import com.valentinilk.shimmer.shimmer
import kotlinx.coroutines.tasks.await

class BusinessesViews : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val db = FirebaseFirestore.getInstance()
            val itemsState = remember { mutableStateOf(emptyList<BusinessesClass>()) }
            DigitalYazmanTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .background(
                            Color(0xFFADD8E6)
                        )

                ) {
                    AllTexts(
                        "Businesses",
                        fontSize = 25,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 15.dp, start = 20.dp)
                    )

                    LaunchedEffect(Unit) {
                        val querySnapshot = db.collection("Businesses").get().await()
                        val userDataList = mutableListOf<BusinessesClass>()
                        for (document in querySnapshot) {
                            val id = document.getString("id") ?: ""
                            val shop = document.getString("shop") ?: ""
                            val name = document.getString("name") ?: ""
                            val address = document.getString("address") ?: ""
                            val contact = document.getString("contact") ?: ""
                            userDataList.add(
                                BusinessesClass(
                                    id, shop, name, address, contact
                                )
                            )
                        }
                        itemsState.value = userDataList
                    }

                    if (itemsState.value.isEmpty()) {
                        repeat(10) {
                            BusinessCard(
                                modifier = Modifier.shimmer(),
                                "Pakistan Photo State",
                                "Muhammad Naeem",
                                "Old Court Road Yazman",
                                "03180150327",
                                applicationContext
                            )
                        }
                    }

                    itemsState.value.forEach { data ->
                        BusinessCard(
                            modifier = Modifier,
                            shop = data.shop,
                            name = data.name,
                            address = data.address,
                            contact = data.contact,
                            context = applicationContext
                        )

                    }

                }

            }
        }
    }
}

@Composable
fun BusinessCard(
    modifier: Modifier,
    shop: String,
    name: String,
    address: String,
    contact: String,
    context: Context
) {
    Column {
        Column(modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(Color.White)
            .clickable {
                Toast
                    .makeText(context, name, Toast.LENGTH_SHORT)
                    .show()
            }) {
            Row(
                verticalAlignment = Alignment.CenterVertically, modifier = modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = modifier.padding(10.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.hamza2),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = modifier
                            .height(60.dp)
                            .width(60.dp)
                            .clip(CircleShape)
                    )
                }
                Column(
                    modifier = modifier.padding(10.dp)
                ) {
                    AllTexts(
                        shop,
                        fontSize = 17,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        modifier = modifier.padding(0.dp)
                    )
                    AllTexts(
                        name,
                        fontSize = 15,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.SemiBold,
                        modifier = modifier.padding(0.dp)
                    )
                    AllTexts(
                        address,
                        fontSize = 13,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Normal,
                        modifier = modifier.padding(0.dp)
                    )
                    AllTexts(
                        contact,
                        fontSize = 13,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.SemiBold,
                        modifier = modifier.padding(0.dp)
                    )
                }

            }


        }
    }
}