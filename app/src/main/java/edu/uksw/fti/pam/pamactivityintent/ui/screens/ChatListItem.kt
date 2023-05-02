package edu.uksw.fti.pam.pamactivityintent.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.base.R
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation
import edu.uksw.fti.pam.pamactivityintent.models.GroupsModel
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.Date
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ChatListItem(chatt: GroupsModel, navigateToProfile: (GroupsModel) -> Unit){
    val currentDate = Date()
    val dateFormat = SimpleDateFormat("HH-mm", Locale.getDefault())
    val formattedDate = dateFormat.format(currentDate)

    Card(modifier = Modifier
        .padding(10.dp, 6.dp)
        .padding(top = 10.dp)
        .fillMaxWidth()
        .height(90.dp)
        .clickable { navigateToProfile(chatt) }, shape = RoundedCornerShape(10.dp), elevation = 4.dp,
        backgroundColor = Color.Gray
    ) {
        Surface() {
            Row(
                Modifier
                    .padding(10.dp)
                    .fillMaxSize()
            ) {
                Row(
                    modifier = Modifier
                        .padding(top = 6.dp)
                ) {
                    Image(
                        modifier = Modifier
                            .size(60.dp),
                        painter = rememberImagePainter(data = chatt.img,
                            builder = {
                                scale(Scale.FILL)
                                placeholder(R.drawable.notification_action_background)
                                transformations(
                                    CircleCropTransformation()
                                )
                            }),
                        contentDescription = null)
                }

                Column(verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxHeight()
                        .weight(0.8f))
                {

                    chatt.GroupName?.let {
                        Text(text = it,
                            fontSize = 16.sp,
                            color = Color(0xff2d8bc2),
                            fontWeight = FontWeight.Bold
                        )
                    }

                    chatt.GroupDescription?.let {
                        Text(text = it,
                            fontSize = 12.sp,
                            color = Color(0xff2d8bc2),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .size(height = 80.dp, width = 70.dp)
                        .padding(14.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(formattedDate.toString(), fontSize = 9.sp)
                    Icon(
                        painter = painterResource(edu.uksw.fti.pam.pamactivityintent.R.drawable.icon_read),
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }

}