package edu.uksw.fti.pam.pamactivityintent.ui.screens

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import edu.uksw.fti.pam.pamactivityintent.CamActivity
import edu.uksw.fti.pam.pamactivityintent.models.*
import edu.uksw.fti.pam.pamactivityintent.ui.BottomNavItems
import edu.uksw.fti.pam.pamactivityintent.ui.ContactItems
import edu.uksw.fti.pam.pamactivityintent.ui.ProfileItems

@Composable
fun NavigationGraph(
    navController: NavHostController, navigateToProfile: (GroupsModel) -> Unit, vm : UserProfileViewModel
) {
    val lContext = LocalContext.current
    NavHost(
        navController = navController,
        startDestination = BottomNavItems.Home.screen_route

    ) {

        composable(BottomNavItems.Home.screen_route) {
            HomeField(navigateToProfile)
        }
        composable(route = BottomNavItems.Group.screen_route) {
            ContactsScreen(navController = navController)
        }
        composable(BottomNavItems.Profile.screen_route) {
            ProfileScreen(vm,navController = navController)
        }

        composable(
            route = ContactItems.AddScreen.route ,
        ) {
            AddContact(navController = navController)
        }

        composable(
            route = ContactItems.DetailScreen.route + "/{GroupName}/{GroupDescription}",
            arguments = listOf(
                navArgument("GroupName"){
                    type = NavType.StringType
                },
                navArgument("GroupDescription"){
                    type = NavType.StringType
                }
            )
        ) {entry ->
            DetailScreen(navController = navController, groupName = entry.arguments?.getString("GroupName")!!, groupDesc = entry.arguments?.getString("GroupDescription")!!, vm = GroupsViewModel())
        }
        composable(
            route = ContactItems.FavGroup.route ,
        ) {
            FavGroup(vm = FavGroupViewModel(),navigateToProfile)
        }


        composable(
            route = ContactItems.FavGroup.route ,
        ) {
            FavGroup(vm = FavGroupViewModel(),navigateToProfile)
        }

        composable(
            route = ProfileItems.UpdateScreen.route ,
        ) {
            updateScreen(navController = navController, vm)
        }
        composable(
            route = ProfileItems.Keep.route ,
        ) {
            KeepScreen()
        }

        composable(
            route = ProfileItems.AboutUs.route ,
        ) {
            AboutUsScreen(vm = AboutUsViewModel())
        }
    }
}



@Composable
fun BottomNavigation(
    navController: NavController
) {
    val items = listOf(
        BottomNavItems.Home,
        BottomNavItems.Group,
        BottomNavItems.Profile,

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
fun BottomNavigationMainScreenView( navigateToProfile: (GroupsModel) -> Unit){
    val navController = rememberNavController()
    val vm = UserProfileViewModel()
    Scaffold(
        bottomBar = {
            BottomNavigation(
                navController = navController,
            )
        }
    ) {
        NavigationGraph(navController = navController, navigateToProfile = navigateToProfile, vm = vm)
    }
}
