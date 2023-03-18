package edu.uksw.fti.pam.pamactivityintent.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.base.R
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation
import edu.uksw.fti.pam.pamactivityintent.models.MessageModel
import edu.uksw.fti.pam.pamactivityintent.models.MessageViewModel
import edu.uksw.fti.pam.pamactivityintent.models.TodosModel


@Composable
fun ChatScreen(chatt: TodosModel){
    Scaffold(
        backgroundColor = Color(0xFFEDEDED),
        topBar = { MessageTopBar(chatt = chatt) },
        bottomBar = { MessageBox() },
        content = { padding ->
            Column(
                modifier = Modifier.padding(padding)
            ) {
                MessageList()
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
fun MessageList() {
    val vm3 = MessageViewModel()

    LaunchedEffect(
        Unit,
        block = {
            vm3.getMessageList()
        }
    )
    val listMessage = vm3.MessageList

    LazyColumn {
        items(listMessage.size) {index ->
            Spacer(modifier = Modifier.height(8.dp))
            if(listMessage[index].isPeer) {
                PeerBubble(listMessage[index])
            } else {
                UserBubble(listMessage[index])
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
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
                Text(
                    text = message.message,
                    fontSize = 16.sp,
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
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
                Text(
                    text = message.message,
                    fontSize = 16.sp,
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun MessageBox() {

    val textState = remember { mutableStateOf(TextFieldValue()) }
    val scrollState = rememberScrollState()

    Box(Modifier.background(Color.Transparent)) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            BasicTextField(
                value = textState.value,
                modifier = Modifier.weight(1f, true)
                    .background(color = Color(0Xff6db7e3), CircleShape)
                    .padding(18.dp)

                    ,
                onValueChange = {
                    textState.value = it
                },


            )
            Spacer(modifier = Modifier.size(12.dp))

            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Send, contentDescription = null)
            }
        }
    }
}
