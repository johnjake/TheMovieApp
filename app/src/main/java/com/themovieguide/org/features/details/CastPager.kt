package com.themovieguide.org.features.details

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.themovieguide.data.mapper.toCastMovie
import com.themovieguide.domain.model.cast.Cast
import com.themovieguide.domain.model.cast.MainCast
import com.themovieguide.domain.model.television.SeasonEntity
import com.themovieguide.domain.utils.EMPTY
import com.themovieguide.org.features.utils.default_profile
import com.themovieguide.org.features.utils.imageUrl
import com.themovieguide.org.features.utils.profileUrl
import com.themovieguide.org.features.utils.readCastFromAsset
import com.themovieguide.org.ui.theme.PinkColor700
import com.themovieguide.org.ui.theme.images.imageProfile
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun CastPreview() {
    val castDetails = readCastFromAsset(LocalContext.current, "Cast.json")
    val cast = castDetails.toCastMovie()
    MovieCastPager(casts = cast)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieCastPager(casts: MainCast) {
    val listCast = casts.cast ?: emptyList()
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray),
        )
        val pageSize = 125.dp
        HorizontalPager(
            state = pagerState,
            pageCount = listCast.size,
            pageSize = PageSize.Fixed(pageSize = pageSize),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 10.dp),
            contentPadding = PaddingValues(
                start = (maxWidth - pageSize) / 2,
                end = (maxWidth - pageSize) / 2,
            ),
            flingBehavior = PagerDefaults.flingBehavior(
                state = pagerState,
                pagerSnapDistance = PagerSnapDistance.atMost(30),
                snapAnimationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
            ),
        ) { page ->

            CircleFilterItem(
                filter = listCast[page],
                pagerState = pagerState,
                page = page,
                onPageSelected = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(page)
                    }
                },
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TelevisionSeasonPager(seasonList: List<SeasonEntity>) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray),
        )
        val pageSize = 125.dp
        HorizontalPager(
            state = pagerState,
            pageCount = seasonList.size,
            pageSize = PageSize.Fixed(pageSize = pageSize),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 10.dp),
            contentPadding = PaddingValues(
                start = (maxWidth - pageSize) / 2,
                end = (maxWidth - pageSize) / 2,
            ),
            flingBehavior = PagerDefaults.flingBehavior(
                state = pagerState,
                pagerSnapDistance = PagerSnapDistance.atMost(30),
                snapAnimationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
            ),
        ) { page ->

            CircleSeasonFilterItem(
                season = seasonList[page],
                pagerState = pagerState,
                page = page,
                onPageSelected = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(page)
                    }
                },
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CircleSeasonFilterItem(
    season: SeasonEntity,
    pagerState: PagerState,
    page: Int,
    onPageSelected: (SeasonEntity) -> Unit,
) {
    Column(
        modifier = Modifier
            .clickable {
                onPageSelected(season)
            }
            .graphicsLayer {
                // Calculate the absolute offset for the current page from the
                // scroll position. We use the absolute value which allows us to mirror
                // any effects for both directions
                val pageOffset = (
                    (pagerState.currentPage - page) + pagerState
                        .currentPageOffsetFraction
                    ).absoluteValue

                // We animate the scaleX + scaleY, between 85% and 100%
                lerp(
                    start = 0.85f,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f),
                ).also { scale ->
                    scaleX = scale
                    scaleY = scale
                }

                // We animate the alpha, between 50% and 100%
                alpha = lerp(
                    start = 0.25f,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f),
                )
            },
    ) {
        Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            if (pagerState.currentPage == page) {
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape),
                )
            }
            val pathUrl = season.poster_path
            val imgProfile = pathUrl?.imageUrl() ?: default_profile
            Image(
                painter = imageProfile(param = imgProfile),
                contentDescription = season.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .padding(1.dp)
                    .border(BorderStroke(2.dp, PinkColor700), CircleShape)
                    .clip(CircleShape),
            )
        }
        Text(
            season.name ?: EMPTY,
            maxLines = 1,
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CircleFilterItem(
    filter: Cast,
    pagerState: PagerState,
    page: Int,
    onPageSelected: (Cast) -> Unit,
) {
    Column(
        modifier = Modifier
            .clickable {
                onPageSelected(filter)
            }
            .graphicsLayer {
                // Calculate the absolute offset for the current page from the
                // scroll position. We use the absolute value which allows us to mirror
                // any effects for both directions
                val pageOffset = (
                    (pagerState.currentPage - page) + pagerState
                        .currentPageOffsetFraction
                    ).absoluteValue

                // We animate the scaleX + scaleY, between 85% and 100%
                lerp(
                    start = 0.85f,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f),
                ).also { scale ->
                    scaleX = scale
                    scaleY = scale
                }

                // We animate the alpha, between 50% and 100%
                alpha = lerp(
                    start = 0.25f,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f),
                )
            },
    ) {
        Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            if (pagerState.currentPage == page) {
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape),
                )
            }
            val pathUrl = filter.profilePath
            val imgProfile = pathUrl?.profileUrl() ?: default_profile
            Image(
                painter = imageProfile(param = imgProfile),
                contentDescription = filter.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .padding(1.dp)
                    .border(BorderStroke(2.dp, PinkColor700), CircleShape)
                    .clip(CircleShape),
            )
        }
        Text(
            filter.name ?: EMPTY,
            maxLines = 1,
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
