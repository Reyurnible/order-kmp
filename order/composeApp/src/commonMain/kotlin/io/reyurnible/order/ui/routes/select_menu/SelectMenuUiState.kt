package io.reyurnible.order.ui.routes.select_menu

// Visible screen state
data class SelectMenuUiState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val selectItem: List<SelectItem>,
)