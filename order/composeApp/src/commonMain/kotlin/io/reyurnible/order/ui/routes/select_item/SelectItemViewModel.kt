package io.reyurnible.order.ui.routes.select_item

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

// Visible screen state
sealed interface SelectItemUiState {
    data class SelectItem(
        val isLoading: Boolean = false,
        val error: Throwable? = null
    ) : SelectItemUiState
}

// Inner state
private data class SelectItemViewModelState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
) {
    fun toUiState(): SelectItemUiState = SelectItemUiState.SelectItem(
        isLoading = isLoading,
        error = error
    )
}

class SelectItemViewModel : ViewModel() {
    private val viewModelState = MutableStateFlow(SelectItemViewModelState())
    val uiState: StateFlow<SelectItemUiState> =
        viewModelState
            .map { it.toUiState() }
            .stateIn(
                viewModelScope,
                SharingStarted.Eagerly,
                viewModelState.value.toUiState()
            )

}