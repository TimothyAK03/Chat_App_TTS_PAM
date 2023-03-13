package edu.uksw.fti.pam.pamactivityintent.models

import com.google.gson.annotations.SerializedName

data class AccountModel(
    @SerializedName("username")
    var username: String,

    @SerializedName("password")
    var password: String,
)
