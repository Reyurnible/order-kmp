package io.reyurnible.order.ui

import order.composeapp.generated.resources.Res
import order.composeapp.generated.resources.app_name
import order.composeapp.generated.resources.order_complete__title
import order.composeapp.generated.resources.order_confirmation__title
import order.composeapp.generated.resources.order_history__title
import order.composeapp.generated.resources.select_item__title
import org.jetbrains.compose.resources.StringResource

/**
 * Destination of OrderApp Navigation.
 */
enum class OrderAppDestination(val title: StringResource) {
    Start(title = Res.string.app_name),
    SelectItem(title = Res.string.select_item__title),
    OrderConfirmation(title = Res.string.order_confirmation__title),
    OrderComplete(title = Res.string.order_complete__title),
    OrderHistory(title = Res.string.order_history__title),
}