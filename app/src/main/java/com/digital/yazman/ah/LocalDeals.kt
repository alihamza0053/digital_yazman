package com.digital.yazman.ah

import android.content.Context
import android.content.res.Resources
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.digital.yazman.ah.ui.theme.DigitalYazmanTheme
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LocalDeals : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val database = Firebase.database
            val myRef = database.getReference("Local Deals")
            var category by remember {
                mutableStateOf("")
            }
            var title by remember {
                mutableStateOf("")
            }
            var description by remember {
                mutableStateOf("")
            }
            var source by remember {
                mutableStateOf("")
            }
            var date by remember {
                mutableStateOf("")
            }

            var vlist = mutableListOf<LocalDealsData>()

            DigitalYazmanTheme {

                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFADD8E6))
                        .verticalScroll(rememberScrollState())
                ) {
                    AllTexts(
                        "Local Deals",
                        fontSize = 25,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 15.dp, start = 20.dp)
                    )

                    CardItem(
                        category = "Service",
                        title = "50% off on shoes",
                        description = "sale sale sale on this Sunday 50% off on all sizes on all service shops.",
                        source = "Service Brand",
                        date = "01-06/01-07",

                        Toast.makeText(applicationContext, "Service Brand", Toast.LENGTH_SHORT)
                    )

                    myRef.get().addOnSuccessListener {
                        if (it.exists()) {
                            it.children.forEach {

                                val todo = it.child("category").value.toString()
                                category = it.child("category").value.toString()
                                title = it.child("title").value.toString()
                                description = it.child("shortDes").value.toString()
                                source = it.child("source").value.toString()
                                date = it.child("date").value.toString()


                            }
                        }
                    }
                    CardItem(
                        category = category,
                        title = title,
                        description = description,
                        source = source,
                        date = date,

                        Toast.makeText(applicationContext, "Service Brand", Toast.LENGTH_SHORT)
                    )

                }



        }
    }
}
}


@Composable
fun CardItem(
    category: String,
    title: String,
    description: String,
    source: String,
    date: String,
    toast: Toast
) {
    Card(
        elevation = 6.dp,
        shape = RoundedCornerShape(15.dp),
        backgroundColor = Color(0xFFFFFFFF),
        modifier = Modifier.padding(start = 20.dp, top = 10.dp, end = 20.dp, bottom = 10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    toast.show()
                },
        ) {
            Image(
                painter = painterResource(id = R.drawable.service_logo),
                contentDescription = null,
                modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
                    .padding(start = 5.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 10.dp)
            ) {

                AllTexts(
                    category,
                    fontSize = 14,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.ExtraLight,
                    modifier = Modifier
                        .alpha(0.5f)
                        .padding(0.dp)
                )
                AllTexts(
                    title,
                    fontSize = 17,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(0.dp)
                )
                AllTexts(
                    description,
                    fontSize = 14,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.padding(0.dp)
                )

                Row(
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 1.dp)
                        .fillMaxWidth()
                ) {
                    AllTexts(
                        source,
                        fontSize = 14,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.ExtraLight,
                        modifier = Modifier
                            .alpha(0.5f)
                            .padding(0.dp)
                            .weight(1f)
                    )
                    AllTexts(
                        date,
                        fontSize = 14,
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.ExtraLight,
                        modifier = Modifier
                            .alpha(0.5f)
                            .padding(0.dp, end = 10.dp)
                            .weight(1f)
                    )

                }
            }
        }
    }
}


