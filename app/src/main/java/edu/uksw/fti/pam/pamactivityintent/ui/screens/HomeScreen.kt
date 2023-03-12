package edu.uksw.fti.pam.pamactivityintent.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import edu.uksw.fti.pam.pamactivityintent.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import kotlin.math.roundToInt
import edu.uksw.fti.pam.pamactivityintent.ui.theme.PAMActivityIntentTheme

@Composable
fun HomeField() {

            val displayMetrics = DisplayMetrics()
            val height = (displayMetrics.heightPixels / displayMetrics.density) - 150;
            val width = displayMetrics.widthPixels / displayMetrics.density;
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)

                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.07f),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.icon_notification),
                            contentDescription = stringResource(id = R.string.notif),
                            tint = Color.Gray,
                            modifier = Modifier.padding(start = 15.dp, end = 30.dp)
                        )
                        Text(
                            "Telegram",
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold,
                            color = Color(R.color.biru_tua),
                            fontSize = 22.sp
                        )
                        Row(
                            modifier = Modifier.padding(end = 15.dp)
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.filter_list),
                                contentDescription = stringResource(id = R.string.filter_list),
                                tint = Color.Gray,
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Icon(
                                painter = painterResource(R.drawable.search),
                                contentDescription = stringResource(id = R.string.search),
                                tint = Color.Gray,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.05f),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = stringResource(R.string.all),color = Color(R.color.biru_muda))
                        Text(text = stringResource(R.string.message))
                        Text(text = stringResource(R.string.grup))
                        Text(text = stringResource(R.string.channel))
                        Text(text = stringResource(R.string.unread))
                    }
                    Column(
                        modifier = Modifier
                            .background(Color.White)
                            .fillMaxWidth()
                            .size(width = width.dp, height = height.dp)
                            .verticalScroll(rememberScrollState()),

                        ) {
                        Column(
                            modifier = Modifier
                                .padding(start = 30.dp, end = 30.dp)
                                .fillMaxWidth()
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 20.dp, bottom = 20.dp)
                            ) {
                                Text(
                                    text = stringResource(R.string.message),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily.SansSerif
                                )
                                Icon(
                                    painter = painterResource(R.drawable.icon_more),
                                    contentDescription = stringResource(id = R.string.icon_more),
                                    tint = Color.Black,
                                    modifier = Modifier.size(30.dp)
                                )
                            }
                            Column(
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(
                                            shape = RoundedCornerShape(
                                                10.dp,
                                                10.dp,
                                                10.dp,
                                                10.dp
                                            )
                                        )
                                        .background(Color(0xfff2f2f2))
                                        .padding(20.dp, 15.dp, 5.dp, 15.dp)
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .fillMaxWidth(),
                                    ) {
                                        Box {
                                            Image(
                                                painter = painterResource(id = R.drawable.person_3),
                                                contentDescription = stringResource(id = R.string.profil),
                                                modifier = Modifier
                                                    .size(50.dp)
                                                    .clip(shape = RoundedCornerShape(20.dp))
                                            )
                                            Box(
                                                modifier = Modifier
                                                    .size(15.dp)
                                                    .clip(shape = CircleShape)
                                                    .background(Color(0xff93fc51))
                                                    .align(Alignment.BottomEnd)
                                                    .border(
                                                        width = 2.0.dp,
                                                        color = Color.White,
                                                        shape = CircleShape
                                                    )
                                            )
                                        }
                                        Column(
                                            modifier = Modifier
                                                .size(height = 50.dp, width = width.dp - 170.dp)
                                                .padding(
                                                    start = 10.dp,
                                                    end = 10.dp,
                                                    top = 3.dp,
                                                    bottom = 3.dp
                                                ),
                                            verticalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text(
                                                "Dier Waringin",
                                                fontSize = 16.sp,
                                                color = Color(R.color.biru_muda),
                                                fontWeight = FontWeight.Bold
                                            )
                                            Text(
                                                "Hey, you want to hangout today?",
                                                fontSize = 12.sp,
                                                color = Color(R.color.biru_muda)
                                            )
                                        }
                                        Column(
                                            modifier = Modifier
                                                .size(height = 50.dp, width = 30.dp)
                                                .padding(
                                                    top = 3.dp,
                                                    bottom = 3.dp
                                                ),
                                            verticalArrangement = Arrangement.SpaceBetween,
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Text("09.00", fontSize = 11.sp)
                                            Box(
                                                modifier = Modifier
                                                    .size(18.dp)
                                                    .clip(shape = CircleShape)
                                                    .background(Color(R.color.biru_muda)),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text(
                                                    "1",
                                                    color = Color.White,
                                                    fontSize = 10.sp,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            }
                                        }
                                    }
                                }
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(
                                            shape = RoundedCornerShape(
                                                10.dp,
                                                10.dp,
                                                10.dp,
                                                10.dp
                                            )
                                        )
                                        .background(Color(0xfff2f2f2))
                                        .padding(20.dp, 15.dp, 5.dp, 15.dp)
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .fillMaxWidth(),
                                    ) {
                                        Box {
                                            Image(
                                                painter = painterResource(id = R.drawable.person_2),
                                                contentDescription = stringResource(id = R.string.profil),
                                                modifier = Modifier
                                                    .size(50.dp)
                                                    .clip(shape = RoundedCornerShape(20.dp))
                                            )
                                            Box(
                                                modifier = Modifier
                                                    .size(15.dp)
                                                    .clip(shape = CircleShape)
                                                    .background(Color(0xff93fc51))
                                                    .align(Alignment.BottomEnd)
                                                    .border(
                                                        width = 2.0.dp,
                                                        color = Color.White,
                                                        shape = CircleShape
                                                    )
                                            )
                                        }
                                        Column(
                                            modifier = Modifier
                                                .size(height = 50.dp, width = width.dp - 170.dp)
                                                .padding(
                                                    start = 10.dp,
                                                    end = 10.dp,
                                                    top = 3.dp,
                                                    bottom = 3.dp
                                                ),
                                            verticalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text(
                                                "Rudin Sibogar",
                                                fontSize = 16.sp,
                                                color = Color(R.color.biru_muda),
                                                fontWeight = FontWeight.Bold
                                            )
                                            Text(
                                                "where are you?",
                                                fontSize = 12.sp,
                                                color = Color(R.color.biru_muda)
                                            )
                                        }
                                        Column(
                                            modifier = Modifier
                                                .size(height = 50.dp, width = 30.dp)
                                                .padding(
                                                    top = 3.dp,
                                                    bottom = 3.dp
                                                ),
                                            verticalArrangement = Arrangement.SpaceBetween,
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Text("09.00", fontSize = 11.sp)
                                            Icon(
                                                painter = painterResource(R.drawable.icon_read),
                                                contentDescription = stringResource(id = R.string.icon_more),
                                                tint = Color(0xff5fa14f),
                                                modifier = Modifier.size(20.dp)
                                            )
                                        }
                                    }
                                }
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(
                                            shape = RoundedCornerShape(
                                                10.dp,
                                                10.dp,
                                                10.dp,
                                                10.dp
                                            )
                                        )
                                        .background(Color(0xfff2f2f2))
                                        .padding(20.dp, 15.dp, 5.dp, 15.dp)
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .fillMaxWidth(),
                                    ) {
                                        Box {
                                            Image(
                                                painter = painterResource(id = R.drawable.person_1),
                                                contentDescription = stringResource(id = R.string.profil),
                                                modifier = Modifier
                                                    .size(50.dp)
                                                    .clip(shape = RoundedCornerShape(20.dp))
                                            )
                                            Box(
                                                modifier = Modifier
                                                    .size(15.dp)
                                                    .clip(shape = CircleShape)
                                                    .background(Color.Gray)
                                                    .align(Alignment.BottomEnd)
                                                    .border(
                                                        width = 2.0.dp,
                                                        color = Color.White,
                                                        shape = CircleShape
                                                    )
                                            )
                                        }
                                        Column(
                                            modifier = Modifier
                                                .size(height = 50.dp, width = width.dp - 170.dp)
                                                .padding(
                                                    start = 10.dp,
                                                    end = 10.dp,
                                                    top = 3.dp,
                                                    bottom = 3.dp
                                                ),
                                            verticalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text(
                                                "Dani Laboger",
                                                fontSize = 16.sp,
                                                color = Color(R.color.biru_muda),
                                                fontWeight = FontWeight.Bold
                                            )
                                            Text(
                                                "Wkwkwkwkwkwkwkwk",
                                                fontSize = 12.sp,
                                                color = Color(R.color.biru_muda)
                                            )
                                        }
                                        Column(
                                            modifier = Modifier
                                                .size(height = 50.dp, width = 30.dp)
                                                .padding(
                                                    top = 3.dp,
                                                    bottom = 3.dp
                                                ),
                                            verticalArrangement = Arrangement.SpaceBetween,
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Text("09.00", fontSize = 11.sp)
                                            Icon(
                                                painter = painterResource(R.drawable.icon_read),
                                                contentDescription = stringResource(id = R.string.icon_more),
                                                tint = Color.Gray,
                                                modifier = Modifier.size(20.dp)
                                            )
                                        }
                                    }
                                }
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 10.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        "More Messages",
                                        fontFamily = FontFamily.SansSerif,
                                        color = Color.Gray
                                    )
                                    Icon(
                                        painter = painterResource(R.drawable.icon_down),
                                        contentDescription = stringResource(id = R.string.icon_more),
                                        tint = Color.Gray,
                                        modifier = Modifier.size(30.dp)
                                    )
                                }
                            }
                        }

                        Column(
                            modifier = Modifier
                                .padding(start = 30.dp, end = 30.dp)
                                .fillMaxWidth()
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 20.dp, bottom = 20.dp)
                            ) {
                                Text(
                                    text = stringResource(R.string.grup),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily.SansSerif
                                )
                                Icon(
                                    painter = painterResource(R.drawable.icon_more),
                                    contentDescription = stringResource(id = R.string.icon_more),
                                    tint = Color.Black,
                                    modifier = Modifier.size(30.dp)
                                )
                            }
                            Column(
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(
                                            shape = RoundedCornerShape(
                                                10.dp,
                                                10.dp,
                                                10.dp,
                                                10.dp
                                            )
                                        )
                                        .background(Color(0xfff2f2f2))
                                        .padding(20.dp, 15.dp, 5.dp, 15.dp)
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .fillMaxWidth(),
                                    ) {
                                        Box {
                                            Image(
                                                painter = painterResource(id = R.drawable.group_1),
                                                contentDescription = stringResource(id = R.string.profil),
                                                modifier = Modifier
                                                    .size(50.dp)
                                                    .clip(shape = RoundedCornerShape(20.dp))
                                            )
                                        }
                                        Column(
                                            modifier = Modifier
                                                .size(height = 50.dp, width = width.dp - 170.dp)
                                                .padding(
                                                    start = 10.dp,
                                                    end = 10.dp,
                                                    top = 3.dp,
                                                    bottom = 3.dp
                                                ),
                                            verticalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text(
                                                "Javax Program Group",
                                                fontSize = 16.sp,
                                                color = Color(R.color.biru_muda),
                                                fontWeight = FontWeight.Bold
                                            )
                                            Row() {
                                                Text(
                                                    "Samuel : ",
                                                    fontSize = 12.sp,
                                                    color = Color.Black
                                                )
                                                Text(
                                                    "Don't forget the HW",
                                                    fontSize = 12.sp,
                                                    color = Color(R.color.biru_muda)
                                                )
                                            }
                                        }
                                        Column(
                                            modifier = Modifier
                                                .size(height = 50.dp, width = 30.dp)
                                                .padding(
                                                    top = 3.dp,
                                                    bottom = 3.dp
                                                ),
                                            verticalArrangement = Arrangement.SpaceBetween,
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Text("09.00", fontSize = 11.sp)
                                            Box(
                                                modifier = Modifier
                                                    .size(18.dp)
                                                    .clip(shape = CircleShape)
                                                    .background(Color(R.color.biru_muda)),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text(
                                                    "1",
                                                    color = Color.White,
                                                    fontSize = 10.sp,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            }
                                        }
                                    }
                                }
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(
                                            shape = RoundedCornerShape(
                                                10.dp,
                                                10.dp,
                                                10.dp,
                                                10.dp
                                            )
                                        )
                                        .background(Color(0xfff2f2f2))
                                        .padding(20.dp, 15.dp, 5.dp, 15.dp)
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .fillMaxWidth(),
                                    ) {
                                        Box {
                                            Image(
                                                painter = painterResource(id = R.drawable.group_2),
                                                contentDescription = stringResource(id = R.string.profil),
                                                modifier = Modifier
                                                    .size(50.dp)
                                                    .clip(shape = RoundedCornerShape(20.dp))
                                            )
                                        }
                                        Column(
                                            modifier = Modifier
                                                .size(height = 50.dp, width = width.dp - 170.dp)
                                                .padding(
                                                    start = 10.dp,
                                                    end = 10.dp,
                                                    top = 3.dp,
                                                    bottom = 3.dp
                                                ),
                                            verticalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text(
                                                "Andro Studio Group",
                                                fontSize = 16.sp,
                                                color = Color(R.color.biru_muda),
                                                fontWeight = FontWeight.Bold
                                            )
                                            Row() {
                                                Text(
                                                    "David : ",
                                                    fontSize = 12.sp,
                                                    color = Color.Black
                                                )
                                                Text(
                                                    "The topic is Kotlin",
                                                    fontSize = 12.sp,
                                                    color = Color(R.color.biru_muda)
                                                )
                                            }
                                        }
                                        Column(
                                            modifier = Modifier
                                                .size(height = 50.dp, width = 30.dp)
                                                .padding(
                                                    top = 3.dp,
                                                    bottom = 3.dp
                                                ),
                                            verticalArrangement = Arrangement.SpaceBetween,
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Text("09.00", fontSize = 11.sp)
                                            Box(
                                                modifier = Modifier
                                                    .size(18.dp)
                                                    .clip(shape = CircleShape)
                                                    .background(Color(R.color.biru_muda)),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text(
                                                    "1",
                                                    color = Color.White,
                                                    fontSize = 10.sp,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            }
                                        }
                                    }
                                }
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(
                                            shape = RoundedCornerShape(
                                                10.dp,
                                                10.dp,
                                                10.dp,
                                                10.dp
                                            )
                                        )
                                        .background(Color(0xfff2f2f2))
                                        .padding(20.dp, 15.dp, 5.dp, 15.dp)
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .fillMaxWidth(),
                                    ) {
                                        Box {
                                            Image(
                                                painter = painterResource(id = R.drawable.group_3),
                                                contentDescription = stringResource(id = R.string.profil),
                                                modifier = Modifier
                                                    .size(50.dp)
                                                    .clip(shape = RoundedCornerShape(20.dp))
                                            )
                                        }
                                        Column(
                                            modifier = Modifier
                                                .size(height = 50.dp, width = width.dp - 170.dp)
                                                .padding(
                                                    start = 10.dp,
                                                    end = 10.dp,
                                                    top = 3.dp,
                                                    bottom = 3.dp
                                                ),
                                            verticalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text(
                                                "Jetpack Compose Group",
                                                fontSize = 16.sp,
                                                color = Color(R.color.biru_muda),
                                                fontWeight = FontWeight.Bold
                                            )
                                            Row() {
                                                Text(
                                                    "Jeff : ",
                                                    fontSize = 12.sp,
                                                    color = Color.Black
                                                )
                                                Text(
                                                    "It has different layout",
                                                    fontSize = 12.sp,
                                                    color = Color(R.color.biru_muda)
                                                )
                                            }
                                        }
                                        Column(
                                            modifier = Modifier
                                                .size(height = 50.dp, width = 30.dp)
                                                .padding(
                                                    top = 3.dp,
                                                    bottom = 3.dp
                                                ),
                                            verticalArrangement = Arrangement.SpaceBetween,
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Text("09.00", fontSize = 11.sp)
                                            Box(
                                                modifier = Modifier
                                                    .size(18.dp)
                                                    .clip(shape = CircleShape)
                                                    .background(Color(R.color.biru_muda)),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text(
                                                    "1",
                                                    color = Color.White,
                                                    fontSize = 10.sp,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            }
                                        }
                                    }
                                }
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 10.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        "More Messages",
                                        fontFamily = FontFamily.SansSerif,
                                        color = Color.Gray
                                    )
                                    Icon(
                                        painter = painterResource(R.drawable.icon_down),
                                        contentDescription = stringResource(id = R.string.icon_more),
                                        tint = Color.Gray,
                                        modifier = Modifier.size(30.dp)
                                    )
                                }
                            }
                        }
                    }
                    Column(
                        modifier = Modifier
                            .background(Color(R.color.biru_tua))
                    ) {
                        Box(
                            modifier = Modifier
                                .size(width = width.dp, height = 15.dp)
                                .clip(shape = RoundedCornerShape(0.dp, 0.dp, 16.dp, 16.dp))
                                .background(Color.White)
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .background(Color(R.color.biru_tua))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .background(Color(R.color.biru_tua))
                                .padding(start = 20.dp, end = 20.dp),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.icon_home),
                                contentDescription = stringResource(id = R.string.icon_bottom),
                                tint = Color.White,
                                modifier = Modifier.size(30.dp)
                            )
                            Icon(
                                painter = painterResource(R.drawable.icon_call),
                                contentDescription = stringResource(id = R.string.icon_bottom),
                                tint = Color.White,
                                modifier = Modifier.size(30.dp)
                            )
                            Icon(
                                painter = painterResource(R.drawable.icon_person),
                                contentDescription = stringResource(id = R.string.icon_bottom),
                                tint = Color.White,
                                modifier = Modifier.size(30.dp)
                            )
                            Icon(
                                painter = painterResource(R.drawable.icon_settings),
                                contentDescription = stringResource(id = R.string.icon_bottom),
                                tint = Color.White,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }
                }
            }


@Preview(showBackground = true)
@Composable
fun DefaultPreview20(){
    PAMActivityIntentTheme {
        HomeField()
    }
}