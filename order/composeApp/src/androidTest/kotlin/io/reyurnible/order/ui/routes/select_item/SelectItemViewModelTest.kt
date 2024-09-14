package io.reyurnible.order.ui.routes.select_item

import io.reyurnible.order.MainDispatcherRule
import io.reyurnible.order.ui.screens.ItemId
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.Test
import kotlin.test.assertEquals

// Fixme Run on commonTest
@RunWith(JUnit4::class)
class SelectItemViewModelTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: SelectItemViewModel

    @Before
    fun setUp() {
        viewModel = SelectItemViewModel()
    }

    @Test
    fun onItemClickPlusItem_increases_item_count() = runTest {

        val itemId = ItemId(1)
        viewModel.onItemClickPlusItem(itemId)

        val itemCount =
            viewModel.uiState.value.selectItem.find { it.id == itemId }?.currentItemCount
        assertEquals(2, itemCount)
    }

    @Test
    fun onItemClickMinusItem_decreases_item_count() = runTest {

        val itemId = ItemId(1)
        viewModel.onItemClickPlusItem(itemId)
        viewModel.onItemClickMinusItem(itemId)

        val itemCount =
            viewModel.uiState.value.selectItem.find { it.id == itemId }?.currentItemCount
        assertEquals(1, itemCount)
    }

    @Test
    fun onItemClickMinusItem_does_not_decrease_item_count_below_1() = runTest {

        val itemId = ItemId(1)
        viewModel.onItemClickMinusItem(itemId)

        val itemCount =
            viewModel.uiState.value.selectItem.find { it.id == itemId }?.currentItemCount
        assertEquals(1, itemCount)
    }

    @Test
    fun onItemClickAddToCart_adds_item_to_cart() = runTest {

        val itemId = ItemId(1)
        viewModel.onItemClickAddToCart(itemId)

        val itemCount =
            viewModel.uiState.value.selectItem.find { it.id == itemId }?.currentItemCount
        assertEquals(1, itemCount)
    }
}