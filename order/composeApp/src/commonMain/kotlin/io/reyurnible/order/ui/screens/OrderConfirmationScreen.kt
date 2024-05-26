package io.reyurnible.order.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import order.composeapp.generated.resources.Res
import order.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun OrderConfirmationScreen(
    onOrderButtonClicked: () -> Unit
) {
    Column {
        LazyColumn(
            modifier = Modifier.fillMaxSize().weight(1f),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            item {
                Text("注文内容確認")
                Spacer(modifier = Modifier.padding(8.dp))
                Text("合計金額: 10000円")
                Spacer(modifier = Modifier.padding(8.dp))
            }
            items(10) { index ->
                OrderDetailRow(index)
            }
        }
        Box(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp),
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                onClick = { onOrderButtonClicked() },
            ) {
                Text("注文する")
            }
        }
    }
}

@Composable
fun OrderDetailRow(
    index: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(Res.drawable.compose_multiplatform),
                contentDescription = "商品画像",
                modifier = Modifier.size(64.dp, 64.dp),
            )
            Column(modifier = Modifier.wrapContentSize().padding(8.dp)) {
                Text("商品名: Item $index")
                Spacer(modifier = Modifier.padding(8.dp))
                Text("価格: ${index * 1000}円")
                Text("個数: 1個")
            }
        }
    }
}

@Composable
@Preview
fun OrderDetailRowPreview() {
    OrderDetailRow(1)
}