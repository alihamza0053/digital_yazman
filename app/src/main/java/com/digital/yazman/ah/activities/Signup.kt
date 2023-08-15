package com.digital.yazman.ah.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.digital.yazman.ah.R
import com.digital.yazman.ah.classes.LoginInfo
import com.digital.yazman.ah.datastore.StoreLightDarkData
import com.digital.yazman.ah.nonScaledSp
import com.digital.yazman.ah.ui.theme.DigitalYazmanTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Signup : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            var darkValue = getIntent().getBooleanExtra("dark",false)
            var dark by remember {
                mutableStateOf(darkValue)
            }
            BackHandler(enabled = true, onBack = {
            })
            var backgroundColor = Color(0xFFADD8E6)
            var textColor = Color(0xFF000000)
            val db = Firebase.firestore
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
            var notify by remember {
                mutableStateOf("0")
            }
            var verify by remember {
                mutableStateOf(0)
            }
            var emailCheck by remember {
                mutableStateOf("")
            }
            var dialog = false
            var nameCheck = 0
            var ids = 0
            db.collection("Users").get().addOnSuccessListener { results ->
                for (document in results) {
                    ids = document.get("id").toString().subSequence(3, document.get("id").toString().length).toString().toInt()
                    if(nameCheck <= ids ){
                        nameCheck = ids + 1
                        id = "DYU0" + nameCheck.toString()
                    }
                }
            }
                .addOnFailureListener {
                    Toast.makeText(applicationContext, "Check Internet", Toast.LENGTH_SHORT).show()
                }
            DigitalYazmanTheme {
                if (dark) {
                    backgroundColor = Color(0xFF14141f)
                    textColor = Color(0xFFFFFFFF)
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(backgroundColor)
                        .verticalScroll(rememberScrollState())
                        .padding(20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {

                    //business start
                    AllTexts(
                        text = "Sign up",
                        fontSize = 25,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 15.dp, start = 20.dp, bottom = 20.dp),
                        dark = dark
                    )

                    Card(
                        modifier = Modifier
                            .border(1.dp, textColor)
                            .padding(17.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
                    ) {

                        AllTexts(
                            text = id,
                            fontSize = 15,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .fillMaxWidth(),
                            dark = dark,
                        )
                    }
                    OutlinedTextField(
                        value = name,
                        onValueChange = {
                            name = it
                        },
                        label = { Text(text = "Name",color = textColor) },
                        modifier = Modifier
                            .fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                        ),
                        textStyle = TextStyle.Default.copy(
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Normal,
                            color = textColor
                        )
                    )


                    OutlinedTextField(
                        value = email,
                        onValueChange = {
                            email = it
                        },
                        label = { Text(text = "Email", color = textColor) },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                        ),
                        textStyle = TextStyle.Default.copy(
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Normal,
                            color = textColor
                        )
                    )

                    OutlinedTextField(
                        value = address,
                        onValueChange = {
                            address = it
                        },
                        label = { Text(text = "Address", color = textColor) },
                        modifier = Modifier
                            .fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                        ),
                        textStyle = TextStyle.Default.copy(
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Normal,
                            color = textColor
                        )
                    )

                    OutlinedTextField(
                        value = phone,
                        onValueChange = {
                            phone = it
                        },
                        label = { Text(text = "Phone Number", color = textColor) },
                        modifier = Modifier
                            .fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                        ),
                        textStyle = TextStyle.Default.copy(
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Normal,
                            color = textColor
                        )
                    )


                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            password = it
                        },
                        textStyle = TextStyle(
                            color = textColor
                        ),
                        label = { Text(text = "Password", color = textColor) },
                        modifier = Modifier.fillMaxWidth(),
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )


                    Spacer(
                        modifier = Modifier.padding(2.dp)
                    )

                    Button(
                        onClick = {
                            signUpWithEmail(email,password)
                            dialog = true
                            enable = false
                            val data = LoginInfo(
                                id.trim(),
                                name.trim(),
                                address.trim(),
                                phone.trim(),
                                email.trim(),
                                notify.trim(),
                                verify
                            )

                            db.collection("Users").get().addOnSuccessListener { results ->
                                for (document in results) {
                                    if (email == document.get("email").toString()) {
                                        emailCheck = document.get("email").toString()
                                    }
                                }
                            }
                            if (name != "" && email != "" && address != "" && phone != "" && password != "") {
                                Toast.makeText(applicationContext, emailCheck, Toast.LENGTH_SHORT)
                                    .show()
                                db.collection("Users").document(id).set(data)
                                    .addOnSuccessListener {
                                        dialog = false
                                        Toast.makeText(
                                            applicationContext,
                                            "record added",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                        context.startActivity(Intent(context, Login::class.java).putExtra("dark",dark))
                                        finish()
                                    }.addOnFailureListener {
                                        enable = true
                                    }
                            } else {
                                dialog = false
                                Toast.makeText(
                                    applicationContext,
                                    "Fill All Fields.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
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

                    LoadingView(modifier = Modifier.padding(16.dp), dialog)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                    ) {
                        AllTexts(
                            text = "Already have account!",
                            fontSize = 12,
                            fontWeight = FontWeight.Normal,
                            dark = dark
                        )

                        Spacer(
                            modifier = Modifier
                                .weight(1f)
                        )

                        Text(
                            text = "Login",
                            color = Color(0xFF800080),
                            fontSize = 17.nonScaledSp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .clickable {
                                    startActivity(Intent(context, Login::class.java).putExtra("dark",dark))
                                    finish()
                                }
                        )
                    }
                }
            }
        }
    }
}



private fun sendVerificationEmail() {
    val user = FirebaseAuth.getInstance().currentUser
    user?.sendEmailVerification()
        ?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Email sent
            } else {
                // Email not sent
            }
        }
}
private fun signUpWithEmail(email: String, password: String) {

    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = FirebaseAuth.getInstance().currentUser
                if (user != null && !user.isEmailVerified) {
                    sendVerificationEmail()
                }
            }
        }
}

@Composable
fun LoadingView(
    modifier: Modifier = Modifier,
    isVisible: Boolean = true
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Row(
            modifier
                .shadow(2.dp, CircleShape)
                .clip(CircleShape)
                .background(MaterialTheme.colors.surface)
                .padding(0.dp, 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .size(16.dp),
                strokeWidth = 2.dp
            )
            androidx.compose.material.Text(
                modifier = Modifier.padding(end = 12.dp),
                text = "Loading...",
                fontSize = 14.sp,
                color = MaterialTheme.colors.onSurface.copy(alpha = .75f)
            )
        }
    }
}
