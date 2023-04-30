package edu.uksw.fti.pam.pamactivityintent.ui

sealed class ContactItems(val route:String){
    object ContactScreen : ContactItems("contact_screen")
    object AddScreen : ContactItems("add_screen")


}
