package io.reyurnible.order.ui.routes.start

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.reyurnible.order.ui.screens.OrderCompleteScreen
import io.reyurnible.order.ui.screens.OrderConfirmationScreen
import io.reyurnible.order.ui.screens.SelectItemScreen
import io.reyurnible.order.ui.screens.StartScreen

@Composable
fun StartRoute(
    viewModel: StartViewModel,
    onSubmitSuccess: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    when(uiState.value) {
        is StartUiState.Start -> {
            StartScreen(
                onStartButtonClicked = {
                    viewModel.onStartButtonClicked(onSubmitSuccess = onSubmitSuccess)
                }
            )
        }
    }
}