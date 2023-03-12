package edu.uksw.fti.pam.pamactivityintent.ui.screens

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.uksw.fti.pam.pamactivityintent.ui.theme.PAMActivityIntentTheme

@Composable
fun ProfileScreen() {
    val context = LocalContext.current
    val dataStore = FirstName(context)
    val storeData = LastName(context)
    val savedFirst = dataStore.getFirst.collectAsState(initial = "")
    val savedLast = storeData.getLast.collectAsState(initial = "")

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp))
                .padding(top = 40.dp, start = 16.dp, end = 16.dp, bottom = 0.dp)
                .clip(shape = RoundedCornerShape(16.dp))
                .height(160.dp)
                .background(Color(0xff36a8eb))
        ) {
                    Box(
                        modifier = Modifier
                            .padding(start = 24.dp, top = 20.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.person_2),
                            contentDescription = stringResource(id = R.string.profil),
                            modifier = Modifier
                                .size(112.dp)
                                .clip(shape = RoundedCornerShape(80.dp))
                        )
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(shape = CircleShape)
                                .background(Color.White)
                                .align(Alignment.BottomEnd)
                                .border(
                                    width = 4.0.dp,
                                    color = Color(0xff36a8eb),
                                    shape = CircleShape
                                )
                            ){
                                Icon(
                                painter = painterResource(R.drawable.icon_home),
                                contentDescription = stringResource(id = R.string.icon_bottom),
                                tint = Color(0xff36a8eb),
                                modifier = Modifier
                                    .size(26.dp)
                                    .padding(start = 6.dp, top = 2.dp)
                                )
                            }
                        }
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(top = 120.dp),
//                horizontalArrangement = Arrangement.Center
//            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 154.dp, top = 36.dp),
                    text = "Hallo ,  ",
                    fontSize = 20.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
                Text(
                    modifier = Modifier
                        .padding(start = 222.dp, top = 36.dp),
                    text = savedFirst.value!!,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    modifier = Modifier
                    .padding(start = 282.dp, top = 36.dp),
                    text = " "
                )
                Text(
                    modifier = Modifier
                        .padding(start = 288.dp, top = 36.dp),
                    text = savedLast.value!!,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
                Text(
                    modifier = Modifier
                        .padding(start = 154.dp, top = 72.dp),
                    text = "Saya Suka Menabung ...",
                    fontSize = 16.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal,
                    color = Color.White,
                )
        }
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .padding(top = 148.dp,  end = 48.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ){
                    Button(
                        modifier = Modifier
                            .width(140.dp)
                            .height(32.dp)
                            .padding(start = 0.dp, top = 0.dp),
                        colors = ButtonDefaults.buttonColors(Color.White),
                        onClick = {},
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = "Edit My Profile",
                            fontSize = 12.sp,
                            color = Color(0xff36a8eb),
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Icon(
                        painter = painterResource(R.drawable.share),
                        contentDescription = stringResource(id = R.string.share),
                        tint = Color.White,
                        modifier = Modifier
                            .size(28.dp)
                            .padding(start = 8.dp, top = 2.dp)
                        )
                    }
                }
            }

@Preview(showBackground = true)
@Composable
fun DefaultPreview3(){
    PAMActivityIntentTheme {
        ProfileScreen()
    }
}


