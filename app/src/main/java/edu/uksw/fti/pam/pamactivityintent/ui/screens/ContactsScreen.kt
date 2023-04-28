package edu.uksw.fti.pam.pamactivityintent.ui.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.uksw.fti.pam.pamactivityintent.ChatActivity
import edu.uksw.fti.pam.pamactivityintent.R
import edu.uksw.fti.pam.pamactivityintent.models.ContactModel
import edu.uksw.fti.pam.pamactivityintent.models.ContactViewModel
import edu.uksw.fti.pam.pamactivityintent.models.GroupModel
import edu.uksw.fti.pam.pamactivityintent.models.TodosModel
import edu.uksw.fti.pam.pamactivityintent.ui.BottomNavItems
import edu.uksw.fti.pam.pamactivityintent.ui.ContactItems

fun AddtoFstore(){

}
@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ContactItems.ContactScreen.route) {
        composable(route = ContactItems.ContactScreen.route){

        }
    }
}

@Composable
fun DetailScreen(nama: String?){
    Box( modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
        ) {
        Text(text = "Hello, $nama")
    }

}

@Composable
fun AddContact(navController: NavController){
    var firstNameInput by remember { mutableStateOf("") }
    var lastNameInput by remember { mutableStateOf("") }
    var numberInput by remember { mutableStateOf("") }
    var img by remember { mutableStateOf("") }
    val contactVM = remember { ContactViewModel() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 130.dp, start = 36.dp, end = 36.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(start = 0.dp, top = 0.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        )
        {
            OutlinedTextField(
                modifier = Modifier
                    .width(140.dp),
                value = firstNameInput,
                label = { Text(text = stringResource(R.string.label_first)) },
                onValueChange = { firstNameInput = it },
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xff36a8eb),
                    unfocusedBorderColor = Color.Gray)
            )

            OutlinedTextField(
                modifier = Modifier
                    .padding(start = 0.dp, top = 0.dp),
                value = lastNameInput,
                label = { Text(text = stringResource(R.string.label_last)) },
                onValueChange = { lastNameInput = it },
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xff36a8eb),
                    unfocusedBorderColor = Color.Gray)
            )
        }

        OutlinedTextField(
            value = numberInput,
            onValueChange = { numberInput = it },
            label = { Text(text = "Number") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xff36a8eb),
                unfocusedBorderColor = Color.Gray)
        )

        OutlinedTextField(
            value = img,
            onValueChange = { img = it },
            label = { Text(text = "Img")},
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xff36a8eb),
                unfocusedBorderColor = Color.Gray)
        )


        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 0.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(Color(0xff36a8eb)),
            onClick = {
                val newContact = ContactModel(
                    firstName = firstNameInput,
                    lastName = lastNameInput,
                    number =  numberInput,
                    img =  img
                )
                contactVM.AddNewContact(newContact, navController)

            }
        )
        {
            // button text
            Text(
                text = "Save",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold

            )
        }
    }
}


@Composable
fun ContactsScreen(navController: NavController) {
    val vm = ContactViewModel()
    LaunchedEffect(
        Unit,
        block = {
            vm.getContactList()
        }
    )
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 30.dp, bottom = 16.dp)
        ) {
            Text(text = stringResource(R.string.add_contact),
                fontSize = 26.sp,
                color = Color(0xff36a8eb),
                fontWeight = FontWeight.Bold
            )
        }
        Box(
            modifier = Modifier
                .padding(start = 0.dp, top = 8.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Box(
                modifier = Modifier
                    .padding(start = 0.dp, top = 8.dp)
                    .size(132.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Image(
                    painter = painterResource(id = R.drawable.iluskontak),
                    contentDescription = null
                )
            }
        }
        Row(
            modifier = Modifier
                .padding(start = 200.dp, top = 12.dp, bottom = 16.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.add),
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier
                    .padding(start = 12.dp)
                    .size(20.dp)
            )
            Icon(
                painter = painterResource(R.drawable.search),
                contentDescription = stringResource(id = R.string.search),
                tint = Color.Gray,
                modifier = Modifier.padding(start = 12.dp)
            )
            Icon(
                painter = painterResource(R.drawable._dots),
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier
                    .padding(start = 12.dp)
                    .size(20.dp)
            )
        }

        val cont = remember { vm.contactModelList }

        LazyColumn( verticalArrangement = Arrangement.SpaceBetween) {
            items(
                items = cont,
                itemContent = {
                    if (it != null) {
                        ContactListItem(contt = it)
                    }
                }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 20.dp, bottom = 100.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End

        ) {
            FloatingActionButton(
                onClick = { navController.navigate(ContactItems.AddScreen.route)},
                backgroundColor = Color(0xFF5BB8EE),
                contentColor = Color.White
            ) {
                Icon(Icons.Filled.Add, "")
            }
        }

    }
}


@SuppressLint("SuspiciousIndentation")
@Composable
fun ContactListItem( contt: ContactModel) {
    Row(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .fillMaxWidth(0.9f)
            .fillMaxHeight(0.3f)
            .size(60.dp)
            .background(Color(0xFF5BB8EE))
            .clickable {
            },
        horizontalArrangement = Arrangement.Start,
    ) {

        AsyncImage( // <--- foto kudu nganggo async image
            model = contt.img,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(top = 10.dp, start = 10.dp)
                .clip(CircleShape)
                .width(40.dp)
                .height(40.dp), // <---- ganti2 wae neng modifier
        )
        Row(
            modifier = Modifier
                .padding(top = 16.dp, start = 12.dp)
        ) {

                contt.firstName?.let {
                    Text(
                        text = it,
                        fontSize = 18.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Normal )
                }

                contt.lastName?.let {
                Text(
                    modifier = Modifier
                        .padding(start = 5.dp),
                    text = it,
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Normal )
            }
        }

    }


    Spacer(modifier = Modifier
        .height(20.dp)
        .padding(20.dp))

}
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview4(){
//    PAMActivityIntentTheme {
//        ContactsScreen()
//    }
//}

