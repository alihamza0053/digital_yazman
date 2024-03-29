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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.digital.yazman.ah.R
import com.digital.yazman.ah.classes.BusinessesClass
import com.digital.yazman.ah.classes.ServicesClass
import com.digital.yazman.ah.datastore.StoreLightDarkData
import com.digital.yazman.ah.ui.theme.DigitalYazmanTheme
import com.google.firebase.firestore.FirebaseFirestore
import com.valentinilk.shimmer.shimmer
import kotlinx.coroutines.tasks.await

class ServicesView : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            var darkValue = getIntent().getBooleanExtra("dark", false)
            var dark by remember {
                mutableStateOf(darkValue)
            }
            var backgroundColor = Color(0xFFADD8E6)
            val servicesName = intent.getStringExtra("services")
            val db = FirebaseFirestore.getInstance()
            val itemsState = remember { mutableStateOf(emptyList<ServicesClass>()) }
            DigitalYazmanTheme {
                // A surface container using the 'background' color from the theme
                if (dark) {
                    backgroundColor = Color(0xFF14141f)
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(backgroundColor)
                ) {
                    AllTexts(
                        servicesName.toString(),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 25,
                        modifier = Modifier.padding(top = 15.dp, start = 20.dp),
                        dark = dark
                    )

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
                                val logoUrl = document.getString("logoUrl") ?: ""
                                val date = document.getString("date") ?: ""
                                userDataList.add(
                                    ServicesClass(
                                        id, name, expert, location, contact, services,logoUrl, date
                                    )
                                )
                            }
                        }
                        itemsState.value = userDataList
                    }


                    val logoUrl = "https://firebasestorage.googleapis.com/v0/b/digital-yazman-34f70.appspot.com/o/logo.png?alt=media&token=3a657305-5f6b-4c75-b012-07b58c71945e"

                    if (itemsState.value.isEmpty()) {

                        repeat(10) {
                            ServicesCard(
                                "Name",
                                "Skill",
                                "Yazman",
                                "03XXXXXXXXX",
                                dark = dark,
                                logoUrl,
                                modifier = Modifier.shimmer()
                            )
                        }
                    }

                    val sortedItems = itemsState.value.sortedBy { it.date }

                    LazyColumn {
                        items(sortedItems) { data ->
                            ServicesCard(
                                name = data.name,
                                expertise = data.expertise,
                                location = data.location,
                                contact = data.contact,
                                logoUrl = data.logoUrl,
                                modifier = Modifier,
                                dark = dark
                            )
                        }
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
    dark: Boolean,
    logoUrl: String,
    modifier: Modifier,

    ) {
    var cardBackgroundColor = Color(0xFFFFFFFF)
    if (dark) {
        cardBackgroundColor = Color(0xFF282834)
    }
    Card(
        modifier = modifier
            .height(200.dp)
            .padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = cardBackgroundColor
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
                AsyncImage(
                    model = logoUrl,
                    contentDescription = "Image",
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .width(100.dp)
                        .padding(5.dp)
                        .clip(shape = RoundedCornerShape(10)),
                )

                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically, modifier = modifier
                    ) {
                        AllTexts(
                            name,
                            fontSize = 17,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Bold,
                            modifier = modifier,
                            dark = dark
                        )
                        Spacer(modifier = modifier.weight(1f))
                        FavoriteButton(modifier = modifier, dark = dark)
                    }

                    AllTexts(
                        expertise,
                        fontSize = 14,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Light,
                        modifier = modifier,
                        dark = dark
                    )
                    Spacer(modifier = modifier.weight(1f))
                    Row {
                        AllTexts(
                            text = "Location",
                            fontSize = 14,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.SemiBold,
                            modifier = modifier,
                            dark = dark
                        )
                        Spacer(modifier = modifier.weight(1f))
                        AllTexts(
                            "Contact",
                            fontSize = 14,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.SemiBold,
                            modifier = modifier,
                            dark = dark
                        )
                    }
                    Row(modifier = modifier) {
                        AllTexts(
                            location,
                            fontSize = 14,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Light,
                            modifier = modifier,
                            dark = dark
                        )
                        Spacer(modifier = modifier.weight(1f))
                        AllTexts(
                            contact,
                            fontSize = 14,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Light,
                            modifier = modifier,
                            dark = dark
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
    dark: Boolean
) {
    var iconColor = Color(0xFF000000)
    if (dark) {
        iconColor = Color(0xFFFFFFFF)
    }
    var isFavorite by remember { mutableStateOf(false) }

    IconToggleButton(
        checked = isFavorite,
        onCheckedChange = {
            isFavorite = !isFavorite
        }
    ) {
        Icon(
            tint = iconColor,
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