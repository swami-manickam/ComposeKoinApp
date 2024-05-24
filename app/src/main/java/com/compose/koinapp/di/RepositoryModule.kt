package com.compose.koinapp.di

import com.compose.koinapp.domain.Repository
import org.koin.dsl.module


val repositoryModule = module {
    factory { Repository(get()) }
}