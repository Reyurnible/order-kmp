package io.reyurnible.order.ui.routes.order_complete

// Visible screen state
data class OrderCompleteUiState(
    val isLoading: Boolean = false,
    val error: Throwable? = null
)