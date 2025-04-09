package com.ag.project.presentation.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.ag.project.domain.repository.RemoteProductsRepository
import com.ag.project.presentation.components.CategoryTabRow
import com.ag.project.presentation.components.ProductItem
import com.ag.project.util.Result
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {

    val repo = koinInject<RemoteProductsRepository>()

    val viewModel: HomeScreenViewModel = koinViewModel()
    val products by viewModel.productsState.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()

    val categories = rememberSaveable {
        listOf(
            "All",
            "electronics",
            "jewelery",
            "men's clothing",
            "women's clothing"
        )
    }
    val pagerState = rememberPagerState {
        categories.size
    }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            if (page == 0) viewModel.getAllProducts()
            else viewModel.getCategory(categories[page])
        }
    }


    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
    ) { innerPadding ->

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            CategoryTabRow(
                pagerState = pagerState,
                categories = categories,
                onTabSelected = { index ->
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )

            when (products) {
                is Result.Error -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = (products as Result.Error).message)
                    }
                }

                Result.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is Result.Success -> {
                    val productsResponse = (products as Result.Success).data

                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        LazyVerticalGrid(
                            columns = GridCells.Adaptive(200.dp),
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            items(productsResponse) { product ->
                                ProductItem(
                                    product = product,
                                    navHostController = navHostController,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
