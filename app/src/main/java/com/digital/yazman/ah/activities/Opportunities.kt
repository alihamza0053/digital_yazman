package com.digital.yazman.ah.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.digital.yazman.ah.classes.LocalDealNewsOppor
import com.digital.yazman.ah.datastore.StoreLightDarkData
import com.digital.yazman.ah.ui.theme.DigitalYazmanTheme
import com.google.firebase.firestore.FirebaseFirestore
import com.valentinilk.shimmer.shimmer
import kotlinx.coroutines.tasks.await

class Opportunities : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            var darkValue = getIntent().getBooleanExtra("dark",false)
            var dark by remember {
                mutableStateOf(darkValue)
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
                ) {

                    AllTexts(
                        "Opportunities",
                        fontSize = 25,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 15.dp, start = 20.dp),
                        dark = dark
                    )


                    LaunchedEffect(Unit) {
                        val querySnapshot = db.collection("Opportunities").get().await()
                        val userDataList = mutableListOf<LocalDealNewsOppor>()
                        for (document in querySnapshot) {
                            val id = document.getString("id") ?: ""
                            val category = document.getString("category") ?: ""
                            val title = document.getString("title") ?: ""
                            val shortDes = document.getString("shortDes") ?: ""
                            val source = document.getString("source") ?: ""
                            val logoUrl = document.getString("logoUrl") ?: ""
                            val date = document.getString("date") ?: ""
                            userDataList.add(
                                LocalDealNewsOppor(
                                    id, category, title, shortDes, source, logoUrl, date
                                )
                            )
                        }
                        itemsState.value = userDataList
                    }

                    val logoUrl = "https://firebasestorage.googleapis.com/v0/b/digital-yazman-34f70.appspot.com/o/logo.png?alt=media&token=3a657305-5f6b-4c75-b012-07b58c71945e"

                    if (itemsState.value.isEmpty()) {
                        repeat(10) {
                            CardItem(
                                modifier = Modifier.shimmer(),
                                category = "category",
                                title = "title",
                                description = "description",
                                source = "source",
                                logoUrl = logoUrl,
                                date = "date",
                                dark = dark
                            )
                        }
                    }

                    val sortedItems = itemsState.value.sortedBy { it.date }

                    LazyColumn {
                        items(sortedItems) { data ->
                            CardItem(
                                category = data.category,
                                title = data.title,
                                description = data.shortDes,
                                source = data.source,
                                logoUrl = data.logoUrl,
                                date = data.date,
                                dark = dark
                            )
                        }
                    }


                }
            }
        }
    }
}
