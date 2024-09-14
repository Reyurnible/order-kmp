package io.reyurnible.order.ui.routes.order_confirmation

// Visible screen state
data class OrderConfirmationUiState(
    val isLoading: Boolean = false,
    val error: Throwable? = null
)