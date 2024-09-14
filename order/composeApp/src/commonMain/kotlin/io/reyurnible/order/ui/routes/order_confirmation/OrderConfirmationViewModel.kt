package io.reyurnible.order.ui.routes.order_confirmation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

// Inner state
private data class OrderConfirmationViewModelState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
) {
    fun toUiState(): OrderConfirmationUiState =
        OrderConfirmationUiState(
            isLoading = isLoading,
            error = error
        )
}

class OrderConfirmationViewModel : ViewModel() {
    private val viewModelState: MutableStateFlow<OrderConfirmationViewModelState> =
        MutableStateFlow(OrderConfirmationViewModelState())

    val uiState: StateFlow<OrderConfirmationUiState> =
        viewModelState
            .map { it.toUiState() }
            .stateIn(
                viewModelScope,
                SharingStarted.Eagerly,
                viewModelState.value.toUiState()
            )

    fun onOrderButtonClicked(onMoveToComplete: () -> Unit) {
        onMoveToComplete.invoke()
    }

}