package com.digital.yazman.ah.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.digital.yazman.ah.R
import com.digital.yazman.ah.datastore.StoreLightDarkData
import com.digital.yazman.ah.ui.theme.DigitalYazmanTheme

class History : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            var darkValue = getIntent().getBooleanExtra("dark",false)
            var dark by remember {
                mutableStateOf(darkValue)
            }
            var backgroundColor = Color(0xFFADD8E6)
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
                        "Yazman Mandi",
                        fontSize = 25,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 15.dp, start = 20.dp),
                        dark = dark
                    )

                    AllTexts(
                        stringResource(id = R.string.history_1),
                        fontSize = 16,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.padding(top = 10.dp, start = 20.dp, end = 15.dp),
                        dark = dark
                    )

                    Image(
                        painter = painterResource(id = R.drawable.yazman_img1),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(top = 5.dp, start = 20.dp, end = 15.dp)
                    )

                    AllTexts(
                        stringResource(id = R.string.history_2),
                        fontSize = 16,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.padding(top = 5.dp, start = 20.dp, end = 15.dp),
                        dark = dark
                    )

                    AllTexts(
                        stringResource(id = R.string.history_3),
                        fontSize = 16,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.padding(top = 5.dp, start = 20.dp, end = 15.dp),
                        dark = dark
                    )

                    Image(
                        painter = painterResource(id = R.drawable.yazman_img2),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp, start = 20.dp, end = 15.dp)
                    )

                    AllTexts(
                        stringResource(id = R.string.history_4),
                        fontSize = 16,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.padding(top = 5.dp, start = 20.dp, end = 15.dp),
                        dark = dark
                    )

                    AllTexts(
                        stringResource(id = R.string.history_5),
                        fontSize = 16,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.padding(
                            top = 5.dp,
                            start = 20.dp,
                            end = 15.dp,
                            bottom = 10.dp
                        ),
                        dark = dark
                    )


                }
            }
        }
    }
}

