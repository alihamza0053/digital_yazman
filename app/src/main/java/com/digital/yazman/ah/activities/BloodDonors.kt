package com.digital.yazman.ah.activities

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.digital.yazman.ah.ui.theme.DigitalYazmanTheme
import com.digital.yazman.ah.nonScaledSp

class BloodDonors : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            var darkValue = getIntent().getBooleanExtra("dark", false)
            var dark by remember {
                mutableStateOf(darkValue)
            }

            var textColor = Color(0xFF000000)
            var cardColor = Color(0xFFFFFFFF)
            var backgroundColor = Color(0xFFADD8E6)

            DigitalYazmanTheme {
                // A surface container using the 'background' color from the theme
                if (dark) {
                    backgroundColor = Color(0xFF14141f)
                    textColor = Color(0xFFFFFFFF)
                    cardColor = Color(0xFF282834)
                }
                val APlus = listOf(
                    BDdata("Ali Hamza", "A+", "03065600053", "Yazman"),
                    BDdata("Ali Hamza", "A+", "03065600053", "Yazman")


                )
                val ANeg = listOf(
                    BDdata("Ali Hamza", "A-", "03065600053", "Yazman"),
                    BDdata("Ali Hamza", "A-", "03065600053", "Yazman")

                )
                val BPlus = listOf(
                    BDdata("Ali Hamza", "B+", "03065600053", "Yazman"),
                    BDdata("Ali Hamza", "B+", "03065600053", "Yazman")

                )
                val BNeg = listOf(
                    BDdata("Ali Hamza", "B-", "03065600053", "Yazman"),
                    BDdata("Ali Hamza", "B-", "03065600053", "Yazman")

                )
                val ABPlus = listOf(
                    BDdata("Ali Hamza", "AB+", "03065600053", "Yazman"),
                    BDdata("Ali Hamza", "AB+", "03065600053", "Yazman")

                )
                val ABNeg = listOf(
                    BDdata("Ali Hamza", "AB-", "03065600053", "Yazman"),
                    BDdata("Ali Hamza", "AB-", "03065600053", "Yazman")

                )
                val OPlus = listOf(
                    BDdata("Ali Hamza", "O+", "03065600053", "Yazman"),
                    BDdata("Ali Hamza", "O+", "03065600053", "Yazman")

                )
                val ONeg = listOf(
                    BDdata("Ali Hamza", "O-", "03065600053", "Yazman"),
                    BDdata("Ali Hamza", "O-", "03065600054", "Yazman")

                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(backgroundColor)


                ) {
                    Text(
                        text = "Blood Donors",
                        color = textColor,
                        fontSize = 25.nonScaledSp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 15.dp, start = 20.dp)
                    )

                    ExpandableBloodDonorsCard(title = "A+", dataList = APlus, dark = dark)
                    ExpandableBloodDonorsCard(title = "A-", dataList = ANeg, dark = dark)
                    ExpandableBloodDonorsCard(title = "B+", dataList = BPlus, dark = dark)
                    ExpandableBloodDonorsCard(title = "B-", dataList = BNeg, dark = dark)
                    ExpandableBloodDonorsCard(title = "AB+", dataList = ABPlus, dark = dark)
                    ExpandableBloodDonorsCard(title = "AB-", dataList = ABNeg, dark = dark)
                    ExpandableBloodDonorsCard(title = "O+", dataList = OPlus, dark = dark)
                    ExpandableBloodDonorsCard(title = "O-", dataList = ONeg, dark = dark)

                }
            }
        }
    }
}

data class BDdata(val name: String, val bloodType: String, val phone: String, val location: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandableBloodDonorsCard(title: String, dataList: List<BDdata>, dark: Boolean) {
    var expandedState by remember {
        mutableStateOf(false)
    }
    val rotateState by animateFloatAsState(targetValue = if (expandedState) 180f else 0f)
    var cardBackgroundColor = Color(0xFFFFFFFF)
    var textColor = Color(0xFF000000)
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
                    text = title,
                    fontSize = 15.nonScaledSp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(6f),
                    color = textColor,
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
                LazyColumn {
                    items(dataList) { data ->
                        BloodDonorsData(
                            context = LocalContext.current,
                            dataList = data,
                            dark = dark,
                            modifier = Modifier
                        )
                    }
                }
            }
        }

    }
}


@Composable
fun BloodDonorsData(
    modifier: Modifier,
    dataList: BDdata,
    dark: Boolean,
    context: Context
) {
    var cardBackgroundColor = Color(0xFFFFFFFF)
    if (dark) {
        cardBackgroundColor = Color(0xFF14141f)
    }
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = cardBackgroundColor
        ),
        modifier = Modifier
            .padding(5.dp)
            .fillMaxSize(),

        ) {
        Column(modifier = modifier
            .fillMaxWidth()
            .clickable {
                if (dataList.phone != "") {
                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + dataList.phone))
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent)
                }

                Toast
                    .makeText(context, dataList.name, Toast.LENGTH_SHORT)
                    .show()
            }) {
            Row(
                verticalAlignment = Alignment.CenterVertically, modifier = modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = modifier.padding(10.dp)
                ) {
                    Row {

                        AllTexts(
                            dataList.name,
                            fontSize = 15,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.SemiBold,
                            modifier = modifier.padding(0.dp), dark = dark
                        )
                        Spacer(modifier = modifier.weight(1f))
                        AllTexts(
                            dataList.bloodType,
                            fontSize = 13,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Normal,
                            modifier = modifier.padding(0.dp), dark = dark
                        )
                    }

                    AllTexts(
                        dataList.phone,
                        fontSize = 13,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Normal,
                        modifier = modifier.padding(0.dp), dark = dark
                    )

                    AllTexts(
                        dataList.location,
                        fontSize = 13,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Normal,
                        modifier = modifier.padding(0.dp), dark = dark
                    )

                }

            }


        }
    }
}

