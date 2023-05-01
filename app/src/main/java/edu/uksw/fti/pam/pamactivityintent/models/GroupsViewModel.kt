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
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import edu.uksw.fti.pam.pamactivityintent.ui.BottomNavItems
import kotlinx.coroutines.launch

class GroupsViewModel : ViewModel() {
    val user = Firebase.auth.currentUser
    private var _groupsModelList = mutableStateListOf<GroupsModel?>()
    private var _CertainGroup by mutableStateOf(GroupsModel("","",""))
    private val db = Firebase.firestore
    private val docRef = db.collection("Group")

    var errorMessage: String by mutableStateOf("")
    val groupsModelList: SnapshotStateList<GroupsModel?>
        get() = _groupsModelList

    val CertainGroup: GroupsModel
        get() = _CertainGroup

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

    fun getCertainGroup(GroupName:String) {

            try {
                docRef.document(GroupName).get()
                    .addOnSuccessListener { document ->
                        if (document != null) {
                            _CertainGroup = document.toObject<GroupsModel>()!!
                        }
                    }
            }
            catch(e: Exception) {
                errorMessage = e.message!!
            }

    }

    fun UpdateGroup(Group: GroupsModel, navController: NavController, GroupName: String) {

        var img = "https://media-assets-ggwp.s3.ap-southeast-1.amazonaws.com/2020/10/Reverie-One-Piece--640x360.jpg"

        if (Group.img == ""){
            Group.img =  "https://media-assets-ggwp.s3.ap-southeast-1.amazonaws.com/2020/10/Reverie-One-Piece--640x360.jpg"
            val fFirestore = Firebase.firestore
            fFirestore.collection("Group").document(GroupName)
                .delete()

                .addOnCompleteListener {task->
                    if(task.isSuccessful){
                        fFirestore.collection("Group").document(GroupName)
                        .set(Group)
                        navController.navigate(BottomNavItems.Group.screen_route)
                    }


                }
        }
        else{
            val fFirestore = Firebase.firestore
            fFirestore.collection("Group").document(GroupName)
                .delete()

                .addOnCompleteListener {task->
                    if(task.isSuccessful){
                        fFirestore.collection("Group").document(GroupName)
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