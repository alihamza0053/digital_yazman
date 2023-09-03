package com.digital.yazman.ah

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.digital.yazman.ah.activities.ui.theme.DigitalYazmanTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import java.util.*

import java.util.*

class ImgUploadTest : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DigitalYazmanTheme {
                // A surface container using the 'background' color from the theme
                ImageUploadScreen()
                AsyncImage(
                    model = "https://firebasestorage.googleapis.com/v0/b/digital-yazman-34f70.appspot.com/o/1.png?alt=media&token=fc9a62d2-b12d-47ed-b59a-0287cf1f1b1d",
                    contentDescription = "Translated description of what the image contains"
                )
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ImageUploadScreen() {
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var uploadSuccess by remember { mutableStateOf(false) }
    var downloadUrl by remember { mutableStateOf<String?>(null) }
    var fileName by remember { mutableStateOf(TextFieldValue()) }

    val context = LocalContext.current
    val storage = Firebase.storage
    val currentUser = FirebaseAuth.getInstance().currentUser

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        imageUri = uri
    }

    // Function to upload image to Firebase Storage
    fun uploadImageToFirebase(storage: FirebaseStorage, userId: String, imageUri: Uri) {
        if (userId.isNotEmpty()) {
            val imageName = imageUri.lastPathSegment ?: UUID.randomUUID().toString()
            val storageRef = storage.reference.child("images/$userId/$imageName")

            storageRef.putFile(imageUri)
                .addOnSuccessListener { _ ->
                    uploadSuccess = true
                    storageRef.downloadUrl.addOnSuccessListener { uri ->
                        downloadUrl = uri.toString()
                    }
                }
                .addOnFailureListener { exception ->
                    uploadSuccess = false
                    downloadUrl = null
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
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Display selected image or default image
        val selectedImagePainter: Painter = rememberImagePainter(data = imageUri?.toString() ?: R.drawable.logo)

        Image(
            painter = selectedImagePainter,
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .fillMaxWidth()
        )

        // Input field for file name
        TextField(
            value = fileName,
            onValueChange = { fileName = it },
            placeholder = { Text("Enter file name") },
            modifier = Modifier.fillMaxWidth()
        )

        // Upload button
        Button(
            onClick = {
                uploadImageToFirebase(storage, currentUser?.uid ?: "", imageUri!!)
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "Upload Image")
        }

        // Show download link if upload is successful
        if (uploadSuccess && !downloadUrl.isNullOrEmpty()) {
            Text("Download Link: $downloadUrl")
        }

        // Choose image button
        Button(
            onClick = {
                launcher.launch("image/*")
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Choose Image")
        }
    }


}

