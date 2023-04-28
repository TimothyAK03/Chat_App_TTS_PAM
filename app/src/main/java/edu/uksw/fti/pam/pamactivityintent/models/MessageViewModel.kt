package edu.uksw.fti.pam.pamactivityintent.models

import android.os.Message
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.PropertyName
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import edu.uksw.fti.pam.pamactivityintent.models.MessageModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class MessageViewModel : ViewModel() {
    private var _messageList = mutableStateListOf<MessageModel?>()
    val messageList: SnapshotStateList<MessageModel?>
        get() = _messageList

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