package io.reyurnible.order.ui.routes.select_menu

import io.reyurnible.order.MainDispatcherRule
import io.reyurnible.order.domain.model.MenuId
import io.reyurnible.order.domain.repository.MenuRepository
import io.reyurnible.order.domain.repository.MenuRepositoryImpl
import io.reyurnible.order.infra.ClientMockMenuEndpoints
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
class SelectMenuViewModelTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: SelectMenuViewModel

    @Before
    fun setUp() {
        val repository: MenuRepository = MenuRepositoryImpl(
            ClientMockMenuEndpoints()
        )
        viewModel = SelectMenuViewModel(repository)
    }

    @Test
    fun onItemClickPlusItem_increases_item_count() = runTest {

        val itemId = MenuId("demihamberg")
        viewModel.onItemClickPlusItem(itemId)

        val itemCount =
            viewModel.uiState.value.selectItem.find { it.id == itemId }?.currentItemCount
        assertEquals(2, itemCount)
    }

    @Test
    fun onItemClickMinusItem_decreases_item_count() = runTest {

        val itemId = MenuId("demihamberg")
        viewModel.onItemClickPlusItem(itemId)
        viewModel.onItemClickMinusItem(itemId)

        val itemCount =
            viewModel.uiState.value.selectItem.find { it.id == itemId }?.currentItemCount
        assertEquals(1, itemCount)
    }

    @Test
    fun onItemClickMinusItem_does_not_decrease_item_count_below_1() = runTest {

        val itemId = MenuId("ponzuhamberg")
        viewModel.onItemClickMinusItem(itemId)

        val itemCount =
            viewModel.uiState.value.selectItem.find { it.id == itemId }?.currentItemCount
        assertEquals(1, itemCount)
    }

    @Test
    fun onItemClickAddToCart_adds_item_to_cart() = runTest {

        val itemId = MenuId("ponzuhamberg")
        viewModel.onItemClickAddToCart(itemId)

        val itemCount =
            viewModel.uiState.value.selectItem.find { it.id == itemId }?.currentItemCount
        assertEquals(1, itemCount)
    }
}