package edu.uksw.fti.pam.pamactivityintent.ui.screens

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.uksw.fti.pam.pamactivityintent.HomeActivity
import edu.uksw.fti.pam.pamactivityintent.R
import edu.uksw.fti.pam.pamactivityintent.contracts.SignUpContract
import edu.uksw.fti.pam.pamactivityintent.ui.theme.PAMActivityIntentTheme
import edu.uksw.fti.pam.pamactivityintent.DataStore.FirstName
import edu.uksw.fti.pam.pamactivityintent.DataStore.LastName
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.uksw.fti.pam.pamactivityintent.CamActivity


import edu.uksw.fti.pam.pamactivityintent.MainActivity
import edu.uksw.fti.pam.pamactivityintent.models.*
import edu.uksw.fti.pam.pamactivityintent.ui.BottomNavItems
import edu.uksw.fti.pam.pamactivityintent.ui.ContactItems
import edu.uksw.fti.pam.pamactivityintent.ui.ProfileItems

import edu.uksw.fti.pam.pamactivityintent.ui.theme.PAMActivityIntentTheme

@Composable
fun ProfileScreen(vm: UserProfileViewModel, navController: NavController) {
    val context = LocalContext.current
    val dataStore = FirstName(context)
    val storeData = LastName(context)
    val savedFirst = dataStore.getFirst.collectAsState(initial = "")
    val savedLast = storeData.getLast.collectAsState(initial = "")
    val user = Firebase.auth.currentUser
    var selectImages by remember { mutableStateOf(listOf<Uri>()) }
    val lContext = LocalContext.current
    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) {
            selectImages = it
        }
    LaunchedEffect(
        Unit,
        block = {
            vm.getLoggedInUserProfile()
        }
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .width(400.dp)
            .clip(shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp))
            .padding(top = 40.dp, start = 16.dp, end = 16.dp, bottom = 0.dp)
            .clip(shape = RoundedCornerShape(18.dp))
            .height(160.dp)
            .background(Color(0xff36a8eb))
    ) {
        Box(
            modifier = Modifier
                .padding(start = 25.dp, top = 25.dp, bottom = 30.dp)

        ) {
            AsyncImage( // <--- foto kudu nganggo async image
                model = vm.userProfile.img,
                contentScale = ContentScale.FillWidth,
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 10.dp, start = 15.dp)
                    .clip(CircleShape)
                    .size(100.dp)
            )

        }
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(top = 120.dp),
//                horizontalArrangement = Arrangement.Center
//            ) {
        Text(
            modifier = Modifier
                .padding(start = 160.dp, top = 40.dp),
            text = stringResource(R.string.hello),
            fontSize = 16.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )
        vm.userProfile.firstName?.let {
            Text(
                modifier = Modifier
                    .padding(start = 215.dp, top = 40.dp),
                text = it,
                fontSize = 16.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        Text(
            modifier = Modifier
                .padding(start = 255.dp, top = 40.dp),
            text = " "
        )
        vm.userProfile.lastName?.let {
            Text(
                modifier = Modifier
                    .padding(start = 265.dp, top = 40.dp),
                text = it,
                fontSize = 16.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
        }


        Row(
            modifier = Modifier
                .padding(top = 80.dp, end = 10.dp)
                .width(400.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                modifier = Modifier
                    .width(164.dp)
                    .height(44.dp)
                    .padding(top = 8.dp, start = 0.dp, end = 25.dp),
                colors = ButtonDefaults.buttonColors(Color.White),
                onClick = { navController.navigate(ProfileItems.UpdateScreen.route) },
                shape = RoundedCornerShape(8.dp)
            )
            {
                Text(
                    text = stringResource(R.string.editmyprofile),
                    fontSize = 12.sp,
                    color = Color(0xff36a8eb),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

    LazyColumn(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(start = 36.dp, top = 220.dp, end = 16.dp)
    ) {
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()
                    .clickable { navController.navigate(ContactItems.FavGroup.route) }
            ) {
                Icon(
                    painter = painterResource(R.drawable.favourites),
                    contentDescription = stringResource(id = R.string.fav),
                    tint = Color.Black,
                    modifier = Modifier
                        .size(24.dp)
                        .padding(top = 4.dp)
                )
                Text(
                    text = stringResource(R.string.fav),
                    modifier = Modifier.padding(start = 30.dp),
                    fontSize = 17.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
            }
        }
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()
                    .clickable { navController.navigate(ProfileItems.Keep.route) }
            ) {
                Icon(
                    painter = painterResource(R.drawable.downloads),
                    contentDescription = stringResource(id = R.string.keep),
                    tint = Color.Black,
                    modifier = Modifier
                        .size(24.dp)
                        .padding(top = 4.dp)
                )
                Text(
                    text = stringResource(R.string.keep),
                    modifier = Modifier.padding(start = 30.dp),
                    fontSize = 17.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
            }
        }
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()
                    .clickable {
                        lContext.startActivity(
                            Intent(lContext, CamActivity::class.java)
                        )
                    }
            ) {
                Icon(
                    painter = painterResource(R.drawable.camera),
                    contentDescription = stringResource(id = R.string.camera),
                    tint = Color.Black,
                    modifier = Modifier
                        .size(24.dp)
                        .padding(top = 4.dp)
                )
                Text(
                    text = stringResource(R.string.cam),
                    modifier = Modifier.padding(start = 30.dp),
                    fontSize = 17.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
            }
        }
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()
                    .clickable {  navController.navigate(ProfileItems.AboutUs.route) }
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_apps_24),
                    contentDescription = stringResource(id = R.string.about),
                    tint = Color.Black,
                    modifier = Modifier
                        .size(24.dp)
                        .padding(top = 4.dp)
                )
                Text(
                    text = stringResource(R.string.about),
                    modifier = Modifier.padding(start = 30.dp),
                    fontSize = 17.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
            }
        }
        item {
            Divider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(20.dp)))}
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()
                    .clickable {  val user = Firebase.auth.currentUser!!
                        user.delete()
                        val fFirestore = Firebase.firestore
                        fFirestore
                            .collection("users")
                            .document(user.uid)
                            .delete()
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful)
                                {
                                    context.startActivity(Intent(context, MainActivity::class.java))
                                }
                            }
                        }
            ) {
                Icon(
                    painter = painterResource(R.drawable.accounts),
                    contentDescription = stringResource(id = R.string.accounts),
                    tint = Color.Black,
                    modifier = Modifier
                        .size(24.dp)
                        .padding(top = 4.dp)
                )
                Text(
                    text = stringResource(R.string.del),
                    modifier = Modifier.padding(start = 30.dp),
                    fontSize = 17.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
            }
        }
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()
                    .clickable {
                        FirebaseAuth
                        .getInstance()
                        .signOut()
                        context.startActivity(Intent(context, MainActivity::class.java))
                            }
            ) {
                Icon(
                    painter = painterResource(R.drawable.logout),
                    contentDescription = stringResource(id = R.string.logout),
                    tint = Color.Red,
                    modifier = Modifier
                        .size(24.dp)
                        .padding(top = 4.dp)
                )
                Text(
                    text = stringResource(R.string.logout),
                    modifier = Modifier.padding(start = 30.dp),
                    fontSize = 17.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal,
                    color = Color.Red
                )
            }
        }



    }
}

