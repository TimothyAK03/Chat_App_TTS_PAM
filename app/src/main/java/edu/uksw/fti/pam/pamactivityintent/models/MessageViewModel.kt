package edu.uksw.fti.pam.pamactivityintent.models

import android.content.ContentValues.TAG
import android.os.Build
import android.os.Message
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.PropertyName
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.type.DateTime
import edu.uksw.fti.pam.pamactivityintent.models.MessageModel
import edu.uksw.fti.pam.pamactivityintent.ui.BottomNavItems
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.time.LocalDateTime

class MessageViewModel : ViewModel() {
    private var _messageList = mutableStateListOf<MessageModel?>()
    val messageList: SnapshotStateList<MessageModel?>
        get() = _messageList

    val user = Firebase.auth.currentUser

    private val db = Firebase.firestore

    fun getUserFirstName(uid: String?, callback: (String?) -> Unit) {
        val docRef = db.collection("users").document(uid!!)
        val source = Source.CACHE
        docRef.get(source).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Document found in the offline cache
                val document = task.result
                val firstName = document?.getString("firstName")
                callback(firstName)
            } else {
                Log.d(TAG, "Cached get failed: ", task.exception)
            }
        }
    }

    fun startListeningForUpdates(Group: String) {
        val docRef = db.collection("chats_${Group}")
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
    fun addMessage(message: MessageModel, Group: String) {
        val docRef = db.collection("chats_${Group}")
        docRef.document(LocalDateTime.now().toString())
            .set(message)
    }

    fun DeleteChat(Group: GroupsModel, navController: NavController) {


        val auth = Firebase.auth

        val fFirestore = Firebase.firestore
        val docRef = fFirestore.collection("chats_${Group.GroupName}")

        val batch = fFirestore.batch()
        docRef.get().addOnSuccessListener { snapshot ->
            for (document in snapshot.documents) {
                batch.delete(document.reference)
            }
            batch.commit().addOnSuccessListener {
                // Collection deleted successfully

            }.addOnFailureListener { e ->
                // Handle any errors here
            }
        }
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