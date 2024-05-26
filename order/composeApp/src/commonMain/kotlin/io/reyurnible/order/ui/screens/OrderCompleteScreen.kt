package io.reyurnible.order.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OrderCompleteScreen(
    onBackButtonClicked: () -> Unit,
) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(16.dp),
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