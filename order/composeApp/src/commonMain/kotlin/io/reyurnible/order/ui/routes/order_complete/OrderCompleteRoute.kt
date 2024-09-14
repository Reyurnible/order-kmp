package io.reyurnible.order.ui.routes.order_complete

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.reyurnible.order.ui.screens.OrderCompleteScreen

@Composable
fun OrderCompleteRoute(
    orderViewModel: OrderCompleteViewModel,
    onOrderCompletedBack: () -> Unit,
) {
    val uiState = orderViewModel.uiState.collectAsStateWithLifecycle()

    OrderCompleteScreen(
        uiState = uiState.value,
        onBackButtonClicked = {
            // TODO Implement
            onOrderCompletedBack()
        }
    )

}