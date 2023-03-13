package edu.uksw.fti.pam.pamactivityintent.ui.screens

import android.content.Intent
import androidx.appcompat.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.uksw.fti.pam.pamactivityintent.CameraActivity
import edu.uksw.fti.pam.pamactivityintent.HomeActivity
import edu.uksw.fti.pam.pamactivityintent.models.AccountModel
import edu.uksw.fti.pam.pamactivityintent.ui.theme.PAMActivityIntentTheme

@Composable
fun camButton() {
    val lContext = LocalContext.current
    Row() {
        Text(
            modifier = Modifier
                .padding(top = 30.dp, start = 120.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            text = "Camera Feature")
    }
    Column(
        modifier = Modifier
            .width(350.dp)
            .padding(start = 40.dp, top = 100.dp)
    ){
        Divider(
            color = Color.Gray,
            thickness = 1.dp,
            modifier = Modifier
                .clip(shape = RoundedCornerShape(20.dp))
        )
    }
    Row(
        modifier = Modifier
            .wrapContentSize(Alignment.Center)
//            .background(colorResource(id = R.color.material_grey_300))
            .fillMaxSize()
    ) {
        Button(
            modifier = Modifier
                .height(200.dp)
                .padding(start = 100.dp, top = 150.dp),
            colors = ButtonDefaults.buttonColors(Color(0xff36a8eb)),
            onClick = {
                lContext.startActivity(Intent(lContext, CameraActivity::class.java))
            },
            shape = RoundedCornerShape(8.dp)
        )
        {
            Text(
                text = "Take A Picture",
                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
    Column(
        modifier = Modifier
            .width(350.dp)
            .padding(start = 40.dp, top = 260.dp)
    ){
        Divider(
            color = Color.Gray,
            thickness = 1.dp,
            modifier = Modifier
                .clip(shape = RoundedCornerShape(20.dp))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun buttonCam() {
    PAMActivityIntentTheme {
        camButton()
    }
}

