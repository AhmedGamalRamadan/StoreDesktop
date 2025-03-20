package com.ag.project.domain.repository

import com.ag.project.domain.model.ProductsResponseItem
import kotlinx.coroutines.flow.Flow
import com.ag.project.util.Result

interface RemoteProductsRepository {

    suspend fun getAllProducts(): Flow<Result<List<ProductsResponseItem>>>

    suspend fun getCategory(category:String): Flow<Result<List<ProductsResponseItem>>>

    suspend fun getProductById(productId:Int): Result<ProductsResponseItem>


}