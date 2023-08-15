package com.digital.yazman.ah.activities

import android.annotation.SuppressLint
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.digital.yazman.ah.R
import com.digital.yazman.ah.classes.BusinessesClass
import com.digital.yazman.ah.datastore.StoreLightDarkData
import com.digital.yazman.ah.ui.theme.DigitalYazmanTheme
import com.google.firebase.firestore.FirebaseFirestore
import com.valentinilk.shimmer.shimmer
import kotlinx.coroutines.tasks.await

class BusinessesViews : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
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
            val db = FirebaseFirestore.getInstance()
            val itemsState = remember { mutableStateOf(emptyList<BusinessesClass>()) }

            DigitalYazmanTheme {
                // A surface container using the 'background' color from the theme
                if (dark) {
                    backgroundColor = Color(0xFF14141f)
                }
                val businessName = intent.getStringExtra("business")
                Toast.makeText(applicationContext, businessName, Toast.LENGTH_SHORT).show()
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .background(
                            backgroundColor
                        )

                ) {
                    AllTexts(
                        businessName.toString(),
                        fontSize = 25,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 15.dp, start = 20.dp), dark = dark
                    )

                    LaunchedEffect(Unit) {
                        val querySnapshot = db.collection("Businesses").get().await()
                        val userDataList = mutableListOf<BusinessesClass>()
                        for (document in querySnapshot) {
                            if (businessName == document.getString("business")) {
                                val id = document.getString("id") ?: ""
                                val shop = document.getString("shop") ?: ""
                                val name = document.getString("name") ?: ""
                                val address = document.getString("address") ?: ""
                                val contact = document.getString("contact") ?: ""
                                val business = document.getString("business") ?: ""
                                userDataList.add(
                                    BusinessesClass(
                                        id, shop, name, address, contact, business
                                    )
                                )
                            }
                        }
                        itemsState.value = userDataList
                    }

                    if (itemsState.value.isEmpty()) {
                        repeat(10) {
                            BusinessCard(
                                modifier = Modifier.shimmer(),
                                "Title",
                                "Name",
                                "Yazman",
                                "03XXXXXXXXX",
                                dark,
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
                            dark,
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
    dark: Boolean,
    context: Context
) {
    var cardBackgroundColor = Color(0xFFFFFFFF)
    if(dark){
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
            .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 5.dp)
            .fillMaxSize(),

        ) {
        Column(modifier = modifier
            .fillMaxWidth()
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
                        modifier = modifier.padding(0.dp), dark = dark
                    )
                    AllTexts(
                        name,
                        fontSize = 15,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.SemiBold,
                        modifier = modifier.padding(0.dp),
                        dark = dark
                    )
                    AllTexts(
                        address,
                        fontSize = 13,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Normal,
                        modifier = modifier.padding(0.dp),
                        dark = dark
                    )
                    AllTexts(
                        contact,
                        fontSize = 13,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.SemiBold,
                        modifier = modifier.padding(0.dp),
                        dark = dark
                    )
                }

            }


        }
    }
}