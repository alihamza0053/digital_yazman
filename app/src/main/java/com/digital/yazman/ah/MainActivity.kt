package com.digital.yazman.ah

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.digital.yazman.ah.ui.theme.DigitalYazmanTheme
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DigitalYazmanTheme {

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
                // splash screen
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF800080))
                ) {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
//                        Image(
//                            painter = painterResource(id = R.drawable.logo_2),
//                            contentDescription = null,
//                            modifier = Modifier.size(200.dp)
//                        )
                        Text(
                            buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.Green, fontSize = 60.nonScaledSp
                                    )
                                ) {
                                    append("D")
                                }
                                append("i")
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.Green, fontSize = 60.nonScaledSp
                                    )
                                ) {
                                    append("g")
                                }
                                append("i")
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.Green, fontSize = 60.nonScaledSp
                                    )
                                ) {
                                    append("t")
                                }
                                append("a")
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.Green, fontSize = 60.nonScaledSp
                                    )
                                ) {
                                    append("l")
                                }
                            },
                            fontSize = 50.nonScaledSp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFFFFFF)
                        )
                        Text(
                            buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.Green, fontSize = 60.nonScaledSp
                                    )
                                ) {
                                    append("Y")
                                }
                                append("a")
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.Green, fontSize = 60.nonScaledSp
                                    )
                                ) {
                                    append("z")
                                }
                                append("ma")
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.Green, fontSize = 60.nonScaledSp
                                    )
                                ) {
                                    append("n")
                                }

                            },
                            fontSize = 50.nonScaledSp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFFFFFF)
                        )


                    }
                    Text(
                        text = "©copyright 2023",
                        fontSize = 15.nonScaledSp,
                        modifier = Modifier.align(Alignment.BottomCenter),
                        color = Color(0xFF78787b)
                    )
                }


                // delay to next activity
                val context = LocalContext.current
                val activity = LocalContext.current as Activity
                LaunchedEffect(Unit) {
                    delay(2.seconds)
                    context.startActivity(Intent(context, menuActivity::class.java))
                    activity.finish()
                }
            }
        }
    }
}
//
//@Preview(
//    showBackground = true, showSystemUi = true
//)
//@Composable
//fun DefaultPreview() {
//    DigitalYazmanTheme {
//        Greeting("Android")
//    }
//}
val Int.nonScaledSp
    @Composable
    get() = (this / LocalDensity.current.fontScale).sp