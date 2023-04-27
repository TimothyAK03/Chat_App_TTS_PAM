package edu.uksw.fti.pam.pamactivityintent.ui.screens

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import edu.uksw.fti.pam.pamactivityintent.R
import edu.uksw.fti.pam.pamactivityintent.models.ContactModel
import edu.uksw.fti.pam.pamactivityintent.models.GroupViewModel
import edu.uksw.fti.pam.pamactivityintent.models.TodosModel
import edu.uksw.fti.pam.pamactivityintent.models.TodosViewModel
import edu.uksw.fti.pam.pamactivityintent.ui.BottomNavItems
import edu.uksw.fti.pam.pamactivityintent.ui.ContactItems

@Composable
fun NavigationGraph(
    navController: NavHostController, navigateToProfile: (TodosModel) -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItems.Home.screen_route

    ) {

        composable(BottomNavItems.Home.screen_route) {
            HomeField(navigateToProfile)
        }
        composable(route = BottomNavItems.Contact.screen_route) {
            ContactsScreen(navController = navController)
        }
        composable(BottomNavItems.Profile.screen_route) {
            ProfileScreen()
        }
        composable(BottomNavItems.Camera.screen_route) {
            CamButton()
        }

        composable(
            route = ContactItems.AddScreen.route ,
        ) {
            AddContact(navController = navController)
        }

    }
}



@Composable
fun BottomNavigation(
    navController: NavController
) {
    val items = listOf(
        BottomNavItems.Home,
        BottomNavItems.Contact,
        BottomNavItems.Profile,
        BottomNavItems.Camera,
    )
    Column(
//        modifier = Modifier
//            .border(
//                width = 1.0.dp,
//                color = Color.Blue,
//                shape = CircleShape
//            )
    ) {
        androidx.compose.material.BottomNavigation(
            backgroundColor = colorResource(id = androidx.appcompat.R.color.material_grey_300),
            contentColor = Color.Black
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            items.forEach { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = "${item.title} icon"
                        )
                    },
                    label = {
                        Text(
                            text = item.title,
                            fontSize = 9.sp
                        )
                    },
                    selectedContentColor = Color.Black,
                    unselectedContentColor = Color.Black.copy(0.4f),
                    alwaysShowLabel = true,
                    selected = currentRoute == item.screen_route,
                    onClick = {
                        navController.navigate(item.screen_route) {
                            navController.graph.startDestinationRoute?.let { screen_route ->
                                popUpTo(screen_route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BottomNavigationMainScreenView( navigateToProfile: (TodosModel) -> Unit){
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation(
                navController = navController,
            )
        }
    ) {
        NavigationGraph(navController = navController, navigateToProfile = navigateToProfile)
    }
}
