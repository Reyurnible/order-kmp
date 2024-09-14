package io.reyurnible.order.ui.routes.select_item

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.reyurnible.order.ui.screens.OrderCompleteScreen
import io.reyurnible.order.ui.screens.OrderConfirmationScreen
import io.reyurnible.order.ui.screens.SelectItemScreen

@Composable
fun SelectItemRoute(
    orderViewModel: SelectItemViewModel,
    onOrderConfirmButtonClicked: () -> Unit,
) {
    val uiState = orderViewModel.uiState.collectAsStateWithLifecycle()

    SelectItemScreen(
        uiState = uiState.value,
        onOrderConfirmButtonClicked = {
            onOrderConfirmButtonClicked()
        },
        onItemClickPlusItem = { itemId ->
            orderViewModel.onItemClickPlusItem(itemId)
        },
        onItemClickMinusItem = { itemId ->
            orderViewModel.onItemClickMinusItem(itemId)
        },
        onItemClickAddToCart = { itemId ->
            orderViewModel.onItemClickAddToCart(itemId)
        },
    )
}