package com.ag.project.presentation.screen.details

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.ag.project.presentation.components.MediumSpacerHeight
import com.ag.project.presentation.components.RatingBar
import com.ag.project.util.Result
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(
    KoinExperimentalAPI::class,
    ExperimentalSharedTransitionApi::class
)
@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    productId: Int,
    navHostController: NavHostController,
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope
) {

    val viewModel: DetailsScreenViewModel = koinViewModel()
    val productDetails by viewModel.product.collectAsStateWithLifecycle()

    LaunchedEffect(productId) {
        productId?.let { id ->
            viewModel.getProductById(id)
        }
    }

    with(sharedTransitionScope) {
        when (productDetails) {

            is Result.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = (productDetails as Result.Error).message)
                }
            }

            Result.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is Result.Success -> {
                val product = (productDetails as Result.Success).data

                Column(
                    modifier = modifier
                        .fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "back",
                        modifier = Modifier
                            .clickable {
                                navHostController.navigateUp()
                            }
                    )

                    Column(
                        modifier = modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colors.background)
                            .padding(12.dp)
                            .verticalScroll(rememberScrollState())
                    ) {

                        MediumSpacerHeight()

                        CoilImage(
                            imageModel = { product.image },
                            modifier = modifier
                                .fillMaxWidth()
                                .height(230.dp)
                                .padding(8.dp)
                                .sharedElement(
                                    state = rememberSharedContentState("image/${product.image}"),
                                    animatedVisibilityScope = animatedVisibilityScope,
                                    boundsTransform = { _, _ ->
                                        tween(1000)
                                    }
                                ),
                            imageOptions = ImageOptions(
                                contentScale = ContentScale.Inside
                            )
                        )

                        MediumSpacerHeight()

                        Row(
                            modifier = modifier
                                .fillMaxWidth(),
                        ) {
                            product.title?.let {
                                Text(
                                    text = it,
                                    style = MaterialTheme.typography.h5,
                                    textAlign = TextAlign.Start,
                                    color = MaterialTheme.colors.primary,
                                    modifier = modifier
                                        .weight(1f)
                                        .sharedElement(
                                            state = rememberSharedContentState("title/${product.title}"),
                                            animatedVisibilityScope = animatedVisibilityScope,
                                            boundsTransform = { _, _ ->
                                                tween(1000)
                                            }
                                        )
                                )
                            }

                            product.price?.let {
                                Text(
                                    text = "$it $",
                                    textAlign = TextAlign.End,
                                    color = Color.Green,
                                    modifier = modifier
                                        .padding(4.dp)
                                        .sharedElement(
                                            state = rememberSharedContentState("price/${product.price}"),
                                            animatedVisibilityScope = animatedVisibilityScope,
                                            boundsTransform = { _, _ ->
                                                tween(1000)
                                            }
                                        )
                                )
                            }
                        }

                        MediumSpacerHeight()

                        Text(
                            text = "Description:",
                            style = MaterialTheme.typography.h4,
                            color = MaterialTheme.colors.primary,
                        )

                        product.description?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.h5,
                                color = MaterialTheme.colors.primary
                            )
                        }

                        MediumSpacerHeight()

                        Text(
                            text = "Rate",
                            style = MaterialTheme.typography.h2,
                            color = MaterialTheme.colors.primary
                        )

                        product.rating?.rate?.let {
                            RatingBar(
                                rating = it,
                                starsModifier = modifier
                                    .size(50.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
