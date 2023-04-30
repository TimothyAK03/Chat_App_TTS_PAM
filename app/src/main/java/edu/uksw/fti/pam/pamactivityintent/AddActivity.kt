package edu.uksw.fti.pam.pamactivityintent

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import edu.uksw.fti.pam.pamactivityintent.ui.theme.PAMActivityIntentTheme
import java.lang.reflect.Modifier


class AddActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PAMActivityIntentTheme() {
                // A surface container using the 'background' color from the theme
                Surface(
                ) {
                    AddScreen(onClickAction = ::addData)
                }
            }
        }
    }

    private fun addData(url: String, date: String) {
        val fFirestore = Firebase.firestore

        // Add a new document with a generated id.
        val data = hashMapOf(
            "date" to date,
            "url" to url
        )

        fFirestore.collection("photo")
            .add(data)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(applicationContext, "Added data successfuly", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(applicationContext, "Failed add data", Toast.LENGTH_SHORT).show()
            }
    }
}

@Composable
fun AddScreen( onClickAction: (String, String) -> Unit ) {
    val lContext = LocalContext.current
    var url by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }

    Column() {

        TextField(
            value = date,
            onValueChange = { date = it },
            label = { Text(text = "Date") },
        )
        TextField(
            value = url,
            onValueChange = { url = it },
            label = { Text(text = "Link Photo") },
        )
    }

    Button(
        modifier = androidx.compose.ui.Modifier
            .padding(top = 80.dp),
        onClick = {
            onClickAction(url, date)
        }
    ) {
        Text(text = "SUBMIT")
    }
}