package io.reyurnible.order.ui.routes.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reyurnible.api.endpoints.users.UserEndPoints
import io.reyurnible.order.infra.ClientUserEndPoints
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

// Inner state
private data class StartViewModelState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
) {
    fun toUiState(): StartUiState = StartUiState(
        isLoading = isLoading,
        error = error
    )
}

class StartViewModel(
    private val userEndPoints: UserEndPoints,
) : ViewModel() {
    private val viewModelState = MutableStateFlow(StartViewModelState())
    val uiState: StateFlow<StartUiState> =
        viewModelState
            .map { it.toUiState() }
            .stateIn(
                viewModelScope,
                SharingStarted.Eagerly,
                viewModelState.value.toUiState()
            )

    init {
        viewModelScope.launch {
            runCatching {
                userEndPoints.getList()
            }.onSuccess {
                print(it.toString())
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    fun onStartButtonClicked(onSubmitSuccess: () -> Unit) {
        // TODO Repositoryの呼び出しを実装
        onSubmitSuccess()
    }
}