@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.ag.project.presentation.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ag.project.domain.model.ProductsResponseItem
import com.ag.project.util.Screen
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage

@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    product: ProductsResponseItem,
    navHostController: NavHostController,
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope
) {

    with(sharedTransitionScope) {
        Card(
            modifier = modifier
                .wrapContentHeight()
                .width(200.dp)
                .padding(8.dp)
                .clickable {
                    product.id?.let { id ->
                        navHostController.navigate(Screen.Details(id))
                    }
                },
            shape = RoundedCornerShape(7.dp),
            backgroundColor = Color.White,
            elevation = 10.dp

        ) {

            Column(
                modifier = modifier
                    .wrapContentHeight()
                    .padding(5.dp)
            ) {

                CoilImage(
                    imageModel = { product.image },
                    modifier = modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .sharedElement(
                            state = rememberSharedContentState("image/${product.image}"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = { _, _ ->
                                tween(1000)
                            }
                        ),
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.Inside
                    ),
                    loading = {
                        Box(
                            modifier = Modifier.matchParentSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()

                        }
                    },
                    failure = {
                        Text("Image Not Supported")
                    },
                )

                Spacer(Modifier.height(7.dp))

                product.title?.let {
                    Text(
                        text = it,
                        fontSize = 14.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = modifier
                            .sharedElement(
                                state = rememberSharedContentState("title/${product.title}"),
                                animatedVisibilityScope = animatedVisibilityScope,
                                boundsTransform = { _, _ ->
                                    tween(1000)
                                }
                            )

                    )
                }

                Spacer(Modifier.height(7.dp))

                product.price?.let {
                    Text(
                        text = "$it $",
                        fontSize = 18.sp,
                        color = Color.Green,
                        modifier = modifier
                            .sharedElement(
                                state = rememberSharedContentState("price/${product.price}"),
                                animatedVisibilityScope = animatedVisibilityScope,
                                boundsTransform = { _, _ ->
                                    tween(1000)
                                }
                            )

                    )
                }

                product.rating?.let {
                    RatingBar(
                        rating = it.rate!!
                    )
                }
            }

        }
    }
}