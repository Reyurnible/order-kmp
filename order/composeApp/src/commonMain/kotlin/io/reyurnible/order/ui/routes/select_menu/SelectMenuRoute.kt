package io.reyurnible.order.ui.routes.select_menu

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SelectItemRoute(
    orderViewModel: SelectMenuViewModel = koinViewModel(),
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