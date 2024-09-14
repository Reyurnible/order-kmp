package io.reyurnible.order.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.reyurnible.order.ui.routes.order_confirmation.OrderConfirmationRoute
import io.reyurnible.order.ui.routes.order_confirmation.OrderConfirmationViewModel
import io.reyurnible.order.ui.routes.order_complete.OrderCompleteRoute
import io.reyurnible.order.ui.routes.order_complete.OrderCompleteViewModel
import io.reyurnible.order.ui.routes.order_history.OrderHistoryRoute
import io.reyurnible.order.ui.routes.order_history.OrderHistoryViewModel
import io.reyurnible.order.ui.routes.select_item.SelectItemRoute
import io.reyurnible.order.ui.routes.select_item.SelectItemViewModel
import io.reyurnible.order.ui.routes.start.StartRoute
import io.reyurnible.order.ui.routes.start.StartViewModel

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
            val viewModel: StartViewModel = viewModel { StartViewModel() }
            StartRoute(
                viewModel = viewModel,
                onSubmitSuccess = {
                    navController.navigate(OrderAppDestination.SelectItem.name)
                }
            )
        }
        // オーダー選択画面
        composable(route = OrderAppDestination.SelectItem.name) {
            val viewModel: SelectItemViewModel = viewModel { SelectItemViewModel() }
            SelectItemRoute(
                viewModel,
                onOrderConfirmButtonClicked = {
                    navController.navigate(OrderAppDestination.OrderConfirmation.name)
                }
            )
        }
        composable(route = OrderAppDestination.OrderConfirmation.name) {
            val viewModel: OrderConfirmationViewModel = viewModel { OrderConfirmationViewModel() }
            OrderConfirmationRoute(
                viewModel,
                onOrderCompleted = {
                    navController.navigate(OrderAppDestination.OrderComplete.name)
                }
            )
        }
        composable(route = OrderAppDestination.OrderComplete.name) {
            val viewModel: OrderCompleteViewModel = viewModel { OrderCompleteViewModel() }
            OrderCompleteRoute(
                viewModel,
                onOrderCompletedBack = {
                    navController.navigate(OrderAppDestination.SelectItem.name)
                }
            )
        }
        composable(route = OrderAppDestination.OrderHistory.name) {
            val viewModel: OrderHistoryViewModel = viewModel { OrderHistoryViewModel() }
            OrderHistoryRoute(
                viewModel,
                onCheckoutComplete = {
                    navController.navigate(OrderAppDestination.Start.name)
                }
            )
        }
    }
}