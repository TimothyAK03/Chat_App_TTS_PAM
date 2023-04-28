package edu.uksw.fti.pam.pamactivityintent.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import edu.uksw.fti.pam.pamactivityintent.models.MessageModel
import edu.uksw.fti.pam.pamactivityintent.models.MessageViewModel
import edu.uksw.fti.pam.pamactivityintent.models.TodosModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ChatScreen(chatt: TodosModel){
    val messageVM = remember { MessageViewModel() }
    LaunchedEffect(Unit) {
        messageVM.startListeningForUpdates()
    }
    Scaffold(
        backgroundColor = Color(0xFFEDEDED),
        topBar = { MessageTopBar(chatt = chatt) },
        bottomBar = { MessageBox(messageVM) },
        content = { padding ->
            Column(
                modifier = Modifier.padding(padding)
            ) {
                MessageList(messageVM)
            }
        }
    )
}



@Composable
fun MessageTopBar(chatt: TodosModel) {
    TopAppBar(
        title = {
            Column(Modifier.padding(start = 16.dp)) {
                Text(chatt.title, fontSize = 17.sp)

            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Call,
                    contentDescription = null,
                    tint = Color.White
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.ThumbUp,
                    contentDescription = null,
                    tint = Color.White
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        },
        navigationIcon = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 4.dp, vertical = 8.dp)
                    .fillMaxWidth()
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null,
                    modifier = Modifier.clickable {  }
                    )
                Image(
                    painter = rememberImagePainter(data = chatt.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                )


            }
        },
    )
}

@Composable
fun MessageList(messageVM: MessageViewModel) {

    val listMessage =  remember { messageVM.messageList }
    LazyColumn () {
        items(
            items = listMessage,
            itemContent = {
                if (it != null) {
                    if (it.isPeer == true)
                        UserBubble(message = it)
                    else
                        PeerBubble(message = it)
                }
            }
        )
    }
}

@Composable
fun UserBubble(message: MessageModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(80.dp, end = 10.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color = Color(0Xff6db7e3)),
    ) {
        Row(modifier = Modifier.padding(all = 10.dp)) {
            Column(modifier = Modifier.weight(3.0f, true)) {
                message.message?.let {
                    Text(
                        text = it,
                        fontSize = 16.sp,
                        color = Color.Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

            }
        }
    }
}

@Composable
fun PeerBubble(message: MessageModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, end = 80.dp)
            .background(color = Color.White)
            .clip(RoundedCornerShape(8.dp)),
    ) {
        Row(modifier = Modifier.padding(10.dp)) {
            Column(modifier = Modifier.weight(3.0f, true)) {
                message.message?.let {
                    Text(
                        text = it,
                        fontSize = 16.sp,
                        color = Color.Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MessageBox(messageVM: MessageViewModel) {
    var textState by remember { mutableStateOf("") }

    Box(Modifier.background(Color.Transparent)) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            TextField(
                value = textState,
                modifier = Modifier
                    .weight(1f, true)
                    .background(color = Color(0Xff6db7e3), CircleShape)
                    .padding(18.dp)

                    ,
                onValueChange = {
                    textState = it
                },
            )
            Spacer(modifier = Modifier.size(12.dp))
            FloatingActionButton(onClick = {
                val newMessage = MessageModel(
                    message = textState,
                    isPeer = true,
                )
                messageVM.addMessage(newMessage)
            })  {
                Icon(imageVector = Icons.Default.Send, contentDescription = null)
            }
        }
    }
}
