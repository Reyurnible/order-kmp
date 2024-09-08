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
private sealed class OrderViewModelState(
    open val isLoading: Boolean = false,
    open val error: Throwable? = null,
) {
    data class OrderConfirmation(
        override val isLoading: Boolean = false,
        override val error: Throwable? = null
    ) : OrderViewModelState()

    data class OrderComplete(
        override val isLoading: Boolean = false,
        override val error: Throwable? = null
    ) : OrderViewModelState()

    fun toUiState(): OrderUiState =
        when (this) {
            is OrderConfirmation -> OrderUiState.OrderConfirmation(
                isLoading = isLoading,
                error = error
            )

            is OrderComplete -> OrderUiState.OrderComplete(
                isLoading = isLoading,
                error = error
            )
        }
}

class OrderViewModel : ViewModel() {
    private val viewModelState: MutableStateFlow<OrderViewModelState> =
        MutableStateFlow(OrderViewModelState.OrderConfirmation())

    val uiState: StateFlow<OrderUiState> =
        viewModelState
            .map { it.toUiState() }
            .stateIn(
                viewModelScope,
                SharingStarted.Eagerly,
                viewModelState.value.toUiState()
            )

    fun onOrderButtonClicked() {
        viewModelState.value = OrderViewModelState.OrderComplete()
    }

}