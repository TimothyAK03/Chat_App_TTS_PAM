package edu.uksw.fti.pam.pamactivityintent.models

import com.google.firebase.firestore.PropertyName


data class MessageModel(
    var message: String? = null,
    var isPeer: String? = null,
    var test: Boolean? = null
)

