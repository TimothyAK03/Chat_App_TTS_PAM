package edu.uksw.fti.pam.pamactivityintent.models

import com.google.firebase.firestore.PropertyName
import com.google.firebase.firestore.ServerTimestamp
import java.time.LocalDateTime
import java.util.UUID


data class MessageModel(
    var message: String? = null,
    var userID: String? = null,
    var isPeer: Boolean? = null,
    var timestamp: String? = null
)
{
    val id: String = UUID.randomUUID().toString()
}


//MARIO INDRA WIAJAYA

