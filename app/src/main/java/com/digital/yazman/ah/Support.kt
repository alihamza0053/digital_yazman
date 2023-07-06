package com.digital.yazman.ah

import android.os.Bundle
import android.provider.MediaStore.Images
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.imageLoader
import com.digital.yazman.ah.ui.theme.DigitalYazmanTheme

class Support : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DigitalYazmanTheme {
                FaqContact()
            }
        }
    }
}

val fontFamily = FontFamily(
    Font(R.font.lexend_black, FontWeight.Bold),
    Font(R.font.lexend_bold, FontWeight.Bold),
    Font(R.font.lexend_extrabold, FontWeight.ExtraBold),
    Font(R.font.lexend_light, FontWeight.Light),
    Font(R.font.lexend_medium, FontWeight.Medium),
    Font(R.font.lexend_extralight, FontWeight.ExtraLight),
    Font(R.font.lexend_regular, FontWeight.Normal),
    Font(R.font.lexend_semibold, FontWeight.SemiBold),
    Font(R.font.lexend_thin, FontWeight.Thin),
)

@Composable
fun FaqContact() {
    val context = LocalContext.current
    var white_text by remember {
        mutableStateOf(Color(0xFFFFFFFF))
    }
    var white_bg by remember {
        mutableStateOf(Color(0xFFFFFFFF))
    }
    var purple_bg by remember {
        mutableStateOf(Color(0xFF800080))
    }
    var black_text by remember {
        mutableStateOf(Color(0xFF000000))
    }
    var visible by remember {
        mutableStateOf(true)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFADD8E6))
            .verticalScroll(rememberScrollState())


    ) {
        //business start
        Text(
            text = "Support",
            color = Color(0xFF000000),
            fontSize = 25.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(top = 15.dp, start = 20.dp, bottom = 10.dp)
        )
        Row(
            modifier = Modifier.padding(5.dp),
        ) {
            Spacer(modifier = Modifier.padding(10.dp))

            Text(text = "FAQ",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontFamily = fontFamily,
                color = white_text,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .background(purple_bg)
                    .padding(10.dp)
                    .weight(1f)
                    .clickable {
                        black_text = Color(0xFF000000)
                        white_text = Color(0xFFFFFFFF)
                        purple_bg = Color(0xFF800080)
                        white_bg = Color(0xFFFFFFFF)
                        visible = true
                    })
            Spacer(modifier = Modifier.padding(10.dp))

            Text(text = "Contact",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontFamily = fontFamily,
                color = black_text,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .background(white_bg)
                    .padding(10.dp)
                    .weight(1f)
                    .clickable {
                        black_text = Color(0xFFFFFFFF)
                        white_text = Color(0xFF000000)
                        purple_bg = Color(0xFFFFFFFF)
                        white_bg = Color(0xFF800080)
                        visible = false
                    })
            Spacer(modifier = Modifier.padding(10.dp))
        }

        //FAQ condition
        if (visible) {
            //FAQs column
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(25.dp)
            ) {
                // first question
                Text(
                    text = "Where my business ads will be shown and for how long in digital Yazman?",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Justify
                )
                Text(
                    text = "Your ads will be shown on dashboard in image slider. Each ad will be shown for limited time, depends on your investment.",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Justify
                )

                // second question
                Text(
                    text = "Are business ads being free?",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(top = 20.dp)
                )
                Text(
                    text = "No, you have to pay for each ad.",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Justify,

                    )
            }
        }

        //Contact condition
        if (!visible) {

            //Contact Column
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(25.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.hamza2),
                    contentDescription = null,
                    modifier = Modifier
                        .width(200.dp)
                        .height(200.dp),
                    contentScale = ContentScale.Fit
                )
                Text(
                    text = "Ali Hamza",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    modifier = Modifier.padding(top = 10.dp)
                )

                Spacer(
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 10.dp)
                        .height(2.dp)
                        .fillMaxWidth()
                        .background(Color(0xFF800080))
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 5.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.phone),
                        contentDescription = null,
                        modifier = Modifier
                            .width(25.dp)
                            .height(25.dp)
                    )
                    Spacer(modifier = Modifier.padding(start = 20.dp, end = 20.dp))
                    Text(
                        text = "03065600053",
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 20.sp
                    )
                }
                Spacer(
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 10.dp)
                        .height(2.dp)
                        .fillMaxWidth()
                        .background(Color(0xFF800080))
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 5.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.whatsapp),
                        contentDescription = null,
                        modifier = Modifier
                            .width(25.dp)
                            .height(25.dp)
                    )
                    Spacer(modifier = Modifier.padding(start = 20.dp, end = 20.dp))
                    Text(
                        text = "03065600053",
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 20.sp
                    )

                }
                Spacer(
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 10.dp)
                        .height(2.dp)
                        .fillMaxWidth()
                        .background(Color(0xFF800080))
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 5.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.gmail),
                        contentDescription = null,
                        modifier = Modifier
                            .width(25.dp)
                            .height(25.dp)
                    )
                    Spacer(modifier = Modifier.padding(start = 20.dp, end = 20.dp))
                    Text(
                        text = "alihamza00053@gmail.com",
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 20.sp
                    )

                }
                Spacer(
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 10.dp)
                        .height(2.dp)
                        .fillMaxWidth()
                        .background(Color(0xFF800080))
                )
                Text(
                    text = "In case of any issue direct contact with me on any platform, so that I can fix the issue and make the app more compatible for you. Thanks!",
                    textAlign = TextAlign.Justify,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp
                )
            }
        }

    }
}
