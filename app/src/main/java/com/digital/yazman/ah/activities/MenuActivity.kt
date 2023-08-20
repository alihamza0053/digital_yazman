package com.digital.yazman.ah.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.OutlinedIconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.digital.yazman.ah.admin.Admin
import com.digital.yazman.ah.R
import com.digital.yazman.ah.datastore.StoreLightDarkData
import com.digital.yazman.ah.datastore.UserInfo
import com.digital.yazman.ah.nonScaledSp
import com.digital.yazman.ah.ui.theme.DigitalYazmanTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

import kotlin.time.Duration.Companion.seconds

class menuActivity : ComponentActivity() {
    @SuppressLint("UnusedTransitionTargetStateParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE
            );
            BackHandler(enabled = true, onBack = {
                finish()
            })
            val firebaseAuth = FirebaseAuth.getInstance()
            val currentUser = firebaseAuth.currentUser
            val context = LocalContext.current
            val scope = rememberCoroutineScope()
            val dataStoreDark = StoreLightDarkData(context)
            val dataStoreUser = UserInfo(context)
            val darkValue = getIntent().getBooleanExtra("dark", false)
            var dark by remember {
                mutableStateOf(darkValue)
            }

            var lightDark by remember {
                mutableStateOf(false)
            }
            var textColor = Color(0xFF000000)
            var backgroundColor = Color(0xFFADD8E6)
            var name = dataStoreUser.getName.collectAsState(initial = "DY-Guest")
            var verify = dataStoreUser.getVerify.collectAsState(initial = "0").value
            var notify = dataStoreUser.getNotify.collectAsState(initial = "0").value
            var userId = dataStoreUser.getId.collectAsState(initial = "0").value
            var userEmail = dataStoreUser.getEmail.collectAsState(initial = "0").value


            var imgVerify by remember {
                mutableStateOf(R.drawable.user_unverified)
            }
            var login by remember {
                mutableStateOf("Login")
            }
            // Toast.makeText(context,notify.toString(),Toast.LENGTH_SHORT).show()
            var userNotify by remember {
                mutableStateOf(notify)
            }

            var badgeNumber by remember {
                mutableStateOf("0")
            }
            var badgeNumberTextColor by remember {
                mutableStateOf(Color.White)
            }
            var badgeColor by remember {
                mutableStateOf(Color.Transparent)
            }

            val db = FirebaseFirestore.getInstance()


            var notifiCheck = 0
            var ids by remember {
                mutableStateOf(0)
            }
            var nameCheck by remember {
                mutableStateOf(0)
            }

            db.collection("Notification").get().addOnSuccessListener { notifyResults ->
                for (document in notifyResults) {
                    ids = document.get("id").toString()
                        .subSequence(3, document.get("id").toString().length).toString().toInt()
                    if (nameCheck <= ids) {
                        nameCheck = ids + 1
                    }
                }
            }

            db.collection("Users").get().addOnSuccessListener { userResults ->
                for (document in userResults) {
                    if (document.get("id").toString() == userId && document.get("email")
                            .toString() == userEmail
                    ) {
                        userNotify = document.get("notify").toString()
//                        name = document.get("name").toString()

                    }
                }
            }

            if (verify == "0") {
                imgVerify = R.drawable.user_unverified
            }
            if (verify == "1") {
                imgVerify = R.drawable.user_verify
            }
            if (verify == "2") {
                imgVerify = R.drawable.admin_king
            }


            if (currentUser != null) {
                login = "Log out"
//                Toast.makeText(applicationContext, "Login Success ", Toast.LENGTH_SHORT)
                //                 .show()
            }


//            myRef.get().addOnSuccessListener {
//                var idCheck =
//                    it.children.last().key.toString()
//                        .subSequence(3, it.children.last().key.toString().length).toString()
//                        .toInt() + 1
//                id = "DYU0" + nameCheck.toString()
//            }.addOnFailureListener {
//                Toast.makeText(applicationContext, "Check Internet", Toast.LENGTH_SHORT).show()
//            }

