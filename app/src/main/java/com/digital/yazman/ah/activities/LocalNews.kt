package com.digital.yazman.ah.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.digital.yazman.ah.classes.LocalDealNewsOppor
import com.digital.yazman.ah.ui.theme.DigitalYazmanTheme
import com.google.firebase.firestore.FirebaseFirestore
import com.valentinilk.shimmer.shimmer
import kotlinx.coroutines.tasks.await

class LocalNews : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val db = FirebaseFirestore.getInstance()
            val itemsState = remember { mutableStateOf(emptyList<LocalDealNewsOppor>()) }
            DigitalYazmanTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFADD8E6))
                        .verticalScroll(rememberScrollState())
                ) {
                    AllTexts(
                        "Local News",
                        fontSize = 25,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 15.dp, start = 20.dp)
                    )


                    LaunchedEffect(Unit) {
                        val querySnapshot = db.collection("Local News").get().await()
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
                                date = "date"
                            )
                        }
                    }

                    itemsState.value.forEach { data ->
                        CardItem(
                            category = data.category,
                            title = data.title,
                            description = data.shortDes,
                            source = data.source,
                            date = data.date
                        )
                    }


                }
            }
        }
    }
}