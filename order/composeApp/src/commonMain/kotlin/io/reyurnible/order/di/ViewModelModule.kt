package io.reyurnible.order.di

import io.reyurnible.order.ui.routes.order_complete.OrderCompleteViewModel
import io.reyurnible.order.ui.routes.order_confirmation.OrderConfirmationViewModel
import io.reyurnible.order.ui.routes.order_history.OrderHistoryViewModel
import io.reyurnible.order.ui.routes.select_item.SelectItemViewModel
import io.reyurnible.order.ui.routes.start.StartViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { StartViewModel(get()) }
    viewModel { SelectItemViewModel() }
    viewModel { OrderConfirmationViewModel() }
    viewModel { OrderCompleteViewModel() }
    viewModel { OrderHistoryViewModel() }
}