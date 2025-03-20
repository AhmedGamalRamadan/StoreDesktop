package com.ag.project.presentation.di

import com.ag.project.data.respository.RemoteProductsRepositoryImpl
import com.ag.project.domain.repository.RemoteProductsRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
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
}