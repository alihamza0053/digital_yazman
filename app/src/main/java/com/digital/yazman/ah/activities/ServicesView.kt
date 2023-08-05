package com.digital.yazman.ah.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.digital.yazman.ah.R
import com.digital.yazman.ah.classes.BusinessesClass
import com.digital.yazman.ah.classes.ServicesClass
import com.digital.yazman.ah.ui.theme.DigitalYazmanTheme
import com.google.firebase.firestore.FirebaseFirestore
import com.valentinilk.shimmer.shimmer
import kotlinx.coroutines.tasks.await

class ServicesView : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val servicesName = intent.getStringExtra("services")
            val db = FirebaseFirestore.getInstance()
            val itemsState = remember { mutableStateOf(emptyList<ServicesClass>()) }
            DigitalYazmanTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .background(Color(0xFFADD8E6))
                ) {

                    LaunchedEffect(Unit) {
                        val querySnapshot = db.collection("Services").get().await()
                        val userDataList = mutableListOf<ServicesClass>()
                        for (document in querySnapshot) {
                            if (servicesName == document.getString("services")) {
                                val id = document.getString("id") ?: ""
                                val name = document.getString("name") ?: ""
                                val expert = document.getString("expert") ?: ""
                                val location = document.getString("location") ?: ""
                                val contact = document.getString("contact") ?: ""
                                val services = document.getString("services") ?: ""
                                userDataList.add(
                                    ServicesClass(
                                        id, name, expert, location, contact, services
                                    )
                                )
                            }
                        }
                        itemsState.value = userDataList
                    }


                    if (itemsState.value.isEmpty()) {

                        repeat(10) {
                            ServicesCard(
                                "Name",
                                "Skill",
                                "Yazman",
                                "03XXXXXXXXX",
                                painterResource(id = R.drawable.hamza2),
                                modifier = Modifier.shimmer()
                            )
                        }
                    }

                    itemsState.value.forEach { data ->
                        ServicesCard(
                            name = data.name,
                            expertise = data.expertise,
                            location = data.location,
                            contact = data.contact,
                            painter = painterResource(id = R.drawable.hamza2),
                            modifier = Modifier,
                        )

                    }


                }
            }
        }
    }
}

@Composable
fun ServicesCard(
    name: String,
    expertise: String,
    location: String,
    contact: String,
    painter: Painter = painterResource(id = R.drawable.hamza2),
    modifier: Modifier,

    ) {
    Card(
        modifier = modifier
            .height(200.dp)
            .padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFFFFF)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )

    ) {
        Column(
            modifier = modifier
                .padding(5.dp)
        ) {
            Row {
                Image(
                    painter = painter,
                    contentDescription = "Image",
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .width(100.dp)
                        .padding(5.dp)
                        .clip(shape = RoundedCornerShape(10)),
                )

                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,modifier = modifier
                    ) {
                        AllTexts(
                            name,
                            fontSize = 17,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Bold,
                            modifier = modifier
                        )
                        Spacer(modifier = modifier.weight(1f))
                        FavoriteButton(modifier = modifier)
                    }

                    AllTexts(
                        expertise,
                        fontSize = 14,
                        color = Color.Blue,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Light,
                        modifier = modifier
                    )
                    Spacer(modifier = modifier.weight(1f))
                    Row {
                        AllTexts(
                            text = "Location",
                            fontSize = 14,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.SemiBold,
                            modifier = modifier
                        )
                        Spacer(modifier = modifier.weight(1f))
                        AllTexts(
                            "Contact",
                            fontSize = 14,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.SemiBold,
                            modifier = modifier
                        )
                    }
                    Row(modifier = modifier) {
                        AllTexts(
                            location,
                            fontSize = 14,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Light,
                            modifier = modifier
                        )
                        Spacer(modifier = modifier.weight(1f))
                        AllTexts(
                            contact,
                            fontSize = 14,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Light,
                            modifier = modifier
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    color: Color = Color.Black
) {

    var isFavorite by remember { mutableStateOf(false) }

    IconToggleButton(
        checked = isFavorite,
        onCheckedChange = {
            isFavorite = !isFavorite
        }
    ) {
        Icon(
            tint = color,
            modifier = modifier.graphicsLayer {
                scaleX = 1.3f
                scaleY = 1.3f
            },
            imageVector = if (isFavorite) {
                Icons.Filled.Favorite
            } else {
                Icons.Default.FavoriteBorder
            },
            contentDescription = null
        )
    }

}