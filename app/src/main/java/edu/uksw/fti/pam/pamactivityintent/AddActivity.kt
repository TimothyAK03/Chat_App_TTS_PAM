package edu.uksw.fti.pam.pamactivityintent

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
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
import java.io.File
import java.lang.reflect.Modifier


class AddActivity : ComponentActivity() {
//    @RequiresApi(Build.VERSION_CODES.O)
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            PAMActivityIntentTheme() {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = androidx.compose.ui.Modifier.fillMaxSize()
//                ) {
//                    Add()
//                }
//            }
//        }
//    }

    private lateinit var photoUri: Uri

    @RequiresApi(Build.VERSION_CODES.O)
    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            // Do something with the selected image URI
            setContent {
                photoUri = it
                if ( photoUri!= null) {
                    Image(
                        painter = rememberImagePainter(data = it),
                        contentDescription = null,
                    )
                    Button(
                        onClick = { uploadImageToStorage()},
                    ) {
                        Text("Upload Image")
                    }
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun uploadImageToStorage() {
        val fstorage = Firebase.storage
        val storageRef =fstorage.reference
        val db = Firebase.firestore
        val docRef = db.collection("images")

        val imageFile = File(photoUri.path!!) // convert Uri to File
        val imageRef = storageRef.child(imageFile.name)

        val uploadTask = imageRef.putFile(photoUri)

        uploadTask.addOnSuccessListener {
            // Image upload successful
            imageRef.downloadUrl.addOnSuccessListener { downloadUri ->
                val imageURL = downloadUri.toString()
                val imageURLHash = hashMapOf(
                    "imageURL" to imageURL
                )
                docRef.document()
                    .set(imageURLHash)
                    .addOnSuccessListener {
                        Log.i("kilo", "URL Image uploaded: $imageURL")
                    }
                    .addOnFailureListener { exception ->
                        Log.e("kilo", "Error writing document", exception)
                    }
                Log.i("kilo", "Image uploaded: $imageURL")

            }.addOnFailureListener { exception ->
                Log.e("kilo", "Error getting image URL", exception)
            }
        }.addOnFailureListener { exception ->
            // Image upload failed
            Log.e("kilo", "Image upload failed: ${exception.message}", exception)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PAMActivityIntentTheme() {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = androidx.compose.ui.Modifier.fillMaxSize()
                ) {
                    Button(onClick = { pickImage.launch("image/*") }) {
                        Text("Select Image")
                    }
                }
            }
        }
    }
}

@Composable
fun Add(onSubmitActionEvent: (photoUri: Uri) -> Unit) {

}