package io.reyurnible.order.ui.routes.order_history

// Visible screen state
data class OrderHistoryUiState(
    val isLoading: Boolean = false,
    val error: Throwable? = null
)