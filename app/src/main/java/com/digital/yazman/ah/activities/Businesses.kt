package com.digital.yazman.ah.activities

import android.content.Intent
import android.os.Bundle
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.digital.yazman.ah.R
import com.digital.yazman.ah.nonScaledSp
import com.digital.yazman.ah.ui.theme.DigitalYazmanTheme

class Businesses : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val context = LocalContext.current
            DigitalYazmanTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFADD8E6))
                        .verticalScroll(rememberScrollState())

                ) {
                    //business start
                    AllTexts(
                        "Businesses",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 25,
                        modifier = Modifier.padding(top = 15.dp, start = 20.dp)
                    )


                    // first row with 3 cards
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        //first row first card

                        CardColumn(
                            painter = painterResource(id = R.drawable.agriculture),
                            title = "Agriculture",
                            fontSize = 12,
                            fontWeight = FontWeight.Light,
                            cardModifier = Modifier,
                            columnModifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                                .clickable {
                                    val intent = Intent(this@Businesses, BusinessesViews::class.java)
                                    intent.putExtra("business","Agriculture")
                                    startActivity(intent)
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))

                        //first row second card

                        CardColumn(
                            painter = painterResource(id = R.drawable.aluminium),
                            title = "Aluminium",
                            fontSize = 12,
                            fontWeight = FontWeight.Light,
                            cardModifier = Modifier,
                            columnModifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                                .clickable {
                                    val intent = Intent(this@Businesses, BusinessesViews::class.java)
                                    intent.putExtra("business","Aluminium")
                                    startActivity(intent)
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))

                        //first row third card

                        CardColumn(
                            painter = painterResource(id = R.drawable.automobile),
                            title = "Automobile",
                            fontSize = 12,
                            fontWeight = FontWeight.Light,
                            cardModifier = Modifier,
                            columnModifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                                .clickable {
                                    val intent = Intent(this@Businesses, BusinessesViews::class.java)
                                    intent.putExtra("business","Automobile")
                                    startActivity(intent)
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                        )
                    }

                    // second row with 3 cards
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        //first row first card

                        CardColumn(
                            painter = painterResource(id = R.drawable.banks),
                            title = "Banks",
                            fontSize = 12,
                            fontWeight = FontWeight.Light,
                            cardModifier = Modifier,
                            columnModifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                                .clickable {
                                    val intent = Intent(this@Businesses, BusinessesViews::class.java)
                                    intent.putExtra("business","Banks")
                                    startActivity(intent)
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))

                        //first row second card

                        CardColumn(
                            painter = painterResource(id = R.drawable.books),
                            title = "Books",
                            fontSize = 12,
                            fontWeight = FontWeight.Light,
                            cardModifier = Modifier,
                            columnModifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                                .clickable {
                                    val intent = Intent(this@Businesses, BusinessesViews::class.java)
                                    intent.putExtra("business","Books")
                                    startActivity(intent)
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))

                        //first row third card

                        CardColumn(
                            painter = painterResource(id = R.drawable.clothing),
                            title = "Clothing",
                            fontSize = 12,
                            fontWeight = FontWeight.Light,
                            cardModifier = Modifier,
                            columnModifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                                .clickable {
                                    val intent = Intent(this@Businesses, BusinessesViews::class.java)
                                    intent.putExtra("business","Clothing")
                                    startActivity(intent)
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                        )
                    }


                    // third row with 3 cards
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        //first row first card

                        CardColumn(
                            painter = painterResource(id = R.drawable.computeraccessories),
                            title = "Computers",
                            fontSize = 12,
                            fontWeight = FontWeight.Light,
                            cardModifier = Modifier,
                            columnModifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                                .clickable {
                                    val intent = Intent(this@Businesses, BusinessesViews::class.java)
                                    intent.putExtra("business","Computers")
                                    startActivity(intent)
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))

                        //first row second card

                        CardColumn(
                            painter = painterResource(id = R.drawable.education),
                            title = "Education",
                            fontSize = 12,
                            fontWeight = FontWeight.Light,
                            cardModifier = Modifier,
                            columnModifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                                .clickable {
                                    val intent = Intent(this@Businesses, BusinessesViews::class.java)
                                    intent.putExtra("business","Education")
                                    startActivity(intent)
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))

                        //first row third card

                        CardColumn(
                            painter = painterResource(id = R.drawable.electrics),
                            title = "Electric",
                            fontSize = 12,
                            fontWeight = FontWeight.Light,
                            cardModifier = Modifier,
                            columnModifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                                .clickable {
                                    val intent = Intent(this@Businesses, BusinessesViews::class.java)
                                    intent.putExtra("business","Electric")
                                    startActivity(intent)
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                        )
                    }

                    // forth row with 3 cards
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        //first row first card

                        CardColumn(
                            painter = painterResource(id = R.drawable.footwear),
                            title = "Footwear",
                            fontSize = 12,
                            fontWeight = FontWeight.Light,
                            cardModifier = Modifier,
                            columnModifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                                .clickable {
                                    val intent = Intent(this@Businesses, BusinessesViews::class.java)
                                    intent.putExtra("business","Footwear")
                                    startActivity(intent)
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))

                        //first row second card

                        CardColumn(
                            painter = painterResource(id = R.drawable.freshmeat),
                            title = "Fresh Meat",
                            fontSize = 12,
                            fontWeight = FontWeight.Light,
                            cardModifier = Modifier,
                            columnModifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                                .clickable {
                                    val intent = Intent(this@Businesses, BusinessesViews::class.java)
                                    intent.putExtra("business","Fresh Meat")
                                    startActivity(intent)
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))

                        //first row third card

                        CardColumn(
                            painter = painterResource(id = R.drawable.grainmarket),
                            title = "Grain Market",
                            fontSize = 12,
                            fontWeight = FontWeight.Light,
                            cardModifier = Modifier,
                            columnModifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                                .clickable {
                                    val intent = Intent(this@Businesses, BusinessesViews::class.java)
                                    intent.putExtra("business","Grain Market")
                                    startActivity(intent)
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                        )
                    }


                    // fifth row with 3 cards
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        //first row first card

                        CardColumn(
                            painter = painterResource(id = R.drawable.grocery),
                            title = "Grocery",
                            fontSize = 12,
                            fontWeight = FontWeight.Light,
                            cardModifier = Modifier,
                            columnModifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                                .clickable {
                                    val intent = Intent(this@Businesses, BusinessesViews::class.java)
                                    intent.putExtra("business","Grocery")
                                    startActivity(intent)
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))

                        //first row second card

                        CardColumn(
                            painter = painterResource(id = R.drawable.jewelry),
                            title = "Jewelry",
                            fontSize = 12,
                            fontWeight = FontWeight.Light,
                            cardModifier = Modifier,
                            columnModifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                                .clickable {
                                    val intent = Intent(this@Businesses, BusinessesViews::class.java)
                                    intent.putExtra("business","Jewelry")
                                    startActivity(intent)
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))

                        //first row third card

                        CardColumn(
                            painter = painterResource(id = R.drawable.machinework),
                            title = "Machine Work",
                            fontSize = 12,
                            fontWeight = FontWeight.Light,
                            cardModifier = Modifier,
                            columnModifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                                .clickable {
                                    val intent = Intent(this@Businesses, BusinessesViews::class.java)
                                    intent.putExtra("business","Machine Work")
                                    startActivity(intent)
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                        )
                    }

                    // sixth row with 3 cards
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        //first row first card

                        CardColumn(
                            painter = painterResource(id = R.drawable.marble),
                            title = "Marble",
                            fontSize = 12,
                            fontWeight = FontWeight.Light,
                            cardModifier = Modifier,
                            columnModifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                                .clickable {
                                    val intent = Intent(this@Businesses, BusinessesViews::class.java)
                                    intent.putExtra("business","Marble")
                                    startActivity(intent)
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))

                        //first row second card

                        CardColumn(
                            painter = painterResource(id = R.drawable.marriagehall),
                            title = "Marriage Hall",
                            fontSize = 12,
                            fontWeight = FontWeight.Light,
                            cardModifier = Modifier,
                            columnModifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                                .clickable {
                                    val intent = Intent(this@Businesses, BusinessesViews::class.java)
                                    intent.putExtra("business","Marriage Hall")
                                    startActivity(intent)
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))

                        //first row third card

                        CardColumn(
                            painter = painterResource(id = R.drawable.mobileaccessories),
                            title = "Mobile Accessories",
                            fontSize = 12,
                            fontWeight = FontWeight.Light,
                            cardModifier = Modifier,
                            columnModifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                                .clickable {
                                    val intent = Intent(this@Businesses, BusinessesViews::class.java)
                                    intent.putExtra("business","Mobile Accessories")
                                    startActivity(intent)
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                        )
                    }


                    // seventh row with 3 cards
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        //first row first card

                        CardColumn(
                            painter = painterResource(id = R.drawable.petroleum),
                            title = "Petroleum",
                            fontSize = 12,
                            fontWeight = FontWeight.Light,
                            cardModifier = Modifier,
                            columnModifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                                .clickable {
                                    val intent = Intent(this@Businesses, BusinessesViews::class.java)
                                    intent.putExtra("business","Petroleum")
                                    startActivity(intent)
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))

                        //first row second card

                        CardColumn(
                            painter = painterResource(id = R.drawable.photoshop),
                            title = "Photo & Copy Shop",
                            fontSize = 12,
                            fontWeight = FontWeight.Light,
                            cardModifier = Modifier,
                            columnModifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                                .clickable {
                                    val intent = Intent(this@Businesses, BusinessesViews::class.java)
                                    intent.putExtra("business","Photo & Copy Shop")
                                    startActivity(intent)
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))

                        //first row third card

                        CardColumn(
                            painter = painterResource(id = R.drawable.propertydealer),
                            title = "Property Dealer",
                            fontSize = 12,
                            fontWeight = FontWeight.Light,
                            cardModifier = Modifier,
                            columnModifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                                .clickable {
                                    val intent = Intent(this@Businesses, BusinessesViews::class.java)
                                    intent.putExtra("business","Property Dealer")
                                    startActivity(intent)
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                        )
                    }


                    // eighth row with 3 cards
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        //first row first card

                        CardColumn(
                            painter = painterResource(id = R.drawable.restaurant),
                            title = "Restaurants",
                            fontSize = 12,
                            fontWeight = FontWeight.Light,
                            cardModifier = Modifier,
                            columnModifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                                .clickable {
                                    val intent = Intent(this@Businesses, BusinessesViews::class.java)
                                    intent.putExtra("business","Restaurants")
                                    startActivity(intent)
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))

                        //first row second card

                        CardColumn(
                            painter = painterResource(id = R.drawable.steeliron),
                            title = "Steel & Iron",
                            fontSize = 12,
                            fontWeight = FontWeight.Light,
                            cardModifier = Modifier,
                            columnModifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                                .clickable {
                                    val intent = Intent(this@Businesses, BusinessesViews::class.java)
                                    intent.putExtra("business","Steel & Iron")
                                    startActivity(intent)
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))

                        //first row third card

                        CardColumn(
                            painter = painterResource(id = R.drawable.sweets),
                            title = "Sweets",
                            fontSize = 12,
                            fontWeight = FontWeight.Light,
                            cardModifier = Modifier,
                            columnModifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                                .clickable {
                                    val intent = Intent(this@Businesses, BusinessesViews::class.java)
                                    intent.putExtra("business","Sweets")
                                    startActivity(intent)
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                        )
                    }

                    // nine row with 3 cards
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        //nine row first card

                        CardColumn(
                            painter = painterResource(id = R.drawable.tehsiloffice),
                            title = "Tehsil Office",
                            fontSize = 12,
                            fontWeight = FontWeight.Light,
                            cardModifier = Modifier,
                            columnModifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                                .clickable {
                                    val intent = Intent(this@Businesses, BusinessesViews::class.java)
                                    intent.putExtra("business","Tehsil Office")
                                    startActivity(intent)
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                        )
                        Spacer(modifier = Modifier.padding(start = 20.dp, end = 20.dp))

                        //nine row second card

                        CardColumn(
                            painter = painterResource(id = R.drawable.woodworking),
                            title = "Wood Work",
                            fontSize = 12,
                            fontWeight = FontWeight.Light,
                            cardModifier = Modifier,
                            columnModifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                                .clickable {
                                    val intent = Intent(this@Businesses, BusinessesViews::class.java)
                                    intent.putExtra("business","Wood Work")
                                    startActivity(intent)
                                },
                            imageModifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                        )
                    }
                    //categories end
                }
            }
        }
    }
}


@Composable
fun CardColumn(
    painter: Painter,
    title: String,
    fontSize: Int = 12,
    fontWeight: FontWeight = FontWeight.Light,
    cardModifier: Modifier,
    columnModifier: Modifier,
    imageModifier: Modifier,

    ) {
    Card(
        elevation = 6.dp,
        shape = RoundedCornerShape(15.dp),
        backgroundColor = Color(0xFFFFFFFF),
        modifier = cardModifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = columnModifier

        ) {
            Image(
                painter = painter, contentDescription = null, modifier = imageModifier
            )
            AllTexts(title, fontSize = fontSize, fontWeight = fontWeight)
        }
    }
}


@Composable
fun AllTexts(
    text: String,
    modifier: Modifier = Modifier.padding(5.dp),
    fontWeight: FontWeight = FontWeight.Light,
    fontSize: Int = 10,
    textAlign: TextAlign = TextAlign.Center,
    color: Color = Color(0xFF000000)
) {
    Text(
        text = text,
        color = color,
        fontFamily = fontFamily,
        fontSize = fontSize.nonScaledSp,
        textAlign = textAlign,
        fontWeight = fontWeight,
        modifier = modifier
    )
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