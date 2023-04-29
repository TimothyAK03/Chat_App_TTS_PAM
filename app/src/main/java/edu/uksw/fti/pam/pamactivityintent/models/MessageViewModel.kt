package edu.uksw.fti.pam.pamactivityintent.models

import android.os.Build
import android.os.Message
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.PropertyName
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.type.DateTime
import edu.uksw.fti.pam.pamactivityintent.models.MessageModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.time.LocalDateTime

class MessageViewModel : ViewModel() {
    private var _messageList = mutableStateListOf<MessageModel?>()
    val messageList: SnapshotStateList<MessageModel?>
        get() = _messageList

    val user = Firebase.auth.currentUser

    private val db = Firebase.firestore



    fun startListeningForUpdates(number: String) {
        val docRef = db.collection("chats_${number}")
        docRef.addSnapshotListener { snapshot, error ->
            if (error != null) {
                Log.e("Firestore", "Error getting chat updates: ", error)
                return@addSnapshotListener
            }

            if (snapshot != null) {
                val messages = snapshot.toObjects(MessageModel::class.java)
                _messageList.clear()
                _messageList.addAll(messages)
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun addMessage(message: MessageModel, number: String) {
        val docRef = db.collection("chats_${number}")
        docRef.document(LocalDateTime.now().toString())
            .set(message)
    }

    fun getMessageList() {
        viewModelScope.launch{
            val db = Firebase.firestore
            try {
                val docRef = db.collection("chats")
                docRef.addSnapshotListener { value, error ->
                    if (error != null) {
                        return@addSnapshotListener
                    }
                    val list = value?.documents
                    if (list != null) {
                        for (d in list) {
                            val c: MessageModel? = d.toObject(MessageModel::class.java)
                            _messageList.add(c)
                        }
                    }
                }

            }

            catch(e: Exception) {
                print(e)
            }
        }
    }

}