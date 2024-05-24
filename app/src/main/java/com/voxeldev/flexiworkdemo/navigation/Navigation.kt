package com.voxeldev.flexiworkdemo.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.voxeldev.flexiworkdemo.screens.CreateAccountScreen
import com.voxeldev.flexiworkdemo.screens.DEFAULT_COWORKING_ID
import com.voxeldev.flexiworkdemo.screens.DetailsScreen
import com.voxeldev.flexiworkdemo.screens.FavoritesScreen
import com.voxeldev.flexiworkdemo.screens.HomeScreen
import com.voxeldev.flexiworkdemo.screens.ListScreen
import com.voxeldev.flexiworkdemo.screens.MapScreen
import com.voxeldev.flexiworkdemo.screens.ProfileScreen
import com.voxeldev.flexiworkdemo.screens.SearchScreen
import com.voxeldev.flexiworkdemo.screens.WelcomeScreen

/**
 * @author nvoxel
 */
@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
    startDestination: NavigationScreen = NavigationScreen.Welcome,
) {
    val bottomNavItems = listOf(
        NavigationScreen.Search,
        NavigationScreen.Favorites,
        NavigationScreen.Home,
        NavigationScreen.Map,
        NavigationScreen.Profile,
    )

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    var bottomBarVisible by rememberSaveable { mutableStateOf(false) }
    bottomBarVisible = isBottomNavActive(currentDestination = currentDestination)

    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = bottomBarVisible,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it }),
            ) {
                NavigationBar {
                    bottomNavItems.forEach { navigationScreen ->
                        NavigationBarItem(
                            icon = {
                                navigationScreen.icon?.let {
                                    Icon(imageVector = it, contentDescription = navigationScreen.title)
                                }
                            },
                            label = {
                                navigationScreen.title?.let {
                                    Text(text = navigationScreen.title)
                                }
                            },
                            selected = currentDestination
                                ?.hierarchy
                                ?.any { it.route?.contains(navigationScreen.routeWithArguments) ?: false } ?: false,
                            onClick = {
                                navHostController.navigate(route = navigationScreen.routeWithArguments) {
                                    popUpTo(id = navHostController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        CompositionLocalProvider(LocalNavController provides navHostController) {
            NavHost(
                modifier = Modifier
                    .padding(paddingValues = paddingValues),
                navController = navHostController,
                startDestination = startDestination.routeWithArguments,
            ) {
                composable(route = NavigationScreen.Welcome.routeWithArguments) {
                    WelcomeScreen()
                }

                composable(route = NavigationScreen.CreateAccount.routeWithArguments) {
                    CreateAccountScreen()
                }

                composable(route = NavigationScreen.Favorites.routeWithArguments) {
                    FavoritesScreen()
                }

                composable(route = NavigationScreen.Home.routeWithArguments) {
                    HomeScreen()
                }

                composable(route = NavigationScreen.Search.routeWithArguments) {
                    SearchScreen()
                }

                composable(route = NavigationScreen.List.routeWithArguments) {
                    ListScreen()
                }

                composable(
                    route = NavigationScreen.Details.routeWithArguments,
                    arguments = listOf(navArgument(name = "coworkingId") { type = NavType.IntType }),
                ) {
                    DetailsScreen(it.arguments?.getInt("coworkingId") ?: DEFAULT_COWORKING_ID)
                }

                composable(route = NavigationScreen.Map.routeWithArguments) {
                    MapScreen()
                }

                composable(route = NavigationScreen.Profile.routeWithArguments) {
                    ProfileScreen()
                }
            }
        }
    }
}

private fun isBottomNavActive(currentDestination: NavDestination?) =
    currentDestination?.route?.contains(NavigationScreen.Welcome.routeWithArguments) == false &&
            currentDestination.route?.contains(NavigationScreen.CreateAccount.routeWithArguments) == false

val LocalNavController = compositionLocalOf<NavController> { error("NavController not found!") }
