package io.reyurnible.order

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import io.reyurnible.order.ui.OrderAppNavigation
import io.reyurnible.order.ui.OrderAppDestination
import io.reyurnible.order.ui.components.OrderAppBar


@Composable
fun OrderApp() {
    val navController: NavHostController = rememberNavController()
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = OrderAppDestination.valueOf(
        backStackEntry?.destination?.route ?: OrderAppDestination.Start.name
    )

    Scaffold(
        topBar = {
            OrderAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        OrderAppNavigation(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            navController = navController
        )
    }
}