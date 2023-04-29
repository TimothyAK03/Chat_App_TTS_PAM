package edu.uksw.fti.pam.pamactivityintent.models

import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.uksw.fti.pam.pamactivityintent.ui.BottomNavItems
import kotlinx.coroutines.launch

class FavGroupViewModel : ViewModel() {
    val user = Firebase.auth.currentUser
    private var _groupsModelList = mutableStateListOf<GroupsModel?>()
    private val db = Firebase.firestore
    private val docRef = db.collection("FavGroup")

    var errorMessage: String by mutableStateOf("")
    val groupsModelList: SnapshotStateList<GroupsModel?>
        get() = _groupsModelList

    fun getContactList() {
        viewModelScope.launch{
            try {

                docRef.get()
                    .addOnSuccessListener { queryDocumentSnapshots ->
                        if (queryDocumentSnapshots != null) {

                            val list = queryDocumentSnapshots.documents
                            for (d in list) {
                                val c: GroupsModel? = d.toObject(GroupsModel::class.java)
                                _groupsModelList.add(c)
                            }
                        }
                    }
            }
            catch(e: Exception) {
                errorMessage = e.message!!
            }
        }
    }
    fun AddNewContact(Group: FavGroupModel) {


        val auth = Firebase.auth


        val fFirestore = Firebase.firestore
        fFirestore.collection("FavGroup").document(Group.GroupName!!)
            .set(Group)

    }

    fun Delete(Group: FavGroupModel) {


        val auth = Firebase.auth


        val fFirestore = Firebase.firestore
        fFirestore.collection("FavGroup").document(Group.GroupName!!)
            .delete()

    }
}