            DigitalYazmanTheme {
                UpdateDialog(
                    context = context,
                    updateVersion = "2.0",
                    title = "Update",
                    shortDes = "Please update it",
                    dark = dark,
                    onCancelClick = {
                        finishAffinity()
                    })
                BackHandler(enabled = true, onBack = {
                    finishAffinity()
                })
//                Toast.makeText(context,userId,Toast.LENGTH_SHORT).show()

                if (dark) {
                    backgroundColor = Color(0xFF14141f)
                    textColor = Color(0xFFFFFFFF)
                } else {
                    backgroundColor = Color(0xFFADD8E6)
                    textColor = Color(0xFF000000)
                }

                val images = listOf(
                    "https://firebasestorage.googleapis.com/v0/b/digital-yazman-34f70.appspot.com/o/1.png?alt=media&token=fc9a62d2-b12d-47ed-b59a-0287cf1f1b1d",
                    "https://firebasestorage.googleapis.com/v0/b/digital-yazman-34f70.appspot.com/o/2.png?alt=media&token=d70ebc49-265c-4c13-9df6-4d01e56e3a6c",
                    "https://firebasestorage.googleapis.com/v0/b/digital-yazman-34f70.appspot.com/o/3.png?alt=media&token=2fe49b52-0ff4-4871-b18c-653c2476c324",
                    "https://firebasestorage.googleapis.com/v0/b/digital-yazman-34f70.appspot.com/o/4.png?alt=media&token=72d1d394-6aac-4314-b915-18b24005084b",

                    )
                val context = LocalContext.current
                // topbar start
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(backgroundColor)
                ) {
                    Row(
                        modifier = Modifier
                            .background(Color(0xFF800080))
                            .fillMaxWidth()
                            .padding(start = 5.dp, end = 5.dp),
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        IconToggleButton(
                            checked = dark,
                            onCheckedChange = {
                                dark = !dark
                                scope.launch {
                                    dataStoreDark.setDark(dark)
                                }
                            },

                            ) {
                            val transition = updateTransition(targetState = dark)
                            val changeImgSize by transition.animateDp(
                                transitionSpec = {
                                    keyframes {
                                        durationMillis = 200
                                        40.dp at 0 with LinearOutSlowInEasing
                                        40.dp at 40 with FastOutLinearInEasing
                                    }
                                }, label = ""
                            ) {
                                30.dp
                            }
                            Image(
                                painter = if (dark) painterResource(id = R.drawable.light_mode) else painterResource(
                                    id = R.drawable.dark_mode
                                ),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(changeImgSize)
                            )
                        }
                        Spacer(
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = name.value,
                            fontSize = 20.nonScaledSp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            color = Color(0xFFFFFFFF),
                            modifier = Modifier
                                .clickable {
                                    if (imgVerify === R.drawable.admin_king) {
                                        context.startActivity(
                                            Intent(context, Admin::class.java).putExtra(
                                                "dark",
                                                dark
                                            ).putExtra("dark", dark)
                                                .putExtra("name", name.value)
                                                .putExtra("email", userEmail)
                                                .putExtra("verify", verify)
                                                .putExtra("id", userId)
                                        )
                                        finish()
                                    }
                                    if (imgVerify === R.drawable.user_verify) {
                                        context.startActivity(
                                            Intent(
                                                context,
                                                BusinessOwnerProfile::class.java
                                            ).putExtra(
                                                "dark",
                                                dark
                                            ).putExtra("dark", dark)
                                                .putExtra("name", name.value)
                                                .putExtra("email", userEmail)
                                                .putExtra("verify", verify)
                                                .putExtra("id", userId)
                                        )
                                        finish()
                                    }
                                    if (imgVerify === R.drawable.user_unverified) {
                                        context.startActivity(
                                            Intent(context, UserProfile::class.java).putExtra(
                                                "dark",
                                                dark
                                            ).putExtra("dark", dark)
                                                .putExtra("name", name.value)
                                                .putExtra("email", userEmail)
                                                .putExtra("verify", verify)
                                                .putExtra("id", userId)
                                        )
                                        finish()
                                    }

                                }
                        )
                        Image(
                            painter = painterResource(id = imgVerify),
                            contentDescription = null,
                            modifier = Modifier
                                .width(15.dp)
                                .height(15.dp)
                        )
                        Spacer(
                            modifier = Modifier.weight(1f)
                        )
                        Text(text = login,
                            fontSize = 15.nonScaledSp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFFFFFF),
                            modifier = Modifier
                                .padding(5.dp)
                                .clickable {
                                    if (currentUser != null) {
                                        firebaseAuth.signOut()
                                    }
                                    context.startActivity(
                                        Intent(
                                            context, Login::class.java
                                        ).putExtra("dark", dark)
                                    )
                                    finish()
                                })
                    }

