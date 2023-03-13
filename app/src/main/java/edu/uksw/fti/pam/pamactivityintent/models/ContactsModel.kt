package edu.uksw.fti.pam.pamactivityintent.models

import com.google.gson.annotations.SerializedName

data class ContactsModel(
    @SerializedName("namaDepan")
    var namaDepan: String,

    @SerializedName("namaBelakang")
    var namaBelakang: String,

    @SerializedName("noTelepon")
    var noTelepon: String,

    @SerializedName("fotoProfil")
    var fotoProfil: String,
)
