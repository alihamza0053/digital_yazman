package com.digital.yazman.ah.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.digital.yazman.ah.ui.theme.DigitalYazmanTheme
import com.digital.yazman.ah.classes.LoginInfo
import com.digital.yazman.ah.classes.NotificationClass
import com.digital.yazman.ah.nonScaledSp
import com.google.common.base.Verify
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserProfile : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            val darkValue = getIntent().getBooleanExtra("dark", false)
            val name = getIntent().getStringExtra("name")
            val email = getIntent().getStringExtra("email")
            val verify = getIntent().getStringExtra("verify")
            val id = getIntent().getStringExtra("id")

            var dark by remember {
                mutableStateOf(darkValue)
            }
            val db = FirebaseFirestore.getInstance()
            val itemsState = remember { mutableStateOf(emptyList<LoginInfo>()) }
            var backgroundColor = Color(0xFFFFFFFF)
            var textColor = Color(0xFF000000)
            val user = FirebaseAuth.getInstance().currentUser
            DigitalYazmanTheme {
                // A surface container using the 'background' color from the theme
                if (dark) {
                    backgroundColor = Color(0xFF14141f)
                    textColor = Color(0xFFFFFFFF)
                }
                BackHandler(enabled = true, onBack = {
                    context.startActivity(
                        Intent(
                            this@UserProfile,
                            menuActivity::class.java
                        ).putExtra("dark", dark)
                    )
                    finish()
                })
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(backgroundColor)
                        .verticalScroll(rememberScrollState())
                        .padding(10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {

        //            Toast.makeText(context, email.toString(), Toast.LENGTH_SHORT).show()
                    LaunchedEffect(Unit) {

                        val querySnapshot = db.collection("Users").get().await()
                        val userDataList = mutableListOf<LoginInfo>()
                        for (document in querySnapshot) {
                            if (document.getString("id") == id && document.getString("email") == email) {
                                val id = document.getString("id") ?: ""
                                val name = document.getString("name") ?: ""
                                val email = document.getString("email") ?: ""
                                val phone = document.getString("phone") ?: ""
                                val address = document.getString("address") ?: ""
                                val date = document.getString("date") ?: ""
                                val verify = document.get("verify") ?: 0
                                val notify = document.getString("notify") ?: ""
                                val accountType = document.getString("accountType") ?: ""
                                userDataList.add(
                                    LoginInfo(
                                        id = id,
                                        name = name,
                                        email = email,
                                        phone = phone,
                                        address = address,
                                        date = date,
                                        verify = verify.toString().toInt(),
                                        notify = notify,
                                        accountType = accountType
                                    )
                                )
                            }
                        }
                        itemsState.value = userDataList
                    }

                    if (itemsState.value.isEmpty()) {
                        UserData(
                            id = "DYU00",
                            name = "Name",
                            email = "Email@gmail.com",
                            phone = "03XXXXXXXXX",
                            address = "Yazman",
                            date = "00-00-2000",
                            verify = "0",
                            notify = "0",
                            accountType = "Account Type",
                            textColor = textColor,
                            user = user,
                            dark = dark,
                            context = context
                        )
                    }

                    itemsState.value.forEach { data ->
                        UserData(
                            id = data.id,
                            name = data.name,
                            email = data.email,
                            phone = data.phone,
                            address = data.address,
                            date = data.date,
                            verify = data.verify.toString(),
                            notify = data.notify,
                            accountType = data.accountType,
                            textColor = textColor,
                            user = user,
                            dark = dark,
                            context = context
                        )
                    }
                    Column {
                        AllTexts(
                            "Need any help contact Admin.",
                            fontSize = 10,
                            fontWeight = FontWeight.Light,
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(alignment = CenterHorizontally)
                                .padding(bottom = 5.dp),
                            dark = dark
                        )
                    }

                }


            }
        }
    }
}


