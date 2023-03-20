package edu.uksw.fti.pam.pamactivityintent.ui.screens

import android.content.Intent
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.uksw.fti.pam.pamactivityintent.CamActivity

@Composable
fun CamButton(){
    val lContext = LocalContext.current
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
                .padding(top = 50.dp)
        ) {
            Text(
                text = "Camera Feature",
                fontSize = 26.sp,
                color = Color(0xff36a8eb),
                fontWeight = FontWeight.Bold
            )
        }
    }
    Column(
        modifier = Modifier
            .width(350.dp)
            .padding(start = 60.dp, top = 160.dp)
    ){
        Divider(
            color = Color.Gray,
            thickness = 1.dp,
            modifier = Modifier
                .clip(shape = RoundedCornerShape(20.dp))
        )
    }
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
    Column(
        modifier = Modifier
            .width(350.dp)
            .padding(start = 60.dp, top = 350.dp)
    ){
        Divider(
            color = Color.Gray,
            thickness = 1.dp,
            modifier = Modifier
                .clip(shape = RoundedCornerShape(20.dp))
        )
    }
}