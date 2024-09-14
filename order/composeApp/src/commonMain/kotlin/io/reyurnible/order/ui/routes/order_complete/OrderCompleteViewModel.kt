package io.reyurnible.order.ui.routes.order_complete

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

// Inner state
private data class OrderCompleteViewModelState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
) {
    fun toUiState(): OrderCompleteUiState =
        OrderCompleteUiState(
            isLoading = isLoading,
            error = error
        )
}

class OrderCompleteViewModel : ViewModel() {
    private val viewModelState: MutableStateFlow<OrderCompleteViewModelState> =
        MutableStateFlow(OrderCompleteViewModelState())

    val uiState: StateFlow<OrderCompleteUiState> =
        viewModelState
            .map { it.toUiState() }
            .stateIn(
                viewModelScope,
                SharingStarted.Eagerly,
                viewModelState.value.toUiState()
            )
}