@Composable
fun CameraButton( navController: NavController) {

    val lContext = LocalContext.current
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 230.dp, start = 50.dp, end = 50.dp)
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(Color(0xff36a8eb)),
        onClick = {
            lContext.startActivity(
                Intent(lContext, CamActivity::class.java))
        }
    )
    {
        // button text
        Text(
            text = "Camera Photo",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold

        )
    }
}

@Composable
fun updateScreen(navController: NavController, vm: UserProfileViewModel) {
    val auth = Firebase.auth
    val currentUser = auth.currentUser

    val UpdateVM = remember { UserProfileViewModel() }

    var img by remember { mutableStateOf(vm.userProfile.img!!) }

    val context = LocalContext.current
    var firstName by remember { mutableStateOf(vm.userProfile.firstName!!) }
    var lastName by remember { mutableStateOf(vm.userProfile.lastName!!) }



    LaunchedEffect(
        Unit,
        block = {
            vm.getLoggedInUserProfile()
        }

    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
            .padding(start = 36.dp, end = 36.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, bottom = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        )
        {
            Text(
                text = stringResource(R.string.editmyprofile),
                fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                color = Color(0xff36a8eb),
                fontSize = 26.sp
            )
        }


//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(top = 130.dp, start = 36.dp, end = 36.dp),
//        verticalArrangement = Arrangement.spacedBy(8.dp)
//    ) {
        Row(
            modifier = Modifier
                .padding(start = 0.dp, top = 0.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        )
        {
            OutlinedTextField(
                modifier = Modifier
                    .width(140.dp),
                value = firstName,
                label = { Text(text = stringResource(R.string.label_first)) },

                onValueChange = { firstName = it },
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xff36a8eb),
                    unfocusedBorderColor = Color.Gray
                )
            )

            OutlinedTextField(
                modifier = Modifier
                    .padding(start = 0.dp, top = 0.dp),
                value = lastName,
                label = { Text(text = stringResource(R.string.label_last)) },
                onValueChange = { lastName = it },
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xff36a8eb),
                    unfocusedBorderColor = Color.Gray
                )
            )
        }

        OutlinedTextField(
            value = img,
            onValueChange = { img = it },
            label = { Text(text = "Link Img") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xff36a8eb),
                unfocusedBorderColor = Color.Gray
            )
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 0.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(Color(0xff36a8eb)),
            onClick = {
                val user = UserProfile(

                    firstName = firstName,
                    lastName = lastName,

                    img = img
                )
                UpdateVM.UpdateUser(user, navController)

            }
        )
        {
            // button text
            Text(
                text = stringResource(R.string.Ubah),
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Box(
            modifier = Modifier
                .padding(start = 6.dp, top = 88.dp)
                .size(262.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Image(
                painter = painterResource(id = R.drawable.updatepicpro),
                contentDescription = null
            )
        }
    }
}

@Composable
fun AboutUsScreen(vm : AboutUsViewModel){

    LaunchedEffect(
        Unit,
        block = {
            vm.getAboutUsList()
        }
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(start = 36.dp, end = 36.dp, bottom = 56.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 30.dp, bottom = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.about),
                fontSize = 26.sp,
                color = Color(0xff36a8eb),
                fontWeight = FontWeight.Bold
            )
        }
        Box(
            modifier = Modifier
                .padding(start = 0.dp, top = 8.dp, bottom = 10.dp)
                .size(132.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painter = painterResource(id = R.drawable.iluskontak),
                contentDescription = null
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            val About = remember { vm.AboutUsList }

            LazyColumn() {
                items(
                    items = About,
                    itemContent = {
                        AboutUsItem(about = it!!)
                    }
                )
            }


        }


    }
}

@Composable
fun AboutUsItem(about: AboutUsModel){

    Text(
        text = about.isi,
        color = Color(0xff2d8bc2),
        textAlign = TextAlign.Justify,
        modifier = Modifier
            .padding(top = 10.dp, bottom = 10.dp),
        fontSize = 14.sp
    )

}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview3(){
//    PAMActivityIntentTheme {
//        ProfileScreen()
//    }
//}


