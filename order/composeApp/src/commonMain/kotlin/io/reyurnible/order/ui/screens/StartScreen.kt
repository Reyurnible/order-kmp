package io.reyurnible.order.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StartScreen(
    onStartButtonClicked: () -> Unit,
) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("ご来店ありがとうございます！")
        Spacer(modifier = Modifier.padding(24.dp))
        Button(onClick = {
            onStartButtonClicked()
        }) {
            Text("Start")
        }
    }
}