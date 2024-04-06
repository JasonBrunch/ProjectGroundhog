package com.example.groundhogdayplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.groundhogdayplanner.ui.theme.GroundhogDayPlannerTheme
import androidx.compose.material3.Scaffold
import androidx.navigation.NavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GroundhogDayPlannerTheme {
                // Initialize the navigation controller
                val navController = rememberNavController()

                Scaffold(
                    bottomBar = { MyNavigationBar(navController) } // Navigation Bar
                ) { innerPadding -> // Use the PaddingValues
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding), // Apply padding here
                        color = MaterialTheme.colorScheme.background
                    ) {
                        // Set up the NavHost for navigation between composables
                        NavHost(navController = navController, startDestination = "home") {
                            composable("home") { HomeScreen() }
                            composable("habits") { HabitsScreen() }
                            composable("flyout") { FlyOut() }
                            // Other destinations as needed
                        }
                    }
                }
            }
        }
    }
}




@Composable
fun HomeScreen() {
    Text(text = "Home Screen")
}

@Composable
fun HabitsScreen() {
    Text(text = "Habits Screen")
}
@Composable
fun FlyOut(){
    //
}

@Composable
fun MyNavigationBar(navController: NavController) {
    var selectedItem = remember { mutableStateOf(0) }
    val items = listOf("Home", "Habits") // Adjusted for Home and Habits only

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    when (item) {
                        "Home" -> Icon(Icons.Filled.Home, contentDescription = item)
                        "Habits" -> Icon(Icons.Filled.List, contentDescription = item)
                        else -> Icon(Icons.Filled.Favorite, contentDescription = item)
                    }
                },
                label = { Text(item) },
                selected = selectedItem.value == index,
                onClick = {
                    selectedItem.value = index
                    when (item) {
                        "Home" -> navController.navigate("home")
                        "Habits" -> navController.navigate("habits")
                    }
                }
            )
        }
    }
}