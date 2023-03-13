package edu.uksw.fti.pam.pamactivityintent.models

import com.google.gson.annotations.SerializedName

data class TodosModel(


    @SerializedName("id")
    var id: Int,

    @SerializedName("title")
    var title: String,

    @SerializedName("image")
    var image: String,

    @SerializedName("chat")
    var chat: String,


    )
