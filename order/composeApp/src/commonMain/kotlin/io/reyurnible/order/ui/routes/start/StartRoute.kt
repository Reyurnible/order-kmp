package io.reyurnible.order.ui.routes.start

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun StartRoute(
    viewModel: StartViewModel,
    onSubmitSuccess: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    StartScreen(
        uiState = uiState.value,
        onStartButtonClicked = {
            viewModel.onStartButtonClicked(onSubmitSuccess = onSubmitSuccess)
        }
    )
}