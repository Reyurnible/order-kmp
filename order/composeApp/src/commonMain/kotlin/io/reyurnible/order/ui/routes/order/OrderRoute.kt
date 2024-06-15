package io.reyurnible.order.ui.routes.order

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.reyurnible.order.OrderScreens
import io.reyurnible.order.ui.screens.OrderCompleteScreen
import io.reyurnible.order.ui.screens.OrderConfirmationScreen
import io.reyurnible.order.ui.screens.SelectItemScreen

@Composable
fun OrderRoute(
    orderViewModel: OrderViewModel,
) {
    val uiState = orderViewModel.uiState.collectAsStateWithLifecycle()

    when (val state = uiState.value) {
        is OrderUiState.OrderConfirmation -> {
            OrderConfirmationScreen(
                onOrderButtonClicked = {
                    // TODO Implement
                }
            )
        }
        is OrderUiState.OrderComplete -> {
            OrderCompleteScreen(
                onBackButtonClicked = {
                    // TODO Implement
                }
            )
        }
    }
}