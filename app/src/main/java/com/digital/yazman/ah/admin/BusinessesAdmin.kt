package com.digital.yazman.ah.admin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.digital.yazman.ah.activities.fontFamily
import com.digital.yazman.ah.admin.ui.theme.DigitalYazmanTheme
import com.digital.yazman.ah.nonScaledSp
import com.digital.yazman.ah.ui.theme.DigitalYazmanTheme
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class BusinessesAdmin : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var id by remember {
                mutableStateOf("ID")
            }
            val db = FirebaseFirestore.getInstance()
            val context = LocalContext.current
            var darkValue = getIntent().getBooleanExtra("dark", false)
            var dark by remember {
                mutableStateOf(darkValue)
            }
            var nameCheck = 0
            var ids = 0

            db.collection("Businesses").get().addOnSuccessListener { results ->
                for (document in results) {
                    ids = document.get("id").toString()
                        .subSequence(3, document.get("id").toString().length).toString().toInt()
                    if (nameCheck <= ids) {
                        nameCheck = ids + 1
                        id = "DYB0" + nameCheck.toString()
                    }
                }
            }
            DigitalYazmanTheme {
                // A surface container using the 'background' color from the theme
                BusinessData(dark,id, "Businesses", context)

            }
        }
    }
}


@Composable
fun BusinessData(dark: Boolean, id: String, collectionName: String, context: Context) {

    var id = id

    var shop by remember {
        mutableStateOf("")
    }
    var name by remember {
        mutableStateOf("")
    }
    var address by remember {
        mutableStateOf("")
    }
    var contact by remember {
        mutableStateOf("")
    }
    val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    var date = dateFormat.format(Date())

    val businesses = arrayOf(
        "Agriculture",
        "Aluminium",
        "Automobile",
        "Banks",
        "Books",
        "Clothing",
        "Computers",
        "Education",
        "Electric",
        "Footwear",
        "Fresh Meat",
        "Grain Market",
        "Grocery",
        "Jewelry",
        "Machine Work",
        "Marble",
        "Marriage Hall",
        "Mobile Accessories",
        "Petroleum",
        "Photo & Copy Shop",
        "Property Dealer",
        "Restaurants",
        "Steel & Iron",
        "Sweets",
        "Tehsil Office",
        "Wood Work",
    )

    var expanded by remember { mutableStateOf(false) }
    var selectedBusiness by remember { mutableStateOf(businesses[0]) }


    val data = hashMapOf(
        "id" to id.trim(),
        "shop" to shop.trim(),
        "name" to name.trim(),
        "address" to address.trim(),
        "contact" to contact.trim(),
        "business" to selectedBusiness.trim(),
        "date" to date.trim()
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFADD8E6))
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
                    value = selectedBusiness,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text(text = "Business") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier
                        .menuAnchor()
                        .background(Color.Transparent)
                        .fillMaxWidth()
                )

                ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    businesses.forEach { item ->
                        DropdownMenuItem(onClick = {
                            selectedBusiness = item
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
            value = shop,
            onValueChange = {
                shop = it
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
            value = name,
            onValueChange = {
                name = it
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
            value = address,
            onValueChange = {
                address = it
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


        Button(
            onClick = {
                val db = FirebaseFirestore.getInstance()
                val collection = db.collection(collectionName)
                if (id != "ID") {
                    if (shop != "" && name != "" && address != "" && contact != "") {
                        collection.document(id).set(data).addOnSuccessListener {
                            shop = "";
                            name = "";
                            address = "";
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