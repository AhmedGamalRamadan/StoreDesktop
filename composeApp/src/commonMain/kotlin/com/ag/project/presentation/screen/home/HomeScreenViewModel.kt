package com.ag.project.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ag.project.domain.model.ProductsResponseItem
import com.ag.project.domain.repository.RemoteProductsRepository
import com.ag.project.util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class HomeScreenViewModel(
    private val remoteRepository: RemoteProductsRepository
) : ViewModel() {


    private val _productsState =
        MutableStateFlow<Result<List<ProductsResponseItem>>>(Result.Loading)
    val productsState = _productsState.asStateFlow()


    fun getAllProducts() = viewModelScope.launch {
        remoteRepository.getAllProducts().collect { data ->
            when (data) {
                is Result.Error -> _productsState.emit(
                    Result.Error(
                        data.message,
                        data.exception
                    )
                )

                Result.Loading -> _productsState.emit(Result.Loading)
                is Result.Success -> _productsState.emit(Result.Success(data.data))
            }
        }
    }

    fun getCategory(category: String) = viewModelScope.launch {
        remoteRepository.getCategory(category).collect { data ->
            when (data) {
                is Result.Error -> _productsState.emit(
                    Result.Error(
                        data.message,
                        data.exception
                    )
                )

                Result.Loading -> _productsState.emit(Result.Loading)
                is Result.Success -> _productsState.emit(Result.Success(data.data))
            }
        }
    }
}