                    // topbar end

                    // slides images start
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                            .verticalScroll(rememberScrollState())
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(start = 20.dp, top = 10.dp, end = 20.dp)
                        ) {
                            AllTexts(
                                text = "Announcements",
                                fontSize = 20,
                                fontWeight = FontWeight.SemiBold,
                                dark = dark
                            )
                            Spacer(
                                modifier = Modifier
                                    .weight(1f)
                            )

                            badgeNumber = ((nameCheck - 1) - userNotify.toInt()).toString()
//                            Toast.makeText(
//                                context,
//                                " badgeNumber ${badgeNumber}, namecheck ${nameCheck}, userNotify ${userNotify}",
//                                Toast.LENGTH_SHORT
//                            ).show()

                            if (badgeNumber == "0") {
                                badgeNumberTextColor = Color.Transparent
                                badgeColor = Color.Transparent
                            } else {
                                badgeColor = Color.Red
                                badgeNumberTextColor = Color.White

                            }
                            if (badgeNumber > "99") {
                                badgeNumber = "99+"
                            }
                            // Notification Badge Box


                            BadgedBox(
                                badge = {
                                    Badge(
                                        modifier = Modifier.offset(y = 5.dp, x = -4.dp),
                                        containerColor = badgeColor
                                    ) {
                                        Text(
                                            badgeNumber,
                                            modifier = Modifier.semantics {
                                                contentDescription =
                                                    "$badgeNumber new notifications"
                                            },
                                            fontFamily = fontFamily,
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = 12.sp,
                                            color = badgeNumberTextColor
                                        )
                                    }
                                }) {
                                Icon(
                                    Icons.Filled.Notifications,
                                    contentDescription = "Notification",
                                    tint = textColor,
                                    modifier = Modifier
                                        .graphicsLayer {
                                            scaleX = 1.3f
                                            scaleY = 1.3f
                                        }
                                        .clickable {
                                            val intent =
                                                Intent(this@menuActivity, Notification::class.java)
                                            intent.putExtra("notification", badgeNumber)
                                            intent.putExtra("dark", dark)
                                            intent.putExtra("userId", userId)

//                                            Toast
//                                                .makeText(
//                                                    context, userId, Toast.LENGTH_SHORT
//                                                )
//                                                .show()
                                            intent.putExtra(
                                                "id",
                                                (userNotify.toInt() + badgeNumber.toInt()).toString()
                                            )
                                            startActivity(intent)
                                            finish()
                                        }
                                )
                            }
                        }
                        @OptIn(ExperimentalPagerApi::class) (
                                Card(
                                    modifier = Modifier.padding(16.dp),
                                    shape = RoundedCornerShape(16.dp),
                                ) {
                                    AutoSlidingCarousel(
                                        itemsCount = images.size,
                                        itemContent = { index ->
                                            AsyncImage(
                                                model = ImageRequest.Builder(LocalContext.current)
                                                    .data(images[index]).build(),
                                                contentDescription = null,
                                                contentScale = ContentScale.Crop,
                                                modifier = Modifier.height(200.dp)
                                            )
                                        })
                                })
                        // slides images end


                        //categories start
                        AllTexts(
                            "Categories",
                            fontSize = 20,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(start = 20.dp, top = 1.dp), dark = dark
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
                                painter = painterResource(id = R.drawable.businesses),
                                title = "Business",
                                fontSize = 10,
                                fontWeight = FontWeight.SemiBold,
                                cardModifier = Modifier,
                                columnModifier = Modifier
                                    .height(80.dp)
                                    .width(80.dp)
                                    .clickable {
                                        context.startActivity(
                                            Intent(
                                                context, Businesses::class.java
                                            ).putExtra("dark", dark)
                                        )
                                    },
                                imageModifier = Modifier
                                    .height(40.dp)
                                    .width(40.dp),
                                dark = dark

                            )

                            Spacer(modifier = Modifier.weight(1f))

                            //first row second card Emergency
                            CardColumn(
                                painter = painterResource(id = R.drawable.emergency),
                                title = "Emergency",
                                fontSize = 10,
                                fontWeight = FontWeight.SemiBold,
                                cardModifier = Modifier,
                                columnModifier = Modifier
                                    .height(80.dp)
                                    .width(80.dp)
                                    .clickable {
                                        context.startActivity(
                                            Intent(
                                                context, Emergency::class.java
                                            ).putExtra("dark", dark)
                                        )
                                    },
                                imageModifier = Modifier
                                    .height(40.dp)
                                    .width(40.dp),
                                dark = dark
                            )
                            Spacer(modifier = Modifier.weight(1f))

                            //first row third card
                            CardColumn(
                                painter = painterResource(id = R.drawable.history),
                                title = "History",
                                fontSize = 10,
                                fontWeight = FontWeight.SemiBold,
                                cardModifier = Modifier,
                                columnModifier = Modifier
                                    .height(80.dp)
                                    .width(80.dp)
                                    .clickable {
                                        context.startActivity(
                                            Intent(
                                                context, History::class.java
                                            ).putExtra("dark", dark)
                                        )
                                    },
                                imageModifier = Modifier
                                    .height(40.dp)
                                    .width(40.dp),
                                dark = dark
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
                            //second row first card
                            CardColumn(
                                painter = painterResource(id = R.drawable.local_deal),
                                title = "Local Deals",
                                fontSize = 10,
                                fontWeight = FontWeight.SemiBold,
                                cardModifier = Modifier,
                                columnModifier = Modifier
                                    .height(80.dp)
                                    .width(80.dp)
                                    .clickable {
                                        context.startActivity(
                                            Intent(
                                                context, LocalDeals::class.java
                                            ).putExtra("dark", dark)
                                        )
                                    },
                                imageModifier = Modifier
                                    .height(40.dp)
                                    .width(40.dp),
                                dark = dark
                            )

                            Spacer(modifier = Modifier.weight(1f))

                            //second row second card
                            CardColumn(
                                painter = painterResource(id = R.drawable.local_news),
                                title = "Local News",
                                fontSize = 10,
                                fontWeight = FontWeight.SemiBold,
                                cardModifier = Modifier,
                                columnModifier = Modifier
                                    .height(80.dp)
                                    .width(80.dp)
                                    .clickable {
                                        context.startActivity(
                                            Intent(
                                                context, LocalNews::class.java
                                            ).putExtra("dark", dark)
                                        )
                                    },
                                imageModifier = Modifier
                                    .height(40.dp)
                                    .width(40.dp),
                                dark = dark
                            )
                            Spacer(modifier = Modifier.weight(1f))

                            //second row third card
                            CardColumn(
                                painter = painterResource(id = R.drawable.opportunities),
                                title = "Opportunities",
                                fontSize = 9,
                                fontWeight = FontWeight.SemiBold,
                                cardModifier = Modifier,
                                columnModifier = Modifier
                                    .height(80.dp)
                                    .width(80.dp)
                                    .clickable {
                                        context.startActivity(
                                            Intent(
                                                context, Opportunities::class.java
                                            ).putExtra("dark", dark)
                                        )
                                    },
                                imageModifier = Modifier
                                    .height(40.dp)
                                    .width(40.dp),
                                dark = dark
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
                            //third row first card
                            CardColumn(
                                painter = painterResource(id = R.drawable.services),
                                title = "Services",
                                fontSize = 10,
                                fontWeight = FontWeight.SemiBold,
                                cardModifier = Modifier,
                                columnModifier = Modifier
                                    .height(80.dp)
                                    .width(80.dp)
                                    .clickable {
                                        context.startActivity(
                                            Intent(
                                                context, Services::class.java
                                            ).putExtra("dark", dark)
                                        )
                                    },
                                imageModifier = Modifier
                                    .height(40.dp)
                                    .width(40.dp),
                                dark = dark
                            )
                            Spacer(modifier = Modifier.weight(1f))

                            //third row second card
                            CardColumn(
                                painter = painterResource(id = R.drawable.transport),
                                title = "Transport",
                                fontSize = 10,
                                fontWeight = FontWeight.SemiBold,
                                cardModifier = Modifier,
                                columnModifier = Modifier
                                    .height(80.dp)
                                    .width(80.dp)
                                    .clickable {
                                        context.startActivity(
                                            Intent(
                                                context, Transport::class.java
                                            ).putExtra("dark", dark)
                                        )
                                    },
                                imageModifier = Modifier
                                    .height(40.dp)
                                    .width(40.dp),
                                dark = dark
                            )
                            Spacer(modifier = Modifier.weight(1f))

                            //third row third card
                            CardColumn(
                                painter = painterResource(id = R.drawable.gallery),
                                title = "Event Gallery",
                                fontSize = 10,
                                fontWeight = FontWeight.SemiBold,
                                cardModifier = Modifier,
                                columnModifier = Modifier
                                    .height(80.dp)
                                    .width(80.dp)
                                    .clickable {
                                        context.startActivity(
                                            Intent(
                                                context, EventGallery::class.java
                                            ).putExtra("dark", dark)
                                        )
                                    },
                                imageModifier = Modifier
                                    .height(40.dp)
                                    .width(40.dp),
                                dark = dark
                            )
                        }


                        // fourth row with 3 cards
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp)
                        ) {
                            Spacer(modifier = Modifier.weight(1f))
                            //fourth row first card
                            CardColumn(
                                painter = painterResource(id = R.drawable.faq),
                                title = "FAQs",
                                fontSize = 10,
                                fontWeight = FontWeight.SemiBold,
                                cardModifier = Modifier,
                                columnModifier = Modifier
                                    .height(80.dp)
                                    .width(80.dp)
                                    .clickable {
                                        context.startActivity(
                                            Intent(
                                                context, FAQs::class.java
                                            ).putExtra("dark", dark)
                                        )
                                    },
                                imageModifier = Modifier
                                    .height(40.dp)
                                    .width(40.dp),
                                dark = dark
                            )
                            Spacer(modifier = Modifier.weight(1f))

                            //fourth row second card
                            CardColumn(
                                painter = painterResource(id = R.drawable.supprot),
                                title = "Contact",
                                fontSize = 10,
                                fontWeight = FontWeight.SemiBold,
                                cardModifier = Modifier,
                                columnModifier = Modifier
                                    .height(80.dp)
                                    .width(80.dp)
                                    .clickable {
                                        context.startActivity(
                                            Intent(
                                                context, Contact::class.java
                                            ).putExtra("dark", dark)
                                        )
                                    },
                                imageModifier = Modifier
                                    .height(40.dp)
                                    .width(40.dp),
                                dark = dark
                            )
                            Spacer(modifier = Modifier.weight(1f))

                            //fourth row third card
//                            CardColumn(
//                                painter = painterResource(id = R.drawable.supprot),
//                                title = "Test",
//                                fontSize = 10,
//                                fontWeight = FontWeight.SemiBold,
//                                cardModifier = Modifier,
//                                columnModifier = Modifier
//                                    .height(80.dp)
//                                    .width(80.dp)
//                                    .clickable {
//                                        context.startActivity(
//                                            Intent(
//                                                context, Test::class.java
//                                            )
//                                        )
//                                    },
//                                imageModifier = Modifier
//                                    .height(40.dp)
//                                    .width(40.dp)
//                            )
                        }
                        //categories end

                    }
                }

            }
        }
    }
}


