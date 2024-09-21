package io.reyurnible.order.ui.routes.start

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun StartRoute(
    viewModel: StartViewModel = koinViewModel(),
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