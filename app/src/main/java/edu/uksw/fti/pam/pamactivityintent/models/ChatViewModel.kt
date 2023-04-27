package edu.uksw.fti.pam.pamactivityintent.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    private var _chatModelList= mutableStateListOf<ChatModel?>()

    var errorMessage: String by mutableStateOf("")
    val chatModelList: SnapshotStateList<ChatModel?>
        get() = _chatModelList

    fun getContactList(name: String) {
        viewModelScope.launch{
            val db = Firebase.firestore
            try {
                val docRef = db.collection("chat_$name")
                docRef.get()
                    .addOnSuccessListener { queryDocumentSnapshots ->
                        if (queryDocumentSnapshots != null) {
                            val list = queryDocumentSnapshots.documents
                            for (d in list) {
                                val c: ChatModel? = d.toObject(ChatModel::class.java)
                                _chatModelList.add(c)
                            }
                        }
                    }
            }
            catch(e: Exception) {
                errorMessage = e.message!!
            }
        }

    }
}