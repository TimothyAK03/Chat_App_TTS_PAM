package edu.uksw.fti.pam.pamactivityintent.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import edu.uksw.fti.pam.pamactivityintent.models.ContactsViewModel
import edu.uksw.fti.pam.pamactivityintent.ui.theme.PAMActivityIntentTheme
import edu.uksw.fti.pam.pamactivityintent.R

@Composable
fun ContactsScreen() {
    val vm = ContactsViewModel()
    LaunchedEffect(
        Unit,
        block = {
            vm.getToDoList()
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
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .wrapContentHeight()
                .padding(top = 30.dp)
        ) {
            Text(text = stringResource(R.string.add_contact))
        }
        if (vm.errorMessage.isEmpty()) {
            LazyColumn(
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                items(vm.toDoList.size) { index ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .fillMaxHeight(0.3f)
                            .padding(top = 15.dp)
                            .background(Color.LightGray),
                        horizontalArrangement = Arrangement.Start,
                    ) {

                        AsyncImage(
                            model = vm.toDoList[index].fotoProfil,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .clip(CircleShape)
                                .width(50.dp)
                                .height(50.dp),
                        )
                        Column() {
                            Text(text = vm.toDoList[index].namaDepan)
                            Text(text = vm.toDoList[index].namaBelakang)
                        }

                    }
                }
            }
        } else {
            Text(text = vm.errorMessage)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview4(){
    PAMActivityIntentTheme {
        ContactsScreen()
    }
}

