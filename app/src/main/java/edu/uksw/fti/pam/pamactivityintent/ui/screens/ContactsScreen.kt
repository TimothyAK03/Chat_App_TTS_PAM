package edu.uksw.fti.pam.pamactivityintent.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import edu.uksw.fti.pam.pamactivityintent.R
import edu.uksw.fti.pam.pamactivityintent.models.GroupsModel
import edu.uksw.fti.pam.pamactivityintent.models.GroupsViewModel
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

    var GroupnameInput by remember { mutableStateOf("") }
    var GroupDescription by remember { mutableStateOf("") }

    var img by remember { mutableStateOf("") }
    val contactVM = remember { GroupsViewModel() }



    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(start = 36.dp, end = 36.dp),
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
                text = "Group List",
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


        OutlinedTextField(
            value = GroupnameInput,
            onValueChange = { GroupnameInput = it },
            label = { Text(text = stringResource(R.string.grupName)) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xff36a8eb),
                unfocusedBorderColor = Color.Gray)
        )

        OutlinedTextField(
            value = GroupDescription,
            onValueChange = { GroupDescription = it },
            label = { Text(text = stringResource(R.string.grupDesc)) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xff36a8eb),
                unfocusedBorderColor = Color.Gray)
        )


        OutlinedTextField(
            value = img,
            onValueChange = { img = it },
            label = { Text(text = "Link Img")},
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
                val newGroup = GroupsModel(
                    GroupName = GroupnameInput,
                    GroupDescription = GroupDescription,
                    img =  img
                )
                contactVM.AddNewContact(newGroup, navController)

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
    val vm = GroupsViewModel()
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
            Text(text = "Group List",
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
                    .padding(start = 0.dp, top = 8.dp, bottom = 10.dp)
                    .size(132.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Image(
                    painter = painterResource(id = R.drawable.iluskontak),
                    contentDescription = null
                )
            }
        }


        val cont = remember { vm.groupsModelList }

        LazyColumn(
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            items(
                items = cont,
                itemContent = {
                    if (it != null) {
                        GroupListItem(contt = it,navController)
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
fun GroupListItem( contt: GroupsModel,navController: NavController) {
    val delVM = remember { GroupsViewModel() }

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
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth()

        ) {

                contt.GroupName?.let {
                    Text(
                        text = it,
                        fontSize = 18.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Normal )
                }
            
            Text(
                text = "Delete",
                fontSize = 18.sp,
                color = Color.Blue,
                fontWeight = FontWeight.Normal,
                modifier = Modifier

                    .clickable { delVM.DeleteGroup(contt.GroupName!!,navController) }



            )


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

