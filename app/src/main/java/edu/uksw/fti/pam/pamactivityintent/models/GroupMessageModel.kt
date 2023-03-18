package edu.uksw.fti.pam.pamactivityintent.models

import com.google.gson.annotations.SerializedName

data class GroupMessageModel(

    @SerializedName("title")
    var title: String,

    @SerializedName("message")
    var message: String,

    @SerializedName("isPeer")
    var isPeer: Boolean,

)
