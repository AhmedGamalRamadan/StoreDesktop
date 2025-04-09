package com.ag.project.presentation.di

import com.ag.project.data.respository.RemoteProductsRepositoryImpl
import com.ag.project.domain.repository.RemoteProductsRepository
import com.ag.project.presentation.screen.details.DetailsScreenViewModel
import com.ag.project.presentation.screen.home.HomeScreenViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module

val newsModule = module {

    single {

        HttpClient {
            install(ContentNegotiation) {
                json(
                    json = Json { ignoreUnknownKeys = true }
                )
            }
        }
    }

    single<RemoteProductsRepository>{
        RemoteProductsRepositoryImpl(get())
    }


    viewModel{
        HomeScreenViewModel(get())
    }

    viewModel{
        DetailsScreenViewModel(get())
    }
}