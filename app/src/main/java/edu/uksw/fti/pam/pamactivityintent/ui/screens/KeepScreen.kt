package edu.uksw.fti.pam.pamactivityintent.ui.screens

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.errorprone.annotations.Keep
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import edu.uksw.fti.pam.pamactivityintent.AddActivity
import edu.uksw.fti.pam.pamactivityintent.R
import edu.uksw.fti.pam.pamactivityintent.models.KeepModel
import edu.uksw.fti.pam.pamactivityintent.models.KeepViewModel
import edu.uksw.fti.pam.pamactivityintent.ui.ContactItems
import edu.uksw.fti.pam.pamactivityintent.ui.theme.PAMActivityIntentTheme
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*
import kotlin.reflect.KFunction2

@Composable
fun ButtonAdd() {
    val context = LocalContext.current
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 60.dp, end = 50.dp)
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(Color(0xff36a8eb)),
        onClick = {
            context.startActivity(
                Intent(context, AddActivity::class.java)
            )
        }
    ) {
        Text(text = stringResource(R.string.Add))
    }
}

@Composable
fun Keep(contt: KeepModel) {
    AsyncImage(
        model = contt.imageURL,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(top = 10.dp, start = 10.dp)
            .size(120.dp)
    )
}

@Composable
fun KeepScreen() {
    val vm = KeepViewModel()
    LaunchedEffect(Unit) {
        vm.getKeepList()
    }

    val cont = remember { vm.keepModelList }
    val context = LocalContext.current

    Column {
        ButtonAdd()
        LazyVerticalGrid(columns = GridCells.Fixed(3), modifier = Modifier.padding(top = 20.dp)) {
            items(
                items = cont,
                itemContent = {
                    if (it != null) {
                        Keep(contt = it)
                    }
                }
            )
        }
    }
}
