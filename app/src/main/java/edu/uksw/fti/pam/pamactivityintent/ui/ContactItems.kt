package edu.uksw.fti.pam.pamactivityintent.ui

sealed class ContactItems(val route:String){
    object ContactScreen : ContactItems("contact_screen")
    object AddScreen : ContactItems("add_screen")
    object DetailScreen : ContactItems("detail_screen")
    object FavGroup : ContactItems("FavGroup_screen")

    fun withArgs(vararg args: String): String{
        return buildString {
            append(route)
            args.forEach { arg->
                append("/$arg")
            }
        }
    }



}
