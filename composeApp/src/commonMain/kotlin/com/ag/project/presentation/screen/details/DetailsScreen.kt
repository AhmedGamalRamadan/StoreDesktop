package com.ag.project.presentation.screen.details

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.coil3.CoilImage

//@Composable
//fun DetailsScreen(
//    modifier: Modifier = Modifier,
//    productId: Int,
//    animatedVisibilityScope: AnimatedVisibilityScope
//) {
//
//
//    LaunchedEffect(productId) {
//        productId?.let { id ->
//            viewModel.getProductById(id)
//        }
//    }
//
//    when (productState) {
//        is Result.Error -> {
//            Box(
//                modifier = Modifier.fillMaxSize(),
//                contentAlignment = Alignment.Center
//            ) {
//                Text(text = (productState as Result.Error).message)
//            }
//        }
//
//        Result.Loading -> {
//            Box(
//                modifier = Modifier.fillMaxSize(),
//                contentAlignment = Alignment.Center
//            ) {
//                CircularProgressIndicator()
//            }
//        }
//
//        is Result.Success -> {
//
//            val product = (productState as Result.Success).data
//
//            Column(
//                modifier = modifier
//                    .fillMaxSize()
//                    .background(MaterialTheme.colorScheme.background)
//                    .padding(12.dp)
//            ) {
//                MediumSpacerHeight()
//
//               CoilImage(
//                    imageModel = product.image,
//                    modifier = modifier
//                        .fillMaxWidth()
//                        .height(230.dp)
//                        .padding(8.dp)
//
//                )
//
//
//                Row(
//                    modifier = modifier
//                        .fillMaxWidth(),
//                ) {
//                    product.title?.let {
//                        Text(
//                            text = it,
//                            textAlign = TextAlign.Start,
//                            modifier = modifier
//                                .weight(1f)
//
//                        )
//                    }
//
//                    product.price?.let {
//                        Text(
//                            text = "$it $",
//                            textAlign = TextAlign.End,
//                            color = Color.Green,
//                            modifier = modifier
//                                .padding(4.dp)
//
//                        )
//                    }
//                }
//
//
//                Text(
//                    text = "Description:",
//                )
//
//                product.description?.let {
//                    Text(
//                        text = it,
//                    )
//                }
//
//
//                Text(
//                    text = "Rate",
//                )
//
//
//            }
//        }
//    }
//}