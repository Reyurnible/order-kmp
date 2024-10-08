package io.reyurnible.order.ui.routes.select_menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reyurnible.order.domain.model.Menu
import io.reyurnible.order.domain.model.MenuId
import io.reyurnible.order.domain.repository.MenuRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

// Inner state
private data class SelectMenuViewModelState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val menuList: List<Menu> = emptyList(),
    val selectItemCountMap: Map<MenuId, Int> = emptyMap(),
    val selectItemCount: Int = 1,
) {
    companion object {
        const val DEFAULT_ITEM_COUNT = 1
    }

    fun toUiState(): SelectMenuUiState = SelectMenuUiState(
        isLoading = isLoading,
        error = error,
        selectItem = menuList.map { item ->
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

class SelectMenuViewModel(
    private val menuRepository: MenuRepository,
) : ViewModel() {
    private val viewModelState = MutableStateFlow(SelectMenuViewModelState())
    val uiState: StateFlow<SelectMenuUiState> =
        viewModelState
            .map { it.toUiState() }
            .stateIn(
                viewModelScope,
                SharingStarted.Eagerly,
                viewModelState.value.toUiState()
            )

    init {
        viewModelScope.launch {
            runCatching {
                menuRepository.getList()
            }.onSuccess {
                viewModelState.value = viewModelState.value.copy(menuList = it)
            }.onFailure {

            }
        }
    }

    fun onItemClickPlusItem(itemId: MenuId) {
        val currentCount =
            viewModelState.value.selectItemCountMap[itemId]
                ?: SelectMenuViewModelState.DEFAULT_ITEM_COUNT
        viewModelState.value = viewModelState.value.copy(
            selectItemCountMap = viewModelState.value.selectItemCountMap.plus(itemId to currentCount + 1)
        )
    }

    fun onItemClickMinusItem(itemId: MenuId) {
        val currentCount =
            viewModelState.value.selectItemCountMap[itemId]
                ?: SelectMenuViewModelState.DEFAULT_ITEM_COUNT
        if (currentCount <= SelectMenuViewModelState.DEFAULT_ITEM_COUNT) {
            return
        }
        viewModelState.value = viewModelState.value.copy(
            selectItemCountMap = viewModelState.value.selectItemCountMap + (itemId to currentCount - 1)
        )
    }

    fun onItemClickAddToCart(itemId: MenuId) {
        val currentCount =
            viewModelState.value.selectItemCountMap[itemId]
                ?: SelectMenuViewModelState.DEFAULT_ITEM_COUNT
        viewModelState.value = viewModelState.value.copy(
            selectItemCountMap = viewModelState.value.selectItemCountMap + (itemId to SelectMenuViewModelState.DEFAULT_ITEM_COUNT)
        )
        // TODO カートに追加のリクエストを送る
    }

}

