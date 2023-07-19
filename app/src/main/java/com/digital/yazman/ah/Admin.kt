package com.digital.yazman.ah

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.LayoutDirection
import android.util.Size
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import com.digital.yazman.ah.ui.theme.DigitalYazmanTheme
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text
import kotlin.math.sqrt

class Admin : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            DigitalYazmanTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFADD8E6))
                ) {
                    Text(
                        text = "Ali Hamza",
                        fontSize = 20.nonScaledSp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF000000),
                        modifier = Modifier
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                20.dp
                            )
                    ) {
                        Hexagon("Business",
                            modifier = Modifier.clickable {
                                context.startActivity(
                                    Intent(context, LocalDealsAdmin::class.java)
                                )
                            })
                        Spacer(modifier = Modifier.weight(1f))
                        Hexagon("Local Deals",
                            modifier = Modifier.clickable {
                                context.startActivity(
                                    Intent(context, LocalDealsAdmin::class.java)
                                )
                            })
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                20.dp
                            )
                    ) {

                        Hexagon("Local News",
                            modifier = Modifier.clickable {
                                context.startActivity(
                                    Intent(context, LocalNewsAdmin::class.java)
                                )
                            })
                        Spacer(modifier = Modifier.weight(1f))
                        Hexagon("MS ads",
                            modifier = Modifier.clickable {
                                context.startActivity(
                                    Intent(context, LocalDealsAdmin::class.java)
                                )
                            })
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                20.dp
                            )
                    ) {

                        Hexagon("Notification",
                            modifier = Modifier.clickable {
                                context.startActivity(
                                    Intent(context, OpportunitiesAdmin::class.java)
                                )
                            })
                        Spacer(modifier = Modifier.weight(1f))
                        Hexagon("Opportunities",
                            modifier = Modifier.clickable {
                                context.startActivity(
                                    Intent(context, OpportunitiesAdmin::class.java)
                                )
                            })
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                20.dp
                            )
                    ) {

                        Hexagon("Services",
                            modifier = Modifier.clickable {
                                context.startActivity(
                                    Intent(context, OpportunitiesAdmin::class.java)
                                )
                            })
                        Spacer(modifier = Modifier.weight(1f))
                        Hexagon("Unknown",
                            modifier = Modifier.clickable {
                                context.startActivity(
                                    Intent(context, LocalDealsAdmin::class.java)
                                )
                            })
                    }
                }
            }
        }
    }
}

@Composable
fun Hexagon(text: String, modifier: Modifier) {
    Box(
        modifier = modifier
            .clip(CutCornerShape(8.dp))
            .background(Color(0xFF000000))
            .height(70.dp)
            .width(130.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 16.nonScaledSp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium,
            color = Color(0xFFFFFFFF)
        )
    }
}
//