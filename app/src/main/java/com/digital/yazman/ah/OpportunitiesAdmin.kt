package com.digital.yazman.ah

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.digital.yazman.ah.ui.theme.DigitalYazmanTheme
import com.google.firebase.firestore.FirebaseFirestore

class OpportunitiesAdmin : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var id by remember {
                mutableStateOf("ID")
            }
            val db = FirebaseFirestore.getInstance()

            var nameCheck = 0
            db.collection("Opportunities").get().addOnSuccessListener { results ->
                for (document in results) {
                    nameCheck = document.get("id").toString()
                        .subSequence(3, document.get("id").toString().length).toString().toInt() + 1
                    id = "DYO0" + nameCheck.toString()
                }
            }
            DigitalYazmanTheme {
                // A surface container using the 'background' color from the theme
                DataToUpload(id = id, "Opportunities")
            }
        }
    }
}
