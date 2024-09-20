package io.reyurnible.order.di

import org.koin.core.KoinApplication

fun KoinApplication.koinConfiguration() {
    // your configuration & modules here
    modules(endPointsModule)
}