@Composable
fun UserData(
    id: String,
    name: String,
    email: String,
    phone: String,
    address: String,
    date: String,
    verify: String,
    notify: String,
    accountType: String,
    textColor: Color,
    user: FirebaseUser?,
    dark: Boolean,
    context: Context,
) {
    var verification by remember {
        mutableStateOf("Not Verified")
    }
    var verificationColor by remember {
        mutableStateOf(Color.Red)
    }
 //   Toast.makeText(LocalContext.current, user!!.isEmailVerified.toString(), Toast.LENGTH_SHORT)
  //      .show()
    if (user!!.isEmailVerified) {
        verification = "Verified"
        verificationColor = Color.Green
    }

    var cardColor = Color(0xFFADD8E6)
    var textColor = Color(0xFF000000)
    var buttonColor = Color(0xFFFFFFFF)
    if (dark) {
        cardColor = Color(0xFF282834)
        textColor = Color(0xFFFFFFFF)
        buttonColor = Color(0xFF000000)
    }
    Card(
        colors = CardDefaults.cardColors(
            containerColor = cardColor
        ),
        modifier = Modifier
            .padding(10.dp)
            .height(600.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize()
        ) {
            AllTexts(
                name,
                fontSize = 20,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = CenterHorizontally)
                    .padding(bottom = 10.dp),
                dark = dark
            )

            OutlinedTextField(
                value = id, onValueChange = { null },
                label = {
                    AllTexts(
                        "ID",
                        fontSize = 13,
                        fontWeight = FontWeight.SemiBold,
                        dark = dark
                    )
                },
                textStyle = TextStyle.Default.copy(
                    fontFamily = fontFamily,
                    color = textColor,
                    fontSize = 15.nonScaledSp,
                    fontWeight = FontWeight.Normal,
                )
            )
            Spacer(modifier = Modifier.weight(1f))

            OutlinedTextField(
                value = email, onValueChange = { null },
                label = {
                    AllTexts(
                        "Email",
                        fontSize = 13,
                        fontWeight = FontWeight.SemiBold,
                        dark = dark
                    )
                },
                textStyle = TextStyle.Default.copy(
                    fontFamily = fontFamily,
                    color = textColor,
                    fontSize = 15.nonScaledSp,
                    fontWeight = FontWeight.Normal,
                )
            )
            Spacer(modifier = Modifier.weight(1f))

            OutlinedTextField(
                value = phone, onValueChange = { null },
                label = {
                    AllTexts(
                        "Phone",
                        fontSize = 13,
                        fontWeight = FontWeight.SemiBold,
                        dark = dark
                    )
                },
                textStyle = TextStyle.Default.copy(
                    fontFamily = fontFamily,
                    color = textColor,
                    fontSize = 15.nonScaledSp,
                    fontWeight = FontWeight.Normal,
                )
            )
            Spacer(modifier = Modifier.weight(1f))

            OutlinedTextField(
                value = address, onValueChange = { null },
                label = {
                    AllTexts(
                        "Address",
                        fontSize = 13,
                        fontWeight = FontWeight.SemiBold,
                        dark = dark
                    )
                },
                textStyle = TextStyle.Default.copy(
                    fontFamily = fontFamily,
                    color = textColor,
                    fontSize = 15.nonScaledSp,
                    fontWeight = FontWeight.Normal,
                )
            )
            Spacer(modifier = Modifier.weight(1f))

            OutlinedTextField(
                value = verification, onValueChange = { null },
                label = {
                    AllTexts(
                        "Email Verification",
                        fontSize = 13,
                        fontWeight = FontWeight.SemiBold,
                        dark = dark
                    )
                },
                textStyle = TextStyle.Default.copy(
                    fontFamily = fontFamily,
                    color = verificationColor,
                    fontSize = 15.nonScaledSp,
                    fontWeight = FontWeight.Normal,
                )
            )

            Spacer(modifier = Modifier.weight(1f))

            OutlinedTextField(
                value = accountType, onValueChange = { null },
                label = {
                    AllTexts(
                        "Account Type",
                        fontSize = 13,
                        fontWeight = FontWeight.SemiBold,
                        dark = dark
                    )
                },
                textStyle = TextStyle.Default.copy(
                    fontFamily = fontFamily,
                    color = textColor,
                    fontSize = 15.nonScaledSp,
                    fontWeight = FontWeight.Normal,
                )
            )

            Spacer(modifier = Modifier.weight(1f))

            OutlinedTextField(
                value = date, onValueChange = { null },
                label = {
                    AllTexts(
                        "Sign up Date",
                        fontSize = 13,
                        fontWeight = FontWeight.SemiBold,
                        dark = dark
                    )
                },
                textStyle = TextStyle.Default.copy(
                    fontFamily = fontFamily,
                    color = textColor,
                    fontSize = 15.nonScaledSp,
                    fontWeight = FontWeight.Normal,
                )
            )
            Button(
                onClick = {
                    if (!user!!.isEmailVerified) {
                        sendVerificationEmail(context = context)
                    }else(
                        Toast.makeText(context,"Already Verified.",Toast.LENGTH_SHORT).show()
                    )

                }, colors = ButtonDefaults.buttonColors(
                    containerColor = buttonColor
                ), modifier = Modifier.padding(top = 10.dp)
            ) {
                AllTexts(
                    "Send Verification Email",
                    fontSize = 13,
                    fontWeight = FontWeight.SemiBold,
                    dark = dark
                )
            }


        }
    }
}

private fun sendVerificationEmail(context: Context) {
    val user = FirebaseAuth.getInstance().currentUser
    user?.sendEmailVerification()
        ?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(context,"Verification Email Send.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context,task.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }
}
