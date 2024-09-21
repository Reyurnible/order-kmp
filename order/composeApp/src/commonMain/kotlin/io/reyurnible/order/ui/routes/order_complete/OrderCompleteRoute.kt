package io.reyurnible.order.ui.routes.order_complete

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun OrderCompleteRoute(
    orderViewModel: OrderCompleteViewModel = koinViewModel(),
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