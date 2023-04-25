package edu.uksw.fti.pam.pamactivityintent.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import edu.uksw.fti.pam.pamactivityintent.models.TodosModel

@Composable
fun ChatListItem(chatt: TodosModel, navigateToProfile: (TodosModel) -> Unit){


    Card(modifier = Modifier
        .padding(10.dp, 6.dp)
        .fillMaxWidth()
        .height(90.dp)
        .clickable { navigateToProfile(chatt) }, shape = RoundedCornerShape(10.dp), elevation = 4.dp,
        backgroundColor = Color.Gray
    ) {
        Surface() {
            Row(
                Modifier
                    .padding(4.dp)
                    .fillMaxSize()
            ) {
                Image(
                    painter = rememberImagePainter(data = chatt.image,
                        builder = {
                            scale(Scale.FILL)
                            placeholder(R.drawable.notification_action_background)
                            transformations(
                                CircleCropTransformation()
                            )
                        }),
                    contentDescription = null)

                Column(verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(6.dp)
                        .fillMaxHeight()
                        .weight(0.8f))
                {
                    Text(text = chatt.title,
                        fontSize = 16.sp,
                        color = Color(0xff2d8bc2),
                        fontWeight = FontWeight.Bold

                    )
                    Text(text = chatt.chat,
                        fontSize = 12.sp,
                        color = Color(0xff2d8bc2),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Column(
                    modifier = Modifier
                        .size(height = 80.dp, width = 30.dp)
                        .padding(
                            top = 4.dp,
                            bottom = 4.dp
                        ),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("09.00", fontSize = 11.sp)
                    Icon(
                        painter = painterResource(edu.uksw.fti.pam.pamactivityintent.R.drawable.icon_read),
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }

}