package io.reyurnible.order.di

import io.reyurnible.order.domain.repository.MenuRepository
import io.reyurnible.order.domain.repository.MenuRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<MenuRepository> { MenuRepositoryImpl(get()) }
}