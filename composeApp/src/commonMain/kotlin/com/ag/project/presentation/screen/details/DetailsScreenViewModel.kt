package com.ag.project.presentation.screen.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ag.project.domain.model.ProductsResponseItem
import com.ag.project.domain.repository.RemoteProductsRepository
import com.ag.project.util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class DetailsScreenViewModel(
    private val remoteProductsRepository: RemoteProductsRepository
) : ViewModel() {


    private val _product = MutableStateFlow<Result<ProductsResponseItem>>(Result.Loading)
    val product = _product.asStateFlow()

    fun getProductById(id: Int) {
        viewModelScope.launch {
            try {
                when (val productDetails = remoteProductsRepository.getProductById(id)) {
                    is Result.Error -> {
                        _product.emit(
                            Result.Error(
                                productDetails.message,
                                productDetails.exception
                            )
                        )

                    }

                    Result.Loading -> {
                        _product.emit(Result.Loading)
                    }

                    is Result.Success -> {
                        _product.emit(Result.Success(productDetails.data))
                    }
                }

            } catch (e: Exception) {
                _product.emit(Result.Error(e.message.toString(), e))
            }
        }
    }
}