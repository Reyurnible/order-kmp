package io.reyurnible.order.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.reyurnible.order.ui.routes.order_confirmation.OrderConfirmationRoute
import io.reyurnible.order.ui.routes.order_complete.OrderCompleteRoute
import io.reyurnible.order.ui.routes.order_history.OrderHistoryRoute
import io.reyurnible.order.ui.routes.select_menu.SelectItemRoute
import io.reyurnible.order.ui.routes.start.StartRoute

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
            StartRoute(
                onSubmitSuccess = {
                    navController.navigate(OrderAppDestination.SelectItem.name)
                }
            )
        }
        // オーダー選択画面
        composable(route = OrderAppDestination.SelectItem.name) {
            SelectItemRoute(
                onOrderConfirmButtonClicked = {
                    navController.navigate(OrderAppDestination.OrderConfirmation.name)
                }
            )
        }
        composable(route = OrderAppDestination.OrderConfirmation.name) {
            OrderConfirmationRoute(
                onOrderCompleted = {
                    navController.navigate(OrderAppDestination.OrderComplete.name)
                }
            )
        }
        composable(route = OrderAppDestination.OrderComplete.name) {
            OrderCompleteRoute(
                onOrderCompletedBack = {
                    navController.navigate(OrderAppDestination.SelectItem.name)
                }
            )
        }
        composable(route = OrderAppDestination.OrderHistory.name) {
            OrderHistoryRoute(
                onCheckoutComplete = {
                    navController.navigate(OrderAppDestination.Start.name)
                }
            )
        }
    }
}