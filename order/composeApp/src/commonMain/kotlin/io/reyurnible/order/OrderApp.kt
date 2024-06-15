package io.reyurnible.order

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import io.reyurnible.order.ui.routes.order.OrderViewModel
import io.reyurnible.order.ui.screens.OrderCompleteScreen
import io.reyurnible.order.ui.screens.OrderConfirmationScreen
import io.reyurnible.order.ui.screens.OrderHistoryScreen
import io.reyurnible.order.ui.screens.SelectItemScreen
import io.reyurnible.order.ui.screens.StartScreen
import order.composeapp.generated.resources.Res
import order.composeapp.generated.resources.common__back_button
import org.jetbrains.compose.resources.stringResource


@Composable
fun OrderApp(
    viewModel: OrderViewModel = viewModel { OrderViewModel() },
    navController: NavHostController = rememberNavController()
) {
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = OrderScreens.valueOf(
        backStackEntry?.destination?.route ?: OrderScreens.Start.name
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
        NavHost(
            navController = navController,
            startDestination = OrderScreens.Start.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // TODO : Navigationを後々ネストする
            // https://developer.android.com/guide/navigation/design/nested-graphs?hl=ja

            // スタート画面
            composable(route = OrderScreens.Start.name) {
                StartScreen(
                    onStartButtonClicked = {
                        navController.navigate(OrderScreens.SelectItem.name)
                    }
                )
            }
            // オーダー選択画面
            composable(route = OrderScreens.SelectItem.name) {
                SelectItemScreen(
                    onOrderConfirmButtonClicked = {
                        navController.navigate(OrderScreens.OrderConfirmation.name)
                    }
                )
            }
            composable(route = OrderScreens.OrderConfirmation.name) {
                OrderConfirmationScreen(
                    onOrderButtonClicked = {
                        navController.navigate(OrderScreens.OrderComplete.name)
                    }
                )
            }
            composable(route = OrderScreens.OrderComplete.name) {
                OrderCompleteScreen(
                    onBackButtonClicked = {
                        navController.navigate(OrderScreens.Start.name)
                    }
                )
            }
            composable(route = OrderScreens.OrderHistory.name) {
                OrderHistoryScreen(
                    onCheckoutButtonClicked = {
                        navController.navigate(OrderScreens.Start.name)
                    }
                )
            }
        }
    }
}

@Composable
fun OrderAppBar(
    currentScreen: OrderScreens,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
//        colors = TopAppBarDefaults.mediumTopAppBarColors(
//            containerColor = MaterialTheme.colorScheme.primaryContainer
//        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(Res.string.common__back_button)
                    )
                }
            }
        }
    )
}