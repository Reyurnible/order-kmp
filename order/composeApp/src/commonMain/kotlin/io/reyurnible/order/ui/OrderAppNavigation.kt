package io.reyurnible.order.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.reyurnible.order.ui.screens.OrderCompleteScreen
import io.reyurnible.order.ui.screens.OrderConfirmationScreen
import io.reyurnible.order.ui.screens.OrderHistoryScreen
import io.reyurnible.order.ui.screens.SelectItemScreen
import io.reyurnible.order.ui.screens.StartScreen

@Composable
fun OrderAppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = OrderAppDestination.Start.name,
        modifier = modifier,
    ) {
        // TODO : Navigationを後々ネストする
        // https://developer.android.com/guide/navigation/design/nested-graphs?hl=ja

        // スタート画面
        composable(route = OrderAppDestination.Start.name) {
            StartScreen(
                onStartButtonClicked = {
                    navController.navigate(OrderAppDestination.SelectItem.name)
                }
            )
        }
        // オーダー選択画面
        composable(route = OrderAppDestination.SelectItem.name) {
            SelectItemScreen(
                onOrderConfirmButtonClicked = {
                    navController.navigate(OrderAppDestination.OrderConfirmation.name)
                }
            )
        }
        composable(route = OrderAppDestination.OrderConfirmation.name) {
            OrderConfirmationScreen(
                onOrderButtonClicked = {
                    navController.navigate(OrderAppDestination.OrderComplete.name)
                }
            )
        }
        composable(route = OrderAppDestination.OrderComplete.name) {
            OrderCompleteScreen(
                onBackButtonClicked = {
                    navController.navigate(OrderAppDestination.Start.name)
                }
            )
        }
        composable(route = OrderAppDestination.OrderHistory.name) {
            OrderHistoryScreen(
                onCheckoutButtonClicked = {
                    navController.navigate(OrderAppDestination.Start.name)
                }
            )
        }
    }
}