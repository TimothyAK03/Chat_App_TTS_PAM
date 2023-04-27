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

class ContactViewModel : ViewModel() {

private var _contactModelList= mutableStateListOf<ContactModel?>()

    var errorMessage: String by mutableStateOf("")
    val contactModelList: SnapshotStateList<ContactModel?>
        get() = _contactModelList

    fun getContactList() {
        viewModelScope.launch{
            val db = Firebase.firestore
            try {
                val docRef = db.collection("contact")
                docRef.get()
                    .addOnSuccessListener { queryDocumentSnapshots ->
                        if (queryDocumentSnapshots != null) {
                            val list = queryDocumentSnapshots.documents
                            for (d in list) {
                                val c: ContactModel? = d.toObject(ContactModel::class.java)
                                _contactModelList.add(c)
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