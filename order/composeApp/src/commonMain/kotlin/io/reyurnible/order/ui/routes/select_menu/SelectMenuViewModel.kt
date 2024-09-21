package io.reyurnible.order.ui.routes.select_item

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reyurnible.order.domain.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

// Inner state
private data class SelectItemViewModelState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val itemList: List<Item> = (1..10).map { index ->
        Item(
            id = ItemId(index),
            name = "商品名${index}",
            price = 1000,
            imageUrl = "",
        )
    },
    val selectItemCountMap: Map<ItemId, Int> = emptyMap(),
    val selectItemCount: Int = 1,
) {
    companion object {
        const val DEFAULT_ITEM_COUNT = 1
    }

    fun toUiState(): SelectItemUiState = SelectItemUiState(
        isLoading = isLoading,
        error = error,
        selectItem = itemList.map { item ->
            SelectItem(
                id = item.id,
                name = item.name,
                price = item.price,
                imageUrl = item.imageUrl,
                currentItemCount = selectItemCountMap[item.id] ?: DEFAULT_ITEM_COUNT,
            )
        }
    )
}

class SelectItemViewModel : ViewModel() {
    private val viewModelState = MutableStateFlow(SelectItemViewModelState())
    val uiState: StateFlow<SelectItemUiState> =
        viewModelState
            .map { it.toUiState() }
            .stateIn(
                viewModelScope,
                SharingStarted.Eagerly,
                viewModelState.value.toUiState()
            )

    fun onItemClickPlusItem(itemId: ItemId) {
        val currentCount =
            viewModelState.value.selectItemCountMap[itemId]
                ?: SelectItemViewModelState.DEFAULT_ITEM_COUNT
        viewModelState.value = viewModelState.value.copy(
            selectItemCountMap = viewModelState.value.selectItemCountMap.plus(itemId to currentCount + 1)
        )
    }

    fun onItemClickMinusItem(itemId: ItemId) {
        val currentCount =
            viewModelState.value.selectItemCountMap[itemId]
                ?: SelectItemViewModelState.DEFAULT_ITEM_COUNT
        if (currentCount <= SelectItemViewModelState.DEFAULT_ITEM_COUNT) {
            return
        }
        viewModelState.value = viewModelState.value.copy(
            selectItemCountMap = viewModelState.value.selectItemCountMap + (itemId to currentCount - 1)
        )
    }

    fun onItemClickAddToCart(itemId: ItemId) {
        val currentCount =
            viewModelState.value.selectItemCountMap[itemId]
                ?: SelectItemViewModelState.DEFAULT_ITEM_COUNT
        viewModelState.value = viewModelState.value.copy(
            selectItemCountMap = viewModelState.value.selectItemCountMap + (itemId to SelectItemViewModelState.DEFAULT_ITEM_COUNT)
        )
        // TODO カートに追加のリクエストを送る
    }

}

