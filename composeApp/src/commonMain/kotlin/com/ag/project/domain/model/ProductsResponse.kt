package com.ag.project.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ProductsResponse(
    val productsResponseItem: List<ProductsResponseItem>
)