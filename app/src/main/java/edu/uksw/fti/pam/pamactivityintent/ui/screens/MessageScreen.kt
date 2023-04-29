package edu.uksw.fti.pam.pamactivityintent.ui.screens

import android.content.Intent
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.uksw.fti.pam.pamactivityintent.HomeActivity
import edu.uksw.fti.pam.pamactivityintent.models.*


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ChatScreen(chatt: GroupsModel){
    val messageVM = remember { MessageViewModel() }
    LaunchedEffect(Unit) {
        messageVM.startListeningForUpdates(chatt.GroupName!!)
    }
    Scaffold(
        backgroundColor = Color(0xFFEDEDED),
        topBar = { MessageTopBar(chatt = chatt) },
        bottomBar = { MessageBox(messageVM,chatt = chatt) },
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
fun MessageTopBar(chatt: GroupsModel) {
    var selected by remember { mutableStateOf(false) }
    val color = if (selected) Color.Blue else Color.White
    val color2 = if (selected) Color.White else Color.Blue
    val lcontext = LocalContext.current
    val FavGroupVM = remember { FavGroupViewModel() }
    val GroupVM = remember { GroupsViewModel() }
    TopAppBar(
        title = {
            Column(Modifier.padding(start = 16.dp)) {
                Text(chatt.GroupName!!, fontSize = 17.sp)
                Text(chatt.GroupDescription!!, fontSize = 13.sp)


            }
        },
        actions = {
            IconButton(onClick = {
                selected = !selected
                val newGroup = FavGroupModel(
                    GroupName = chatt.GroupName,
                    GroupDescription = chatt.GroupDescription,
                    img =  chatt.img
                )

                FavGroupVM.AddNewContact(newGroup)
            }) {
                Icon(
                    imageVector = Icons.Default.ThumbUp,
                    contentDescription = null,
                    tint = color
                )
            }
            IconButton(onClick = {
                selected = !selected
                val newGroup = FavGroupModel(
                    GroupName = chatt.GroupName,
                    GroupDescription = chatt.GroupDescription,
                    img =  chatt.img
                )

                FavGroupVM.Delete(newGroup)
            }) {
                Icon(
                    imageVector = Icons.Default.ThumbUp,
                    contentDescription = null,
                    tint = color2
                )
            }
            IconButton(onClick = {

                val fFirestore = Firebase.firestore
                val docRef = fFirestore.collection("chats_${chatt.GroupName}")

                val batch = fFirestore.batch()
                docRef.get().addOnSuccessListener { snapshot ->
                    for (document in snapshot.documents) {
                        batch.delete(document.reference)
                    }
                    batch.commit().addOnSuccessListener {
                        lcontext.startActivity(Intent(lcontext,HomeActivity::class.java))

                    }.addOnFailureListener { e ->
                        // Handle any errors here
                    }
                }

            }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    tint = Color.Red
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
                    modifier = Modifier.clickable { lcontext.startActivity(Intent(lcontext,HomeActivity::class.java)) }
                    )
                Image(
                    painter = rememberImagePainter(data = chatt.img),
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
    val currentUser = messageVM.user
    LazyColumn () {
        items(
            items = listMessage,
            itemContent = {
                if (it != null) {
                    if (it.userID == currentUser?.uid)
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
fun MessageBox(messageVM: MessageViewModel, chatt: GroupsModel) {
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
                val currentUser = messageVM.user
                val newMessage = MessageModel(
                    message = textState,
                    userID = currentUser?.uid!!,
                    isPeer = true,
                )
                messageVM.addMessage(newMessage,chatt.GroupName!!)
            })  {
                Icon(imageVector = Icons.Default.Send, contentDescription = null)
            }
        }
    }
}
