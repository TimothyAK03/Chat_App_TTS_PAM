package edu.uksw.fti.pam.pamactivityintent.ui

import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import edu.uksw.fti.pam.pamactivityintent.R

sealed class BottomNavItems (
    val title: String,
    val icon: ImageVector,
    val screen_route: String

    ) {
    object Home: BottomNavItems("Home", Icons.Default.Home, "home")
    object Group: BottomNavItems("Group", Icons.Default.AccountCircle, "group")
    object Profile: BottomNavItems("Profile", Icons.Default.Person, "profile")
    object Camera: BottomNavItems("Camera", Icons.Default.AddCircle, "camera")
}