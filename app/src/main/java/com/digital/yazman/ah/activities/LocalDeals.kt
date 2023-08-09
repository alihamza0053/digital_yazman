package com.digital.yazman.ah.activities

import android.os.Bundle
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.digital.yazman.ah.R
import com.digital.yazman.ah.classes.LocalDealNewsOppor
import com.digital.yazman.ah.ui.theme.DigitalYazmanTheme
import com.google.firebase.firestore.FirebaseFirestore
import com.valentinilk.shimmer.shimmer
import kotlinx.coroutines.tasks.await

class LocalDeals : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var dark by remember {
                mutableStateOf(true)
            }
            var backgroundColor = Color(0xFFADD8E6)
            val db = FirebaseFirestore.getInstance()
            val itemsState = remember { mutableStateOf(emptyList<LocalDealNewsOppor>()) }

            DigitalYazmanTheme {
                // A surface container using the 'background' color from the theme
                if (dark) {
                    backgroundColor = Color(0xFF14141f)
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(backgroundColor)
                        .verticalScroll(rememberScrollState())
                ) {
                    AllTexts(
                        "Local Deals",
                        fontSize = 25,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 15.dp, start = 20.dp), dark = dark
                    )

                    LaunchedEffect(Unit) {
                        val querySnapshot = db.collection("Local Deals").get().await()
                        val userDataList = mutableListOf<LocalDealNewsOppor>()
                        for (document in querySnapshot) {
                            val id = document.getString("id") ?: ""
                            val category = document.getString("category") ?: ""
                            val title = document.getString("title") ?: ""
                            val shortDes = document.getString("shortDes") ?: ""
                            val source = document.getString("source") ?: ""
                            val date = document.getString("date") ?: ""
                            userDataList.add(
                                LocalDealNewsOppor(
                                    id, category, title, shortDes, source, date
                                )
                            )
                        }
                        itemsState.value = userDataList
                    }

                    if (itemsState.value.isEmpty()) {
                        repeat(10) {
                            CardItem(
                                modifier = Modifier.shimmer(),
                                category = "category",
                                title = "title",
                                description = "description",
                                source = "source",
                                date = "date",
                                dark = dark
                            )
                        }
                    }

                    itemsState.value.forEach { data ->
                        CardItem(
                            category = data.category,
                            title = data.title,
                            description = data.shortDes,
                            source = data.source,
                            date = data.date,
                            dark = dark
                        )
                    }

                }


            }
        }
    }
}


@Composable
fun CardItem(
    modifier: Modifier = Modifier,
    category: String,
    title: String,
    description: String,
    source: String,
    date: String,
    dark: Boolean
) {
    var cardBackgroundColor = Color(0xFFFFFF)
    if(dark){
        cardBackgroundColor = Color(0xFF282834)
    }
    Card(
        elevation = 6.dp,
        shape = RoundedCornerShape(15.dp),
        backgroundColor = cardBackgroundColor,
        modifier = modifier.padding(start = 20.dp, top = 10.dp, end = 20.dp, bottom = 10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .clickable {

                },
        ) {
            Image(
                painter = painterResource(id = R.drawable.service_logo),
                contentDescription = null,
                modifier = modifier
                    .height(50.dp)
                    .width(50.dp)
                    .padding(start = 5.dp)
            )
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(start = 10.dp)
            ) {

                AllTexts(
                    category,
                    fontSize = 14,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.ExtraLight,
                    modifier = modifier
                        .alpha(0.5f)
                        .padding(0.dp), dark = dark
                )
                AllTexts(
                    title,
                    fontSize = 17,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier.padding(0.dp), dark = dark
                )
                AllTexts(
                    description,
                    fontSize = 14,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Light,
                    modifier = modifier.padding(0.dp), dark = dark
                )

                Row(
                    modifier = modifier
                        .padding(top = 10.dp, bottom = 1.dp)
                        .fillMaxWidth()
                ) {
                    AllTexts(
                        source,
                        fontSize = 14,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.ExtraLight,
                        modifier = modifier
                            .alpha(0.5f)
                            .padding(0.dp)
                            .weight(1f), dark = dark
                    )
                    AllTexts(
                        date,
                        fontSize = 14,
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.ExtraLight,
                        modifier = modifier
                            .alpha(0.5f)
                            .padding(0.dp, end = 10.dp)
                            .weight(1f), dark = dark
                    )

                }
            }
        }
    }
}


