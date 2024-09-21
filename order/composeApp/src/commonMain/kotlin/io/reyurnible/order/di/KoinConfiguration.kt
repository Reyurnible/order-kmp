package io.reyurnible.order.di

import org.koin.core.KoinApplication

fun KoinApplication.koinConfiguration() {
    modules(endPointsModule)
    modules(viewModelModule)
}