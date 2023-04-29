package edu.uksw.fti.pam.pamactivityintent.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.uksw.fti.pam.pamactivityintent.ui.BottomNavItems
import kotlinx.coroutines.launch

class ContactViewModel : ViewModel() {
    val user = Firebase.auth.currentUser
    private var _contactModelList = mutableStateListOf<ContactModel?>()
    private val db = Firebase.firestore
    private val docRef = db.collection("users")

    var errorMessage: String by mutableStateOf("")
    val contactModelList: SnapshotStateList<ContactModel?>
        get() = _contactModelList

    fun getContactList() {
        viewModelScope.launch{
            try {

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
    fun AddNewContact(Contact: ContactModel, navController: NavController) {


        val auth = Firebase.auth


        val fFirestore = Firebase.firestore
        fFirestore.collection("users")
            .add(Contact)
            .addOnCompleteListener {task->
                if(task.isSuccessful){
                    auth.createUserWithEmailAndPassword(Contact.firstName+Contact.lastName!!, Contact.number!!)
                    navController.navigate(BottomNavItems.Contact.screen_route)
                }


            }
    }
}