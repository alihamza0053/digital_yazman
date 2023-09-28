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
import androidx.compose.ui.text.input.ImeAction
import com.digital.yazman.ah.R
import com.digital.yazman.ah.classes.LoginInfo
import com.digital.yazman.ah.datastore.StoreLightDarkData
import com.digital.yazman.ah.nonScaledSp
import com.digital.yazman.ah.ui.theme.DigitalYazmanTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Signup : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            var darkValue = getIntent().getBooleanExtra("dark", false)
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
            var nameError by remember {
                mutableStateOf(false)
            }
            var email by remember {
                mutableStateOf("")
            }
            var emailError by remember {
                mutableStateOf(false)
            }
            var password by remember {
                mutableStateOf("")
            }
            var passwordError by remember {
                mutableStateOf(false)
            }
            var enable by remember {
                mutableStateOf(true)
            }
            var address by remember {
                mutableStateOf("")
            }
            var addressError by remember {
                mutableStateOf(false)
            }
            var phone by remember {
                mutableStateOf("")
            }
            var phoneError by remember {
                mutableStateOf(false)
            }
            var notify by remember {
                mutableStateOf("0")
            }
            var accountType by remember {
                mutableStateOf("Will be Updated!")
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
                    ids = document.get("id").toString()
                        .subSequence(3, document.get("id").toString().length).toString().toInt()
                    if (nameCheck <= ids) {
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
                val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
                var date = dateFormat.format(Date())
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(backgroundColor)
                        .verticalScroll(rememberScrollState())
                        .padding(20.dp),
                    verticalArrangement = Arrangement.Center,

                    ) {

                    //business start
                    AllTexts(
                        text = "Create\nAccount",
                        fontSize = 30,
                        fontWeight = FontWeight.Light,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(top = 15.dp, bottom = 20.dp),
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
                            nameError = name.length > 12 || name.length < 3
                        },
                        label = { Text(text = "Name", color = textColor) },
                        modifier = Modifier
                            .fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        textStyle = TextStyle.Default.copy(
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Normal,
                            color = textColor
                        )
                    )
                    if (nameError) {
                        Text(
                            text = "Min 3, Max 12",
                            color = MaterialTheme.colors.error
                        )
                    }


                    OutlinedTextField(
                        value = email,
                        onValueChange = {
                            email = it
                            emailError = !email.contains("@gmail.com")
                        },
                        label = { Text(text = "Email", color = textColor) },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        ),
                        textStyle = TextStyle.Default.copy(
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Normal,
                            color = textColor
                        )
                    )

                    if (emailError) {
                        Text(
                            text = "Email must contain @gmail.com",
                            color = MaterialTheme.colors.error
                        )
                    }

                    OutlinedTextField(
                        value = address,
                        onValueChange = {
                            address = it
                            addressError = address.length < 0
                        },
                        label = { Text(text = "Address", color = textColor) },
                        modifier = Modifier
                            .fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        textStyle = TextStyle.Default.copy(
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Normal,
                            color = textColor
                        )
                    )

                    if (addressError) {
                        Text(
                            text = "There is no address!",
                            color = MaterialTheme.colors.error
                        )
                    }

                    OutlinedTextField(
                        value = phone,
                        onValueChange = {
                            phone = it
                            phoneError =
                                phone.length < 11 || phone.length > 11 || !phone.contains("03")
                        },
                        label = { Text(text = "Phone Number", color = textColor) },
                        modifier = Modifier
                            .fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        textStyle = TextStyle.Default.copy(
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Normal,
                            color = textColor
                        )
                    )
                    if (phoneError) {
                        Text(
                            text = "Complete Number: 03XXXXXXXXX",
                            color = MaterialTheme.colors.error
                        )
                    }


                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            password = it
                            passwordError = password.length < 8
                        },
                        textStyle = TextStyle(
                            color = textColor
                        ),
                        label = { Text(text = "Password", color = textColor) },
                        modifier = Modifier.fillMaxWidth(),
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        )
                    )

                    if (passwordError) {
                        Text(text = "Minimum 8 characters", color = MaterialTheme.colors.error)
                    }

                    OutlinedTextField(
                        value = accountType,
                        onValueChange = {
                            null
                        },
                        textStyle = TextStyle(
                            color = textColor
                        ),
                        label = { Text(text = "Account Type", color = textColor) },
                        modifier = Modifier.fillMaxWidth(),
                    )



                    Spacer(
                        modifier = Modifier.padding(2.dp)
                    )

                    Button(
                        onClick = {
                            dialog = true
                            enable = false
                            val data = hashMapOf(
                                "id" to id.trim(),
                                "name" to name.trim(),
                                "address" to address.trim(),
                                "phone" to phone.trim(),
                                "email" to email.trim(),
                                "notify" to notify.trim(),
                                "date" to date.trim(),
                                "accountType" to "User",
                                "verify" to verify
                            )

                            db.collection("Users").get().addOnSuccessListener { results ->
                                for (document in results) {
                                    if (email == document.get("email").toString()) {
                                        emailCheck = document.get("email").toString()
                                    }
                                }
                            }
                            if (!nameError && !emailError && !addressError && !phoneError && !passwordError) {
                                FirebaseAuth.getInstance()
                                    .createUserWithEmailAndPassword(email, password)
                                    .addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            db.collection("Users").document(id).set(data)
                                                .addOnSuccessListener {
                                                    dialog = false
                                                    Toast.makeText(
                                                        applicationContext,
                                                        "record added",
                                                        Toast.LENGTH_SHORT
                                                    )
                                                        .show()
                                                    context.startActivity(
                                                        Intent(
                                                            context,
                                                            Login::class.java
                                                        ).putExtra("dark", dark)
                                                    )
                                                    finish()
                                                }.addOnFailureListener {
                                                    Toast.makeText(
                                                        context,
                                                        it.message.toString(),
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                    dialog = false
                                                    enable = true
                                                }
                                            val user = FirebaseAuth.getInstance().currentUser
                                            if (user != null && !user.isEmailVerified) {
                                                sendVerificationEmail()
                                            }
                                        }
                                    }.addOnFailureListener {
                                        enable = true
                                        dialog = false
                                        Toast.makeText(
                                            applicationContext,
                                            it.message.toString(),
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    }


                            } else {
                                enable = true
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
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
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

                        Button(
                            onClick = {
                                startActivity(
                                    Intent(
                                        context,
                                        Login::class.java
                                    ).putExtra("dark", dark)
                                )
                                finish()
                            },
                            enabled = enable,
                            colors = ButtonDefaults.buttonColors(Color.Transparent)
                        ) {

                            Text(
                                text = "Login",
                                color = Color(0xFF800080),
                                fontSize = 17.nonScaledSp,
                                fontFamily = fontFamily,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                    }
                }
                LoadingView(modifier = Modifier, dialog)
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(50.dp),
                color = Color.White,
                strokeWidth = 3.dp
            )
            Text(text = "Please wait...", color = Color.White)
        }
    }
}
