package edu.uksw.fti.pam.pamactivityintent.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import edu.uksw.fti.pam.pamactivityintent.models.UserProfile
import kotlinx.coroutines.launch

class UserProfileViewModel : ViewModel() {
        private var _userProfile by mutableStateOf(UserProfile(""))
        val db = Firebase.firestore
        val auth = Firebase.auth

        val userProfile: UserProfile
                get() = _userProfile

        fun getLoggedInUserProfile() {
                viewModelScope.launch {
                        val currentUser = auth.currentUser
                        val docRef = db.collection("users").document(currentUser?.uid!!)
                        docRef.get()
                                .addOnSuccessListener { document ->
                                        if (document != null) {
                                                _userProfile = document.toObject<UserProfile>()!!
                                        }
                                }
                }
        }



}