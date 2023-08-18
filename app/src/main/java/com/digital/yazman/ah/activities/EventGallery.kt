package com.digital.yazman.ah.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.digital.yazman.ah.R
import com.digital.yazman.ah.classes.EventGalleryClass
import com.digital.yazman.ah.classes.EventGalleryImgClass
import com.digital.yazman.ah.classes.NotificationClass
import com.digital.yazman.ah.datastore.StoreLightDarkData
import com.digital.yazman.ah.nonScaledSp
import com.digital.yazman.ah.ui.theme.DigitalYazmanTheme
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class EventGallery : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            var darkValue = getIntent().getBooleanExtra("dark", false)
            var dark by remember {
                mutableStateOf(darkValue)
            }
            var backgroundColor = Color(0xFFADD8E6)
            val db = FirebaseFirestore.getInstance()
            val itemsState = remember { mutableStateOf(emptyList<EventGalleryClass>()) }
            val imagesState = remember { mutableStateOf(emptyList<EventGalleryImgClass>()) }

            val images = mutableListOf(
                "https://dy.alihamza.me/wp-content/uploads/2023/08/3.jpg",
                "https://dy.alihamza.me/wp-content/uploads/2023/08/19298522a8e9255e68c7ea1379aee488.jpg",
                "https://dy.alihamza.me/wp-content/uploads/2023/08/f1c3b1fbe428a1cf67ccc3a5256be949.jpg",
                "https://dy.alihamza.me/wp-content/uploads/2023/08/contact-1.png",
                "https://dy.alihamza.me/wp-content/uploads/2023/08/e0c9cc35e1f9da131cfc6d84664a973e.jpg",
            )
            DigitalYazmanTheme {
                // A surface container using the 'background' color from the theme
                BackHandler(enabled = true, onBack = {
                    finish()
                })
                if (dark) {
                    backgroundColor = Color(0xFF14141f)
                }
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .fillMaxSize()
                        .background(
                            backgroundColor
                        )
                ) {
                    AllTexts(
                        "Event Gallery",
                        fontSize = 25,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 15.dp, start = 20.dp),
                        dark = dark
                    )

//
//                    LaunchedEffect(Unit) {
//                        val querySnapshot = db.collection("Event Gallery").get().await()
//                        val userDataList = mutableListOf<EventGalleryClass>()
//                        val imgData = mutableListOf<EventGalleryImgClass>()
//                        for (document in querySnapshot) {
//                            val id = document.getString("id") ?: ""
//                            val title = document.getString("title") ?: ""
//                            val link1 = document.getString("link1") ?: ""
//                            val link2 = document.getString("link2") ?: ""
//                            val link3 = document.getString("link3") ?: ""
//                            val link4 = document.getString("link4") ?: ""
//                            val link5 = document.getString("link5") ?: ""
//                            val date = document.getString("date") ?: ""
//                            userDataList.add(
//                                EventGalleryClass(
//                                    id, title, link1, link2, link3, link4, link5, date
//                                )
//                            )
//                            imgData.add(
//                                EventGalleryImgClass(
//                                    link1, link2, link3, link4, link5
//                                )
//                            )
//                        }
//                        imagesState.value = imgData
//                        itemsState.value = userDataList
//                    }
//
//                    val sortedItems = itemsState.value.sortedBy { it.date }
//                    var a = 0
//
//                    LazyColumn(modifier = Modifier.height(500.dp)) {
//                        items(sortedItems) { data ->
//                            ExpandableImageCard(imagesState, data.title, dark)
//                        }
//                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandableImageCard(images: MutableState<List<EventGalleryImgClass>>, title: String, dark: Boolean) {
    var cardBackgroundColor = Color(0xFFFFFFFF)
    var textColor = Color(0xFF000000)

    var expandedState by remember {
        mutableStateOf(false)
    }
    val rotateState by animateFloatAsState(targetValue = if (expandedState) 180f else 0f)

    if (dark) {
        cardBackgroundColor = Color(0xFF282834)
        textColor = Color(0xFFFFFFFF)
    }
    Card(
        colors = CardDefaults.cardColors(
            containerColor = cardBackgroundColor
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 20.dp, end = 20.dp)
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        onClick = {
            expandedState = !expandedState
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    modifier = Modifier.weight(6f),
                    text = title,
                    fontSize = 18.nonScaledSp,
                    color = textColor,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                IconButton(modifier = Modifier
                    .alpha(6f)
                    .weight(1f)
                    .rotate(rotateState),
                    onClick = { expandedState = !expandedState }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        tint = textColor,
                        contentDescription = "drop down arrow"
                    )

                }
            }
            if (expandedState) {
                ImageGrid(imageUrls = images)
            }
        }

    }
}


@Composable
fun ImageGrid(imageUrls: MutableState<List<EventGalleryImgClass>>) {
    val context = LocalContext.current
    var visibility = false
    var url by remember {
        mutableStateOf("")
    }
    Box {
        Column {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 80.dp), // 2 columns
                modifier = Modifier
                    .padding(16.dp)
                    .height(200.dp)
            ) {
                items(imageUrls.value) { imageUrl ->
                    Row(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    ) {
                        Image(
                            painter = rememberImagePainter(
                                data = imageUrl.link1,
                                builder = {
                                    placeholder(R.drawable.faq) // Placeholder image while loading
                                    error(R.drawable.ic_launcher_background) // Image to display on error
                                }
                            ),
                            contentDescription = null, // Set a proper content description
                            contentScale = ContentScale.Inside,
                            modifier = Modifier
                                .height(80.dp)
                                .width(80.dp)
                                .clickable {
                                    url = imageUrl.link1
                                    visibility = true
                                    Toast.makeText(
                                        context,
                                        visibility.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    if (visibility) {
                                        visibility = false
                                    }
                                }

                        )
                        Image(
                            painter = rememberImagePainter(
                                data = imageUrl.link2,
                                builder = {
                                    placeholder(R.drawable.faq) // Placeholder image while loading
                                    error(R.drawable.ic_launcher_background) // Image to display on error
                                }
                            ),
                            contentDescription = null, // Set a proper content description
                            modifier = Modifier
                                .height(80.dp)
                                .width(80.dp)
                                .clickable {
                                    url = imageUrl.link2
                                    visibility = true
                                    Toast.makeText(
                                        context,
                                        visibility.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    if (visibility) {
                                        visibility = false
                                    }
                                },
                            contentScale = ContentScale.Inside

                        )

                    }
                }
            }
        }

        AsyncImage(model = url, contentDescription = null, modifier = Modifier
            .fillMaxSize()
            .clickable { url = "" }, contentScale = ContentScale.Crop
        )

    }
}
