package com.voxeldev.flexiworkdemo.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EventAvailable
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * @author nvoxel
 */
sealed class NavigationScreen(
    val route: String? = null,
    val routeWithArguments: String,
    val icon: ImageVector? = null,
    val title: String? = null,
) {

    data object Welcome : NavigationScreen(
        routeWithArguments = "welcome",
    )

    data object CreateAccount : NavigationScreen(
        routeWithArguments = "create_account",
    )

    data object Favorites : NavigationScreen(
        routeWithArguments = "favorites",
        icon = Icons.Default.StarBorder,
        title = "Избранное",
    )

    data object Home : NavigationScreen(
        routeWithArguments = "home",
        icon = Icons.Default.EventAvailable,
        title = "Аренда",
    )

    data object Search : NavigationScreen(
        routeWithArguments = "search",
        icon = Icons.Default.Search,
        title = "Поиск",
    )

    data object List : NavigationScreen(
        routeWithArguments = "list",
    )

    data object Details : NavigationScreen(
        route = "details",
        routeWithArguments = "details/{coworkingId}",
    )

    data object Map : NavigationScreen(
        routeWithArguments = "map",
        icon = Icons.Default.Map,
        title = "Карта",
    )

    data object Profile : NavigationScreen(
        routeWithArguments = "profile",
        icon = Icons.Outlined.Person,
        title = "Профиль",
    )

    data object Reservations : NavigationScreen(
        routeWithArguments = "reservations",

    )
}
