package com.example.uthsmarttasks

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.uthsmarttasks.sealedModel.BottomNavItem
import com.example.uthsmarttasks.view.TaskDetailScreen
import com.example.uthsmarttasks.view.TaskListScreen
import com.example.uthsmarttasks.viewmodel.TaskViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartTasksApp()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SmartTasksApp() {
    val navController = rememberNavController()
    val viewModel = remember { TaskViewModel() }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(padding)
        ) {
            composable("home") {
                TaskListScreen(
                    viewModel = viewModel,
                    onTaskClick = { taskId ->
                        navController.navigate("detail/$taskId")
                    }
                )
            }
            composable("schedule") {
                // TODO: Home screen (optional)
                Text("Schedule screen")
            }
            composable("add") {
                // TODO: Add screen
                Text("Add new task")
            }
            composable("notify") {
                // TODO: Notification screen
                Text("Notifications")
            }
            composable("settings") {
                // TODO: Settings screen
                Text("Settings")
            }
            composable("detail/{id}") { backStackEntry ->
                val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
                if (id != null) {
                    TaskDetailScreen(
                        viewModel = viewModel,
                        taskId = id,
                        onBack = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}


@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Tasks,
        BottomNavItem.Add,
        BottomNavItem.Notifications,
        BottomNavItem.Settings
    )

    NavigationBar {
        val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = currentDestination == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                    }
                }
            )
        }
    }
}

