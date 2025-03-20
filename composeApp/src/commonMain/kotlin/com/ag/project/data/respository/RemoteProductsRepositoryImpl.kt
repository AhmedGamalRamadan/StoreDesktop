package com.ag.project.data.respository

import com.ag.project.domain.model.ProductsResponseItem
import com.ag.project.domain.repository.RemoteProductsRepository
import com.ag.project.util.Constants
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.ag.project.util.Result
import io.ktor.client.call.body

class RemoteProductsRepositoryImpl(
    private val httpClient: HttpClient
) : RemoteProductsRepository {

    override suspend fun getAllProducts(): Flow<Result<List<ProductsResponseItem>>> {
        return flow {
            try {
                val data: HttpResponse =
                    httpClient.get("${Constants.BASE_URL}${Constants.PRODUCTS_ENDPOINT}")
                val responseBody = data.bodyAsText()

                if (responseBody.isBlank()) {
//                    Log.e("home:", "Error: Empty response from server")
                    emit(Result.Error("Empty response from server"))
                    return@flow
                }
                emit(Result.Success(data.body()))

            } catch (e: Exception) {
                Result.Error(e.message.toString(), e)
            }
        }
    }

    override suspend fun getCategory(category: String): Flow<Result<List<ProductsResponseItem>>> {
        return flow {
            try {
                val data: HttpResponse =
                    httpClient.get(
                        "${Constants.BASE_URL}${Constants.PRODUCTS_ENDPOINT}/${Constants.CATEGORY}/$category"
                    )

                val responseBody = data.bodyAsText()

                if (responseBody.isBlank()) {
//                    Log.e("home:", "Error: Empty response from server")
                    emit(Result.Error("Empty response from server"))
                    return@flow
                }
                emit(Result.Success(data.body()))

            } catch (e: Exception) {
                Result.Error(e.message.toString(), e)
            }
        }
    }

    override suspend fun getProductById(productId: Int): Result<ProductsResponseItem> {
        return try {
            val data: HttpResponse = httpClient.get(
                "${Constants.BASE_URL}${Constants.PRODUCTS_ENDPOINT}/$productId"
            )

            if (data.bodyAsText().isBlank()) {
                return Result.Error("Empty response from server")
            }
            Result.Success(data.body())

        } catch (e: Exception) {
            Result.Error(e.message.toString(), e)
        }
    }
}