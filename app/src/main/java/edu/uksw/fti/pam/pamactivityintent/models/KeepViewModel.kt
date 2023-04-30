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

class KeepViewModel : ViewModel() {

    private var _keepModelList= mutableStateListOf<KeepModel?>()

    var errorMessage: String by mutableStateOf("")
    val keepModelList: SnapshotStateList<KeepModel?>
        get() = _keepModelList

    fun getKeepList() {
        viewModelScope.launch{
            val db = Firebase.firestore
            try {
                val docRef = db.collection("photo")
                docRef.get()
                    .addOnSuccessListener { queryDocumentSnapshots ->
                        if (queryDocumentSnapshots != null) {
                            val list = queryDocumentSnapshots.documents
                            for (d in list) {
                                val c: KeepModel? = d.toObject(KeepModel::class.java)
                                _keepModelList.add(c)
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