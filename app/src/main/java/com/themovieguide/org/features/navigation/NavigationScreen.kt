package com.themovieguide.org.features.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Houseboat
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationScreen(val route: String, var icon: ImageVector, var title: String) {
    object MainScreen : NavigationScreen(
        route = "main_screen/{id}",
        icon = Icons.Default.Home,
        title = "Home",
    ) {
        fun createRoute(id: String): String {
            return "details_screen/$id"
        }
    }
    object NowShowing : NavigationScreen(
        route = "nowshowing_screen/{id}",
        icon = Icons.Default.Settings,
        title = "NowPlaying",
    ) {
        fun createRoute(id: String): String {
            return "details_screen/$id"
        }
    }

    object TelevisionScreen : NavigationScreen(
        route = "television_screen/{id}",
        icon = Icons.Default.Home,
        title = "Television",
    ) {
        fun createRoute(id: String): String {
            return "details_screen/$id"
        }
    }

    object Visited : NavigationScreen(
        route = "visited_screen",
        icon = Icons.Default.Houseboat,
        title = "Visited",
    )

    object DetailScreen : NavigationScreen(
        route = "details_screen/{id}",
        icon = Icons.Default.Houseboat,
        title = "Details",
    ) {
        fun createRoute(id: String): String {
            return "details_screen/$id"
        }
    }
    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { args ->
                append("/$args")
            }
        }
    }
}
