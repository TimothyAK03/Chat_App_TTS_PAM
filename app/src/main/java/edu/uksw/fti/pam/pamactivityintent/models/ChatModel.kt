package edu.uksw.fti.pam.pamactivityintent.models

data class ChatModel(
    var firstName: String? = null,
    var lastName: String? = null,
    var number: String? = null,
    var img: String? = null,
    var message: String? = null,
    var isPeer: Boolean
)
