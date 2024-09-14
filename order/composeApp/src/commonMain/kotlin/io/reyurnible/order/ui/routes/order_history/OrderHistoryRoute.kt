package io.reyurnible.order.ui.routes.order_history

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun OrderHistoryRoute(
    orderViewModel: OrderHistoryViewModel,
    onCheckoutComplete: () -> Unit,
) {
    val uiState = orderViewModel.uiState.collectAsStateWithLifecycle()

    OrderHistoryScreen(
        uiState = uiState.value,
        onCheckoutButtonClicked = {
            // TODO
        }
    )

}