package com.digital.yazman.ah.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import com.digital.yazman.ah.R
import com.digital.yazman.ah.datastore.StoreLightDarkData
import com.digital.yazman.ah.datastore.UserInfo
import com.digital.yazman.ah.nonScaledSp
import com.digital.yazman.ah.ui.theme.DigitalYazmanTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class Login : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val firebaseAuth = FirebaseAuth.getInstance()
            val currentUser = firebaseAuth.currentUser
            val context = LocalContext.current
            var darkValue = getIntent().getBooleanExtra("dark", false)
            var dark by remember {
                mutableStateOf(darkValue)
            }
            BackHandler(enabled = true, onBack = {
                finish()
            })
            var backgroundColor = Color(0xFFADD8E6)
            var textColor = Color(0xFF000000)
            var email by remember {
                mutableStateOf("")
            }
            var emailError by remember {
                mutableStateOf(false)
            }
            var enable by remember {
                mutableStateOf(true)
            }

            var dialog by remember {
                mutableStateOf(false)
            }
            var password by remember {
                mutableStateOf("")
            }
            var passwordError by remember {
                mutableStateOf(false)
            }
            var name by remember {
                mutableStateOf("")
            }
            val scope = rememberCoroutineScope()
            val dataStoreUser = UserInfo(context)
            val db = FirebaseFirestore.getInstance()

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
                ) {

                    //business start
                    AllTexts(
                        text = "Welcome\nBack",
                        fontSize = 30,
                        fontWeight = FontWeight.Light,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(top = 15.dp, bottom = 50.dp),
                        dark = dark
                    )
//                    Image(
//                        painter = painterResource(id = R.drawable.new_logo),
//                        contentDescription = "logo",
//                        modifier = Modifier.size(150.dp)
//                    )

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
                            color = textColor,
                        )
                    )
                    if (emailError) {
                        Text(
                            text = "Email must contain @gmail.com",
                            color = MaterialTheme.colorScheme.error
                        )
                    }

                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            password = it
                            passwordError = password.length < 8
                        },
                        textStyle = TextStyle(color = textColor),
                        label = { Text(text = "Password", color = textColor) },
                        modifier = Modifier.fillMaxWidth(),
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        )
                    )
                    if (passwordError) {
                        Text(text = "Minimum 8 characters", color = MaterialTheme.colorScheme.error)
                    }

                    Spacer(
                        modifier = Modifier.padding(2.dp)
                    )

                    Button(
                        onClick = {

                            if (!emailError && !passwordError && email != "" && password != "") {
                                dialog = true
                                enable = false
                                //signIn with email and password
                                FirebaseAuth.getInstance()
                                    .signInWithEmailAndPassword(email, password)
                                    .addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            db.collection("Users").get()
                                                .addOnSuccessListener { result ->
                                                    for (document in result) {
                                                        if (document.get("email") == email) {
                                                            scope.launch {
                                                                dataStoreUser.setId(
                                                                    document.get("id").toString()
                                                                )
                                                                dataStoreUser.setName(
                                                                    document.get("name").toString()
                                                                )
                                                                name =
                                                                    document.get("name").toString()
                                                                Toast.makeText(
                                                                    context,
                                                                    "Welcome " + name,
                                                                    Toast.LENGTH_SHORT
                                                                ).show()
                                                                dataStoreUser.setEmail(
                                                                    document.get("email").toString()
                                                                )
                                                                dataStoreUser.setAddress(
                                                                    document.get("address")
                                                                        .toString()
                                                                )
                                                                dataStoreUser.setPhone(
                                                                    document.get("phone").toString()
                                                                )
                                                                dataStoreUser.setNotify(
                                                                    document.get("notify")
                                                                        .toString()
                                                                )
                                                                dataStoreUser.setVerify(
                                                                    document.get("verify")
                                                                        .toString()
                                                                )
                                                            }
                                                            context.startActivity(
                                                                Intent(
                                                                    context,
                                                                    menuActivity::class.java
                                                                ).putExtra(
                                                                    "dark",
                                                                    dark
                                                                )
                                                            )
                                                            finish()
                                                        }
                                                    }
                                                }.addOnFailureListener {
                                                    Toast.makeText(
                                                        context,
                                                        it.message.toString(),
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                    enable = true
                                                    dialog = false
                                                }
                                            val user =
                                                FirebaseAuth.getInstance().currentUser
//                                            Toast.makeText(
//                                                context,
//                                                user?.email.toString(),
//                                                Toast.LENGTH_SHORT
//                                            ).show()

                                            // Handle successful login
                                        } else {
                                            enable = true
                                            dialog = false
                                            Toast.makeText(
                                                context,
                                                task.exception?.message,
                                                Toast.LENGTH_SHORT
                                            )
                                                .show()
                                            // Handle login failure
                                        }

                                    }

                            } else {
                                Toast.makeText(
                                    context,
                                    "Check Fields!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

//                            //Toast.makeText(applicationContext,"hamza",Toast.LENGTH_SHORT).show();
//                            val database = Firebase.database
//                            val myRef = database.getReference("Signup")
//                            myRef.get().addOnSuccessListener {
//                                if(it.exists()){
//                                    it.children.forEach {
//                                        if (it.child("email").value == email && it.child("password").value == password) {
//                                            Toast.makeText(applicationContext, "Login Success ",Toast.LENGTH_SHORT).show()
//                                        }
//
//                                    }
//                                }
//                            }
                        },
                        enabled = enable,
                        shape = RoundedCornerShape(3.dp),
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(Color(0xFF800080))
                    )
                    {
                        Text(
                            text = "Login",
                            fontSize = 20.nonScaledSp,
                            color = Color(0xFFFFFFFF),
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.SemiBold,
                        )
                    }






                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AllTexts(
                            text = "Do not have account!",
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
                                        Signup::class.java
                                    ).putExtra("dark", dark)
                                )
                                finish()
                            },
                            enabled = enable,
                            colors = ButtonDefaults.buttonColors(Color.Transparent)
                        ) {

                            Text(
                                text = "Sign Up",
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


//
//private fun signInWithEmail(context: Context, email: String, password: String, dark: Boolean) {
//
//}
//
//private fun sendVerificationEmail() {
//    val user = FirebaseAuth.getInstance().currentUser
//    user?.sendEmailVerification()
//        ?.addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                // Email sent
//            } else {
//                // Email not sent
//            }
//        }
//}


