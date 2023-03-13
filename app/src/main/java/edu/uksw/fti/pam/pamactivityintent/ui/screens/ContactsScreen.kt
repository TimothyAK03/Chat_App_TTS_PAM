package edu.uksw.fti.pam.pamactivityintent.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                modifier = Modifier.padding(start = 12.dp).size(20.dp)
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
                modifier = Modifier.padding(start = 12.dp).size(20.dp)
            )
        }
        if (vm.errorMessage.isEmpty()) {
            LazyColumn(
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                //ngoding neng jero items, items ojo diapus,
                //foto nganggo async image seko JSON ojo diapus, ganti ukuran mbek posisi wae
                //text format e kudu koyo seng tk tulis nde ngisor
                //nama depan, napa belakang mbek foto dideleh 1x wae, langsung muncul kabeh
                items(vm.toDoList.size) { index ->
                    //row dinggo barisi chat foto, mbek jeneng
                    Row(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(10.dp))
                            .fillMaxWidth(0.9f)
                            .fillMaxHeight(0.3f)
                            .size(60.dp)
                            .background(Color(0xFF5BB8EE)),
                        horizontalArrangement = Arrangement.Start,
                    ) {

                        AsyncImage( // <--- foto kudu nganggo async image
                            model = vm.toDoList[index].fotoProfil,
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
                            Text(
                                text = vm.toDoList[index].namaDepan,
                                fontSize = 18.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Normal )//<-- ngetext format e ngene
                            Text(
                                modifier = Modifier
                                    .padding(start = 5.dp),
                                text = vm.toDoList[index].namaBelakang,
                                fontSize = 18.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Normal)
                        }

                    }
                    Spacer(modifier = Modifier
                        .height(20.dp)
                        .padding(20.dp))
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

