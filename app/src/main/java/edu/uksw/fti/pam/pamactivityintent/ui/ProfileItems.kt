package edu.uksw.fti.pam.pamactivityintent.ui

sealed class ProfileItems(val route:String){
    object UpdateScreen : ProfileItems("update_screen")
    object Keep : ProfileItems("keep_screen")
    object AboutUs : ProfileItems("AboutUs_screen")


}
