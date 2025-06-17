package com.example.uthsmarttasks.sealedModel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Home : BottomNavItem("home", Icons.Default.Home, "Home")
    object Tasks : BottomNavItem("schedule", Icons.Default.List, "Tasks")
    object Add : BottomNavItem("add", Icons.Default.AddCircle, "Add")
    object Notifications : BottomNavItem("notify", Icons.Default.Notifications, "Notify")
    object Settings : BottomNavItem("settings", Icons.Default.Settings, "Settings")
}
