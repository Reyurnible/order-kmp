package io.reyurnible.order.ui.routes.order_history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

// Visible screen state
data class OrderHistoryUiState(
    val isLoading: Boolean = false,
    val error: Throwable? = null
)

// Inner state
private data class OrderHistoryViewModelState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
) {
    fun toUiState(): OrderHistoryUiState =
        OrderHistoryUiState(
            isLoading = isLoading,
            error = error
        )
}

class OrderHistoryViewModel : ViewModel() {
    private val viewModelState: MutableStateFlow<OrderHistoryViewModelState> =
        MutableStateFlow(OrderHistoryViewModelState())

    val uiState: StateFlow<OrderHistoryUiState> =
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