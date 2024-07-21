package io.reyurnible.order.ui.routes.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

// Visible screen state
sealed interface OrderUiState {
    data class OrderConfirmation(
        val isLoading: Boolean = false,
        val error: Throwable? = null
    ) : OrderUiState

    data class OrderComplete(
        val isLoading: Boolean = false,
        val error: Throwable? = null
    ) : OrderUiState

}

// Inner state
private data class OrderViewModelState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
) {
    fun toUiState(): OrderUiState = OrderUiState.OrderConfirmation(
        isLoading = isLoading,
        error = error
    )
}

class OrderViewModel : ViewModel() {
    private val viewModelState = MutableStateFlow(OrderViewModelState())
    val uiState: StateFlow<OrderUiState> =
        viewModelState
            .map { it.toUiState() }
            .stateIn(
                viewModelScope,
                SharingStarted.Eagerly,
                viewModelState.value.toUiState()
            )

}