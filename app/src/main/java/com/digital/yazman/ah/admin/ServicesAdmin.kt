package com.digital.yazman.ah.admin

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.digital.yazman.ah.R
import com.digital.yazman.ah.activities.fontFamily
import com.digital.yazman.ah.nonScaledSp
import com.digital.yazman.ah.ui.theme.DigitalYazmanTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

class ServicesAdmin : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var id by remember {
                mutableStateOf("ID")
            }
            val context = LocalContext.current
            var darkValue = getIntent().getBooleanExtra("dark", false)
            var dark by remember {
                mutableStateOf(darkValue)
            }
            val db = FirebaseFirestore.getInstance()
            var nameCheck = 0
            var ids = 0

            db.collection("Services").get().addOnSuccessListener { results ->
                for (document in results) {
                    ids = document.get("id").toString()
                        .subSequence(3, document.get("id").toString().length).toString().toInt()
                    if (nameCheck <= ids) {
                        nameCheck = ids + 1
                        id = "DYS0" + nameCheck.toString()
                    }
                }
            }
            DigitalYazmanTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    ServicesData(dark, id, "Services", context)

                }
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ServicesData(dark: Boolean, id: String, collectionName: String, context: Context) {

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        imageUri = uri
    }
    var uploadSuccess by remember { mutableStateOf(false) }
    val storage = Firebase.storage
    val currentUser = FirebaseAuth.getInstance().currentUser

    var id = id

    var name by remember {
        mutableStateOf("")
    }
    var expert by remember {
        mutableStateOf("")
    }
    var location by remember {
        mutableStateOf("")
    }
    var contact by remember {
        mutableStateOf("")
    }
    var logoUrl by remember {
        mutableStateOf("")
    }
    val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    var date = dateFormat.format(Date())

    val businesses = arrayOf(
        "TV Cable",
        "Car Mechanics",
        "Catering",
        "Computer Repair",
        "Electricians",
        "Handyman",
        "Home Cleaners",
        "Home Security",
        "HVACR",
        "Internet",
        "Movers",
        "Painters",
        "Pet Service",
        "Photograph",
        "Plumbers",
    )

    var expanded by remember { mutableStateOf(false) }
    var selectedServices by remember { mutableStateOf(businesses[0]) }


    val data = hashMapOf(
        "id" to id.trim(),
        "name" to name.trim(),
        "expert" to expert.trim(),
        "location" to location.trim(),
        "contact" to contact.trim(),
        "services" to selectedServices.trim(),
        "logoUrl" to logoUrl.trim(),
        "date" to date.trim()
    )

    // upload image to firebase storage
    fun uploadImageToFirebase(storage: FirebaseStorage, userId: String, imageUri: Uri) {
        if (userId.isNotEmpty()) {
            val imageName = imageUri.lastPathSegment ?: UUID.randomUUID().toString()
            val storageRef = storage.reference.child("images/$userId/$imageName")

            storageRef.putFile(imageUri)
                .addOnSuccessListener { _ ->
                    uploadSuccess = true
                    storageRef.downloadUrl.addOnSuccessListener { uri ->
                        logoUrl = uri.toString()
                    }
                }
                .addOnFailureListener { exception ->
                    uploadSuccess = false
                    logoUrl = ""
                    // Handle the error
                    Toast.makeText(context, "Upload failed: ${exception.message}", Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(context, "Please select an image", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFADD8E6))
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val selectedImagePainter: Painter = rememberImagePainter(data = imageUri?.toString() ?: R.drawable.logo)
        Image(
            painter = selectedImagePainter,
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .fillMaxWidth()
                .combinedClickable(
                    onClick = {
                        logoUrl = ""
                        launcher.launch("image/*")
                    },
                    onLongClick = {
                        uploadImageToFirebase(storage, currentUser?.uid ?: "", imageUri!!)
                    },
                )
        )
        Text(
            text = collectionName,
            color = Color(0xFF000000),
            fontSize = 20.nonScaledSp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 20.dp, top = 10.dp)
        )
        OutlinedTextField(
            value = id,
            onValueChange = {
                id = it
            },
            label = { Text(text = "ID") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
            ),
            textStyle = TextStyle.Default.copy(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
            )
        )


        // dropdown menu
        Box(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
        ) {
            ExposedDropdownMenuBox(
                expanded = expanded, onExpandedChange = {
                    expanded = !expanded
                }, modifier = Modifier
                    .background(Color.Transparent)
                    .fillMaxWidth()
            ) {
                TextField(
                    value = selectedServices,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text(text = "Services") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier
                        .menuAnchor()
                        .background(Color.Transparent)
                        .fillMaxWidth()
                )

                ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    businesses.forEach { item ->
                        DropdownMenuItem(onClick = {
                            selectedServices = item
                            expanded = false
                            Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                        }) {
                            Text(
                                text = item,
                                modifier = Modifier
                                    .background(Color.Transparent)
                                    .fillMaxWidth()
                            )

                        }
                    }
                }
            }
        }

        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
            },
            label = { Text(text = "Shop Name") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
            ),
            textStyle = TextStyle.Default.copy(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
            )
        )

        OutlinedTextField(
            value = expert,
            onValueChange = {
                expert = it
            },
            label = { Text(text = "Owner Name") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
            ),
            textStyle = TextStyle.Default.copy(
                fontFamily = fontFamily,
                fontWeight = FontWeight.SemiBold,
            )
        )

        OutlinedTextField(
            value = location,
            onValueChange = {
                location = it
            },
            label = { Text(text = "Shop Address") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
            ),
            textStyle = TextStyle.Default.copy(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
            )
        )

        OutlinedTextField(
            value = contact,
            onValueChange = {
                contact = it
            },
            label = { Text(text = "Owner Contact Number") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
            ),
            textStyle = TextStyle.Default.copy(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
            )
        )
        OutlinedTextField(
            value = logoUrl,
            onValueChange = {
                logoUrl = it
            },
            label = { Text(text = "Logo Url") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
            ),
            textStyle = TextStyle.Default.copy(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
            )
        )


        Button(
            onClick = {
                val db = FirebaseFirestore.getInstance()
                val collection = db.collection(collectionName)
                if (id != "ID") {
                    if (name != "" && expert != "" && location != "" && contact != "") {
                        collection.document(id).set(data).addOnSuccessListener {
                            name = "";
                            expert = "";
                            location = "";
                            contact = "";
                            id = id
                        }
                        context.startActivity(Intent(context, Admin::class.java).putExtra("dark", dark))
                    }
                }
            },
            shape = RoundedCornerShape(3.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color(0xFF800080))
        ) {
            Text(
                text = "Upload", fontSize = 20.nonScaledSp, color = Color(0xFFFFFFFF),
                fontFamily = fontFamily,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }

}

