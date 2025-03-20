package com.ag.project.presentation.screen.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ag.project.domain.repository.RemoteProductsRepository
import com.ag.project.presentation.components.ProductItem
import com.ag.project.util.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {

    val repo = koinInject<RemoteProductsRepository>()


    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 200.dp),
        modifier = Modifier
            .fillMaxSize()
    ) {

        CoroutineScope(Dispatchers.IO).launch {
            repo.getAllProducts().collect { data ->

                when (data) {
                    is Result.Error -> {}
                    Result.Loading -> {}
                    is Result.Success -> {
                        items(data.data){product->

                            ProductItem(
                                product = product,
                                navHostController = navHostController
                            )
                        }
                    }
                }
            }
        }
    }

}