@Composable
fun UpdateDialog(
    context: Context,
    updateVersion: String,
    title: String,
    shortDes: String,
    dark: Boolean,
    onCancelClick: () -> Unit
) {
    val appVersion = context.packageManager.getPackageInfo(context.packageName, 0).versionName
    Toast.makeText(context, appVersion.toString(), Toast.LENGTH_SHORT).show()
    Toast.makeText(context, updateVersion.toString(), Toast.LENGTH_SHORT).show()
    var dialogBg = Color(0xFFADD8E6)
    var buttonColor = Color(0xFFFFFFFF)
    if (dark) {
        dialogBg = Color(0xFF14141f)
        buttonColor = Color(0xFF282834)
    }

    if (appVersion < updateVersion) {
        AlertDialog(modifier = Modifier.padding(20.dp),
            backgroundColor = dialogBg,
            properties = DialogProperties(usePlatformDefaultWidth = false),
            onDismissRequest = { false },
            title = {
                Column {
                    AllTexts(
                        text = title,
                        fontSize = 17,
                        modifier = Modifier.align(Alignment.Start),
                        fontWeight = FontWeight.SemiBold,
                        dark = dark
                    )
                }
            },
            text = {
                Column {
                    AllTexts(
                        text = shortDes,
                        fontSize = 13,
                        modifier = Modifier.align(Alignment.Start),
                        fontWeight = FontWeight.Normal,
                        dark = dark
                    )
                    AllTexts(
                        text = "You can use app after update.",
                        fontSize = 13,
                        modifier = Modifier.align(Alignment.Start),
                        fontWeight = FontWeight.Normal,
                        dark = dark
                    )
                    AllTexts(
                        text = "Thanks❤️",
                        fontSize = 13,
                        modifier = Modifier.align(Alignment.Start),
                        fontWeight = FontWeight.Normal,
                        dark = dark
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {

                    },modifier = Modifier.padding(bottom = 10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonColor
                    )
                ) {
                    AllTexts(text = "Update", fontSize = 12, fontWeight = FontWeight.Normal, dark = dark)
                }
            },
            dismissButton = {
                Button(onClick = {
                    onCancelClick()
                },modifier = Modifier.padding(bottom = 10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonColor
                    )) {
                    AllTexts(text = "Exit", fontSize = 12, fontWeight = FontWeight.Normal, dark = dark)

                }
            }
        )
    }
}

// image slider start
@Composable
fun IndicatorDot(
    modifier: Modifier = Modifier, size: Dp, color: Color
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(color)
    )
}


