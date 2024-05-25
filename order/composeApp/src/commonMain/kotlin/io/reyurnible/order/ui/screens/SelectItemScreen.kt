package io.reyurnible.order.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import order.composeapp.generated.resources.Res
import order.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SelectItemScreen(

) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(10) { index ->
            ItemRow(index)
        }
    }
}

@Composable
fun ItemRow(
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
            }
        }
    }
}

@Composable
@Preview
fun ItemRowPreview() {
    ItemRow(1)
}