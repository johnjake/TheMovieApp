package com.themovieguide.org.features.details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.themovieguide.domain.model.television.DetailsTelevision
import com.themovieguide.domain.model.television.SeasonEntity
import com.themovieguide.domain.states.television.StateTelevisionSingle
import com.themovieguide.domain.utils.EMPTY
import com.themovieguide.org.features.utils.default_image
import com.themovieguide.org.features.utils.imageUrl
import com.themovieguide.org.ui.theme.AnimatedText
import com.themovieguide.org.ui.theme.gradientHome
import com.themovieguide.org.ui.theme.images.ArrowBackPainter
import com.themovieguide.org.ui.theme.images.HeartPainter
import com.themovieguide.org.ui.theme.modifier.modifierCardGenres
import com.themovieguide.org.ui.theme.modifier.modifierGenres
import com.themovieguide.org.ui.theme.modifier.modifierOverView
import com.themovieguide.org.ui.theme.modifier.modifierStarTickets
import com.themovieguide.org.ui.theme.modifier.modifierTitle
import com.themovieguide.org.ui.theme.rating.RatingStar
import com.themovieguide.org.ui.theme.text.TextMovieGenres
import com.themovieguide.org.ui.theme.text.TextMovieOverView
import com.themovieguide.org.ui.theme.text.TextMovieOverViewTitle
import timber.log.Timber

@Composable
fun TelevisionDetailsScreen(detailsState: StateTelevisionSingle?, navController: NavHostController,) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientHome()),
    ) {
        when (detailsState) {
            is StateTelevisionSingle.HideLoader -> { }
            is StateTelevisionSingle.ShowLoader -> { }
            is StateTelevisionSingle.OnFailed -> {
                Timber.e("Television Details Screen Error: ${detailsState.error}")
            }
            is StateTelevisionSingle.OnSuccess -> {
                val data = detailsState.data
                HeaderDetails(tv = data, seasonList = data.seasons, navController = navController)
            }
            else -> {
                Timber.e("Television Details: No initial data.")
            }
        }
    }
}

@Composable
private fun HeaderDetails(
    tv: DetailsTelevision,
    seasonList: List<SeasonEntity>?,
    navController: NavHostController,
) {
    val averageVote = tv.voteCount ?: 0
    val startRating = averageVote / 100
    val scrollState = rememberScrollState()
    val isTopIcon = remember { mutableStateOf(true) }
    val imgPath = tv.posterPath?.imageUrl() ?: default_image
    Column(modifier = Modifier.verticalScroll(scrollState)) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .background(gradientHome()),
        ) {
            Column {
                /** tool bar **/
                AnimatedVisibility(visible = isTopIcon.value) {
                    ImgBackGround(url = imgPath)
                    TopAppBar(
                        backgroundColor = Color.Transparent,
                        title = { Text(text = "") },
                        navigationIcon = {
                            ArrowBackPainter(navController)
                        },
                        actions = {
                            HeartPainter()
                        },
                        modifier = Modifier
                            .background(Color.Transparent)
                            .statusBarsPadding(),
                    )
                    Column(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .offset(y = 380.dp),
                    ) {
                        AnimatedText(
                            modifier = modifierTitle.align(Alignment.CenterHorizontally),
                            text = tv.name ?: EMPTY,
                            useAnimation = false,
                        )
                        RatingStar(
                            modifier = modifierStarTickets
                                .align(Alignment.CenterHorizontally),
                            rating = startRating.toFloat(),
                            spaceBetween = 2.dp,
                        )
                        LazyRow(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                            items(count = tv.genres?.size ?: 0) { index ->
                                Card(
                                    modifier = modifierCardGenres.align(Alignment.CenterHorizontally),
                                    content = {
                                        Column(modifier = Modifier.width(85.dp)) {
                                            TextMovieGenres(
                                                title = tv.genres?.get(index)?.name ?: EMPTY,
                                                modifier = modifierGenres.align(Alignment.CenterHorizontally),
                                            )
                                        }
                                    },
                                )
                            }
                        }
                        /** story line ***/
                        Spacer(modifier = Modifier.padding(8.dp))
                        TextMovieOverViewTitle(title = "Story Line", modifier = Modifier.padding(start = 4.dp))
                        TextMovieOverView(
                            title = tv.overview ?: EMPTY,
                            modifier = modifierOverView
                                .align(Alignment.CenterHorizontally)
                                .offset(y = 0.dp),
                        )
                        Spacer(modifier = Modifier.padding(8.dp))
                        TextMovieOverViewTitle(title = "Season", modifier = Modifier.padding(start = 4.dp))
                        /** season **/
                        TelevisionSeasonPager(seasonList = seasonList ?: emptyList())
                        Spacer(modifier = Modifier.padding(8.dp))
                        /****/
                        Spacer(modifier = Modifier.padding(8.dp))
                        TextMovieOverViewTitle(title = "ChatGPT", modifier = Modifier.padding(start = 4.dp))
                        TextMovieOverView(
                            title = tv.overview ?: EMPTY,
                            modifier = modifierOverView
                                .align(Alignment.CenterHorizontally)
                                .offset(y = 5.dp),
                        )
                    }
                }
                /** icon play video **/

                BoxWithConstraints(modifier = Modifier.offset(y = (-105).dp), contentAlignment = Alignment.TopCenter) {
                }
                Box(
                    modifier = Modifier
                        .height(330.dp)
                        .width(400.dp)
                        .systemBarsPadding(),

                )
            }
        }
    }
}
