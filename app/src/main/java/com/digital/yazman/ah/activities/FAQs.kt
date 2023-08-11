package com.digital.yazman.ah.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.digital.yazman.ah.R
import com.digital.yazman.ah.datastore.StoreLightDarkData
import com.digital.yazman.ah.ui.theme.DigitalYazmanTheme
import com.digital.yazman.ah.nonScaledSp

class FAQs : ComponentActivity() {
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
            DigitalYazmanTheme {
                // A surface container using the 'background' color from the theme
                if (dark) {
                    backgroundColor = Color(0xFF14141f)
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .background(
                            backgroundColor
                        )

                ) {
                    AllTexts(
                        "Notifications",
                        fontSize = 25,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 15.dp, start = 20.dp),
                        dark = dark
                    )
                    ExpandableTextCard("How to place ads of our business?", "You have to contact us for business ads.", dark)
                    ExpandableTextCard("Are these ads free?", "No, you have to pay for each ad.", dark)
                    ExpandableTextCard("How long it will take to place my ads?", "As Soon as your payment successfull, you ads will be visible", dark)
                    ExpandableTextCard("Can I promote my business here?", "Yes, you can promote your through ads.", dark)
                    ExpandableTextCard("What to do in case of any issue", "Contact us if you face any issue.", dark)


                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandableTextCard(title: String, faq: String,dark: Boolean) {
    var expandedState by remember {
        mutableStateOf(false)
    }
    val rotateState by animateFloatAsState(targetValue = if (expandedState) 180f else 0f)
    var cardBackgroundColor = Color(0xFFFFFFFF)
    var textColor = Color(0xFF000000)
    if(dark){
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
                    text = title,
                    fontSize = 15.nonScaledSp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(6f),
                    color =  textColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
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
                Text(text = faq,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Light,
                    color =  textColor,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis)
            }
        }

    }
}