@Composable
fun DotsIndicator(
    modifier: Modifier = Modifier,
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color = Color(0xFFADD8E6),
    unSelectedColor: Color = Color.Gray,
    dotSize: Dp
) {
    LazyRow(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()
    ) {
        items(totalDots) { index ->
            IndicatorDot(
                color = if (index == selectedIndex) selectedColor else unSelectedColor,
                size = dotSize
            )

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun AutoSlidingCarousel(
    modifier: Modifier = Modifier,
    //   autoSlideDuration: Long = AUTO_SLIDE_DURATION,
    pagerState: PagerState = remember { PagerState() },
    itemsCount: Int,
    itemContent: @Composable (index: Int) -> Unit,
) {
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

    LaunchedEffect(pagerState.currentPage) {
        delay(5.seconds)
        pagerState.animateScrollToPage((pagerState.currentPage + 1) % itemsCount)
    }

    Box(
        modifier = modifier.fillMaxWidth(),
    ) {
        HorizontalPager(count = itemsCount, state = pagerState) { page ->
            itemContent(page)
        }

        // you can remove the surface in case you don't want
        // the transparant bacground
        Surface(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .align(Alignment.BottomCenter),
            shape = CircleShape,
            color = Color.Black.copy(alpha = 0.5f)
        ) {
            DotsIndicator(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                totalDots = itemsCount,
                selectedIndex = if (isDragged) pagerState.currentPage else pagerState.targetPage,
                dotSize = 8.dp
            )
        }
    }
}