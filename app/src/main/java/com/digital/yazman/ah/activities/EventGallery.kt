package com.digital.yazman.ah.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import com.digital.yazman.ah.nonScaledSp
import com.digital.yazman.ah.ui.theme.DigitalYazmanTheme

class EventGallery : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var imgVisibility by remember {
                mutableStateOf(false)
            }
            val images = listOf(
                "https://firebasestorage.googleapis.com/v0/b/digital-yazman-34f70.appspot.com/o/1.png?alt=media&token=fc9a62d2-b12d-47ed-b59a-0287cf1f1b1d",
                "https://firebasestorage.googleapis.com/v0/b/digital-yazman-34f70.appspot.com/o/2.png?alt=media&token=d70ebc49-265c-4c13-9df6-4d01e56e3a6c",
                "https://firebasestorage.googleapis.com/v0/b/digital-yazman-34f70.appspot.com/o/3.png?alt=media&token=2fe49b52-0ff4-4871-b18c-653c2476c324",
                "https://firebasestorage.googleapis.com/v0/b/digital-yazman-34f70.appspot.com/o/4.png?alt=media&token=72d1d394-6aac-4314-b915-18b24005084b",
                "https://firebasestorage.googleapis.com/v0/b/digital-yazman-34f70.appspot.com/o/1.png?alt=media&token=fc9a62d2-b12d-47ed-b59a-0287cf1f1b1d",

                )
            DigitalYazmanTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .fillMaxSize()
                        .background(
                            Color(0xFFADD8E6)
                        )
                ) {
                    AllTexts(
                        "Event Gallery",
                        fontSize = 25,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 15.dp, start = 20.dp)
                    )
                    repeat(20){

                        ExpandableCard(images, "Cricket Event", imgVisibility)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandableCard(images: List<String>, title: String, imgVisibility: Boolean) {
    var expandedState by remember {
        mutableStateOf(false)
    }
    val rotateState by animateFloatAsState(targetValue = if (expandedState) 180f else 0f)

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
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
                    fontSize = 20.nonScaledSp,
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
fun ImageGrid(imageUrls: List<String>) {
    val context = LocalContext.current
    var visibility  = false
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
                items(imageUrls) { imageUrl ->
                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    ) {
                        Image(
                            painter = rememberImagePainter(
                                data = imageUrl,
                                builder = {
                                    placeholder(R.drawable.faq) // Placeholder image while loading
                                    error(R.drawable.ic_launcher_background) // Image to display on error
                                }
                            ),
                            contentDescription = null, // Set a proper content description
                            contentScale = ContentScale.Inside,
                            modifier = Modifier
                                .clickable {
                                    url = imageUrl
                                    visibility = true
                                    Toast.makeText(
                                        context,
                                        visibility.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    if (visibility){
                                        visibility = false
                                    }
                                }
                        )
                    }
                }
            }
        }

        AsyncImage(model = url, contentDescription = null, modifier = Modifier
            .fillMaxSize()
            .clickable { url = "" }, contentScale = ContentScale.Crop)

    }
}
