package io.reyurnible.order.ui.routes.order_confirmation

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun OrderConfirmationRoute(
    orderViewModel: OrderConfirmationViewModel = koinViewModel(),
    onOrderCompleted: () -> Unit,
) {
    val uiState = orderViewModel.uiState.collectAsStateWithLifecycle()

    OrderConfirmationScreen(
        uiState = uiState.value,
        onOrderButtonClicked = {
            orderViewModel.onOrderButtonClicked(onMoveToComplete = {
                onOrderCompleted()
            })
        }
    )

}