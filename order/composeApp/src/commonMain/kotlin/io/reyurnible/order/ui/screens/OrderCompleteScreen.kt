package io.reyurnible.order.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.reyurnible.order.ui.components.OrderAppBar
import io.reyurnible.order.ui.routes.order_complete.OrderCompleteUiState
import order.composeapp.generated.resources.Res
import order.composeapp.generated.resources.order_complete__title
import order.composeapp.generated.resources.order_history__title

@Composable
fun OrderCompleteScreen(
    uiState: OrderCompleteUiState,
    onBackButtonClicked: () -> Unit,
) {
    Scaffold(
        topBar = {
            OrderAppBar(
                currentScreenName = Res.string.order_complete__title,
                canNavigateBack = false,
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("ご注文ありがとうございます！")
                Spacer(modifier = Modifier.padding(24.dp))
                Button(
                    modifier = Modifier.widthIn(max = 280.dp).fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    onClick = { onBackButtonClicked() },
                ) {
                    Text("注文画面に戻る")
                }
            }
        }
    }
}