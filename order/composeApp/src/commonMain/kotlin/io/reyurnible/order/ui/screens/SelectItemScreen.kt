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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.reyurnible.order.ui.components.OrderAppBar
import io.reyurnible.order.ui.routes.select_item.SelectItemUiState
import order.composeapp.generated.resources.Res
import order.composeapp.generated.resources.compose_multiplatform
import order.composeapp.generated.resources.select_item__title
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.jvm.JvmInline

@Composable
fun SelectItemScreen(
    uiState: SelectItemUiState,
    onOrderConfirmButtonClicked: () -> Unit,
    onItemClickPlusItem: (ItemId) -> Unit = {},
    onItemClickMinusItem: (ItemId) -> Unit = {},
    onItemClickAddToCart: (ItemId) -> Unit = {},
) {
    Scaffold(
        topBar = {
            OrderAppBar(
                currentScreenName = Res.string.select_item__title,
                canNavigateBack = false,

            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            LazyColumn(
                modifier = Modifier.fillMaxSize().weight(1f),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(uiState.selectItem) { item ->
                    ItemRow(
                        item = item,
                        onClickPlusItem = { onItemClickPlusItem(it) },
                        onClickMinusItem = { onItemClickMinusItem(it) },
                        onClickAddToCart = { onItemClickAddToCart(it) },
                    )
                }
            }
            Box(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp),
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    onClick = { onOrderConfirmButtonClicked() },
                ) {
                    Text("注文内容を確認する")
                }
            }
        }
    }
}

@JvmInline
value class ItemId(val id: Int)

data class SelectItem(
    val id: ItemId,
    val name: String,
    val price: Int,
    val imageUrl: String,
    val currentItemCount: Int,
)

@Composable
fun ItemRow(
    item: SelectItem,
    onClickPlusItem: (ItemId) -> Unit = {},
    onClickMinusItem: (ItemId) -> Unit = {},
    onClickAddToCart: (ItemId) -> Unit = {},
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
                Text("商品名: ${item.name}")
                Spacer(modifier = Modifier.padding(8.dp))
                Text("価格: ${item.price}円")
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Button(onClick = { onClickMinusItem.invoke(item.id) }) {
                        Text("-")
                    }
                    Text("${item.currentItemCount}", Modifier.padding(8.dp))
                    Button(onClick = { onClickPlusItem.invoke(item.id) }) {
                        Text("+")
                    }
                    Spacer(modifier = Modifier.fillMaxWidth().weight(1f))
                    Button(onClick = { onClickAddToCart.invoke(item.id) }) {
                        Text("カートに追加")
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun ItemRowPreview() {
    ItemRow(SelectItem(
        id = ItemId(1),
        name = "商品名",
        price = 1000,
        imageUrl = "",
        currentItemCount = 1
    ))
}