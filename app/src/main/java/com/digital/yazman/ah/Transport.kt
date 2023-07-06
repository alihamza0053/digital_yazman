package com.digital.yazman.ah

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.widget.TextViewCompat.AutoSizeTextType
import com.digital.yazman.ah.ui.theme.DigitalYazmanTheme

class Transport : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DigitalYazmanTheme {
                // A surface container using the 'background' color from the theme
                MainColumn()


            }
        }
    }
}

@Composable
fun MainColumn() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFADD8E6))
            .verticalScroll(rememberScrollState())

    ) {
        Text(
            text = "Transportation",
            color = Color(0xFF000000),
            fontSize = 25.nonScaledSp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(top = 15.dp, start = 20.dp)
        )
        Bus("Fasial Movers","03065600053","12:00pm","03:00pm","Yazman","Multan","1200","300","3 Hours")
        Bus("Niazi Express","03065600053","06:00am","12:00pm","Yazman","Lahore","1500","500","6 Hours")
        Bus("Fasial Movers","03065600053","12:00pm","03:00pm","Yazman","Multan","1200","300","3 Hours")
        Bus("Niazi Express","03065600053","06:00am","12:00pm","Yazman","Lahore","1500","500","6 Hours")
        Bus("Fasial Movers","03065600053","12:00pm","03:00pm","Yazman","Multan","1200","300","3 Hours")
        Bus("Niazi Express","03065600053","06:00am","12:00pm","Yazman","Lahore","1500","500","6 Hours")
        Bus("Fasial Movers","03065600053","12:00pm","03:00pm","Yazman","Multan","1200","300","3 Hours")
        Bus("Niazi Express","03065600053","06:00am","12:00pm","Yazman","Lahore","1500","500","6 Hours")


    }

}

@Composable
fun Bus(busName:String, busNumber:String,startTime:String,arivalTime:String,startPoint:String,destination:String,ticketPrice:String,distance:String,timeTaken:String) {
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .background(Color.White),
    ) {

        //first row
        Row(
            modifier = Modifier
                .padding(start = 20.dp, top = 5.dp, end = 20.dp, bottom = 5.dp)
        ) {
            Text(
                text = busName,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 15.nonScaledSp
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = busNumber,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 15.nonScaledSp
            )
        }

        Spacer(
            modifier = Modifier
                .background(Color(0xFFADD8E6))
                .fillMaxWidth()
                .height(2.dp)
        )


        //second row
        Row(
            modifier = Modifier
                .padding(start = 20.dp, top = 5.dp, end = 20.dp, bottom = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(end = 20.dp)
            ) {
                Text(
                    text = startTime,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.nonScaledSp,
                    modifier = Modifier
                        .padding(bottom = 17.dp)
                )
                Text(
                    text = arivalTime,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.nonScaledSp
                )
            }
            Image(
                painter = painterResource(id = R.drawable.circles),
                contentDescription = null,
                modifier = Modifier
                    .width(55.dp)
                    .height(55.dp)
                    .padding(top = 4.dp)
            )
            Column {
                Text(
                    text = startPoint,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.nonScaledSp,
                    modifier = Modifier
                        .padding(bottom = 17.dp)
                )
                Text(
                    text = destination,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.nonScaledSp
                )
            }
            Card(
                colors = CardDefaults.cardColors(Color(0xFF800080)),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.padding(start = 30.dp),
            ) {

                Text(
                    text = ticketPrice + "Rs",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    modifier = Modifier
                        .padding(10.dp)
                )
            }
        }
        Spacer(
            modifier = Modifier
                .background(Color(0xFFADD8E6))
                .fillMaxWidth()
                .height(2.dp)
        )


        //third row
        Row(
            modifier = Modifier
                .padding(start = 20.dp, top = 5.dp, end = 20.dp, bottom = 5.dp)
        ) {
            Text(
                text = distance + "km",
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 15.nonScaledSp
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Time $timeTaken",
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 15.nonScaledSp
            )
        }
    }
}
