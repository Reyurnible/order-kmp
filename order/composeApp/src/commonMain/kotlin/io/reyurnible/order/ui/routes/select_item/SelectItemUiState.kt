package io.reyurnible.order.ui.routes.select_item

// Visible screen state
data class SelectItemUiState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val selectItem: List<SelectItem>,
)