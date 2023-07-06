package com.digital.yazman.ah

import android.content.Intent
import android.os.Bundle
import android.provider.Settings.Global
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import com.digital.yazman.ah.ui.theme.DigitalYazmanTheme
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Signup : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val firestoreDatabase = Firebase.firestore
            val database = Firebase.database
            val myRef = database.getReference("Signup")

            var id by remember {
                mutableStateOf("ID")
            }
            var name by remember {
                mutableStateOf("")
            }
            var email by remember {
                mutableStateOf("")
            }
            var password by remember {
                mutableStateOf("")
            }
            var enable by remember {
                mutableStateOf(true)
            }
            var address by remember {
                mutableStateOf("")
            }
            var phone by remember {
                mutableStateOf("")
            }
            var verify by remember {
                mutableStateOf(0)
            }
            var nameCheck = 0
            firestoreDatabase.collection("Users").get().addOnSuccessListener { results ->
                for (document in results) {
                    nameCheck = document.get("id").toString().subSequence(3, document.get("id").toString().length).toString().toInt() + 1
                    id = "DYU0" + nameCheck.toString()


                }
//                id = "DYU0" + nameCheck
            }
//            myRef.get().addOnSuccessListener {
//                var nameCheck =
//                    it.children.last().key.toString()
//                        .subSequence(3, it.children.last().key.toString().length).toString()
//                        .toInt() + 1
//                id = "DYU0" + nameCheck.toString()
//            }
                .addOnFailureListener {
                    Toast.makeText(applicationContext, "Check Internet", Toast.LENGTH_SHORT).show()
                }

            val context = LocalContext.current
            DigitalYazmanTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFADD8E6))
                        .verticalScroll(rememberScrollState())
                        .padding(20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally


                ) {
                    //business start
                    Text(
                        text = "Sign up",
                        color = Color(0xFF000000),
                        fontSize = 25.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 15.dp, start = 20.dp, bottom = 20.dp)
                    )

                    Card(
                        modifier = Modifier
                            .border(1.dp, Color.Black)
                            .padding(17.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
                    ) {

                        Text(
                            text = id,
                            fontSize = 15.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .fillMaxWidth(),
                        )
                    }
                    OutlinedTextField(
                        value = name,
                        onValueChange = {
                            name = it
                        },
                        label = { Text(text = "Name") },
                        modifier = Modifier
                            .fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                        ),
                        textStyle = TextStyle.Default.copy(
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Normal,
                        )
                    )


                    OutlinedTextField(
                        value = email,
                        onValueChange = {
                            email = it
                        },
                        label = { Text(text = "Email") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                        ),
                        textStyle = TextStyle.Default.copy(
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Normal,
                        )
                    )

                    OutlinedTextField(
                        value = address,
                        onValueChange = {
                            address = it
                        },
                        label = { Text(text = "Address") },
                        modifier = Modifier
                            .fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                        ),
                        textStyle = TextStyle.Default.copy(
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Normal,
                        )
                    )

                    OutlinedTextField(
                        value = phone,
                        onValueChange = {
                            phone = it
                        },
                        label = { Text(text = "Phone Number") },
                        modifier = Modifier
                            .fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                        ),
                        textStyle = TextStyle.Default.copy(
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Normal,
                        )
                    )


                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            password = it
                        },
                        label = { Text(text = "Password") },
                        modifier = Modifier.fillMaxWidth(),
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )


                    Spacer(
                        modifier = Modifier.padding(2.dp)
                    )

                    Button(
                        onClick = {
                            enable = false
                            val data = LoginInfo(
                                id.trim(),
                                name.trim(),
                                address.trim(),
                                phone.trim(),
                                email.trim(),
                                password.trim(),
                                verify
                            )
//                            myRef.get().addOnSuccessListener {
//                                var check = name
//                                var a = it.children.last().key
//                                Toast.makeText(applicationContext, a.toString(), Toast.LENGTH_SHORT)
//                                    .show()
//                                it.children.forEach {
//                                    if (it.child("name").value == name) {
//                                        Toast.makeText(
//                                            applicationContext,
//                                            "Already ",
//                                            Toast.LENGTH_SHORT
//                                        )
//                                            .show()
//                                        check = "Unknown"
//                                    }
//                                }

//                                if (check == name) {
                            if (true) {
                                if (name != "" && email != "" && address != "" && phone != "" && password != "") {
                                    firestoreDatabase.collection("Users").document(id).set(data)
                                        .addOnSuccessListener {
                                            Toast.makeText(
                                                applicationContext,
                                                "record added",
                                                Toast.LENGTH_SHORT
                                            )
                                                .show()
                                        }
//                                    myRef.child(id).setValue(data).addOnSuccessListener {
//                                        Toast.makeText(
//                                            applicationContext,
//                                            "record added",
//                                            Toast.LENGTH_SHORT
//                                        )
//                                            .show()
//                                    }.addOnFailureListener {
//                                        Toast.makeText(
//                                            applicationContext,
//                                            "check your internet",
//                                            Toast.LENGTH_SHORT
//                                        )
//                                            .show()
//                                    }
                                } else {
                                    Toast.makeText(
                                        applicationContext,
                                        "Fill All Fields.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            } else {
                                Toast.makeText(
                                    applicationContext,
                                    "Wait until ID generated!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
//                                }
//                                else{
//                                    check = name
//                                }
                            enable = true
                        },
                        shape = RoundedCornerShape(3.dp),
                        modifier = Modifier.fillMaxWidth(),
                        enabled = enable,
                        colors = ButtonDefaults.buttonColors(Color(0xFF800080))
                    ) {
                        Text(
                            text = "Sign up", fontSize = 20.sp, color = Color(0xFFFFFFFF),
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.SemiBold,
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                    ) {
                        Text(
                            text = "Already have account!",
                            color = Color(0xFF000000),
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Normal,
                        )

                        Spacer(
                            modifier = Modifier
                                .weight(1f)
                        )

                        Text(
                            text = "Login",
                            color = Color(0xFF800080),
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .clickable {
                                    startActivity(Intent(context, Login::class.java))
                                    finish()
                                }
                        )
                    }
                }
            }
        }
    }
}
