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

class GroupsViewModel : ViewModel() {
    val user = Firebase.auth.currentUser
    private var _groupsModelList = mutableStateListOf<GroupsModel?>()
    private val db = Firebase.firestore
    private val docRef = db.collection("Group")

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
    fun UpdateGroup(Group: GroupsModel, navController: NavController, GroupName: String) {

        var img = "https://media-assets-ggwp.s3.ap-southeast-1.amazonaws.com/2020/10/Reverie-One-Piece--640x360.jpg"

        if (Group.img == "") {
            Group.img =
                "https://media-assets-ggwp.s3.ap-southeast-1.amazonaws.com/2020/10/Reverie-One-Piece--640x360.jpg"
            val fFirestore = Firebase.firestore

            fFirestore.collection("Group").document(GroupName)
                .delete()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                        fFirestore.collection("chats_${GroupName}")
                            .get()
                            .addOnSuccessListener { querySnapshot ->
                                for (document in querySnapshot.documents) {
                                    val data = document.data
                                    if (data != null) {
                                        fFirestore.collection("chats_${Group.GroupName!!}").document(document.id).set(data)
                                        fFirestore.collection("chats_${GroupName}").document(document.id).delete()
                                    }
                                }
                            }

                        fFirestore.collection("FavGroup").document(GroupName)
                            .get()
                            .addOnSuccessListener { document ->
                                if (document != null && document.exists()) {
                                    fFirestore.collection("FavGroup").document(GroupName)
                                        .delete()
                                    fFirestore.collection("FavGroup").document(Group.GroupName!!)
                                        .set(Group)

                                }

                            }
                        fFirestore.collection("Group").document(Group.GroupName!!)
                            .set(Group)
                        navController.navigate(BottomNavItems.Group.screen_route)


                    }
                }
        }
        else{
            val fFirestore = Firebase.firestore

            fFirestore.collection("Group").document(GroupName)
                .delete()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                        fFirestore.collection("FavGroup").document(GroupName)
                            .get()
                            .addOnSuccessListener { document ->
                                if (document != null && document.exists()) {
                                    fFirestore.collection("FavGroup").document(GroupName)
                                        .delete()
                                    fFirestore.collection("FavGroup").document(Group.GroupName!!)
                                        .set(Group)
                                    navController.navigate(BottomNavItems.Group.screen_route)
                                } else {
                                    navController.navigate(BottomNavItems.Group.screen_route)
                                }

                            }
                        fFirestore.collection("Group").document(Group.GroupName!!)
                            .set(Group)
                        navController.navigate(BottomNavItems.Group.screen_route)


                    }
                }
        }


    }

    fun AddNewContact(Group: GroupsModel, navController: NavController) {




        if (Group.img == ""){
            Group.img =  "https://media-assets-ggwp.s3.ap-southeast-1.amazonaws.com/2020/10/Reverie-One-Piece--640x360.jpg"
            val fFirestore = Firebase.firestore
            fFirestore.collection("Group").document(Group.GroupName!!)
                .set(Group)
                .addOnCompleteListener {task->
                    if(task.isSuccessful){
                        navController.navigate(BottomNavItems.Group.screen_route)
                    }


                }
        }
        else{
            val fFirestore = Firebase.firestore
            fFirestore.collection("Group").document(Group.GroupName!!)
                .set(Group)
                .addOnCompleteListener {task->
                    if(task.isSuccessful){
                        navController.navigate(BottomNavItems.Group.screen_route)
                    }


                }
        }

    }


    fun DeleteGroup(GroupName : String, navController: NavController) {





        val fFirestore = Firebase.firestore
        fFirestore.collection("Group").document(GroupName)
            .delete()
            .addOnCompleteListener {task->
                if(task.isSuccessful){
                    navController.navigate(BottomNavItems.Group.screen_route)
                }


            }
    }


}