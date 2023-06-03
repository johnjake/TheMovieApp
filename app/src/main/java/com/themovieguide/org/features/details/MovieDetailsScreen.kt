package com.themovieguide.org.features.details

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.themovieguide.data.mapper.toCastMovie
import com.themovieguide.data.mapper.toMovieEntity
import com.themovieguide.domain.model.Movie
import com.themovieguide.domain.model.cast.MainCast
import com.themovieguide.domain.states.cast.StateCast
import com.themovieguide.domain.states.details.StateDetails
import com.themovieguide.domain.utils.EMPTY
import com.themovieguide.org.features.utils.default_image
import com.themovieguide.org.features.utils.imageUrl
import com.themovieguide.org.features.utils.readCastFromAsset
import com.themovieguide.org.features.utils.readJsonFromAsset
import com.themovieguide.org.ui.theme.AnimatedText
import com.themovieguide.org.ui.theme.gradientHome
import com.themovieguide.org.ui.theme.images.ArrowBackPainter
import com.themovieguide.org.ui.theme.images.HeartPainter
import com.themovieguide.org.ui.theme.images.PlayVideo
import com.themovieguide.org.ui.theme.images.imagePainter
import com.themovieguide.org.ui.theme.modifier.modifierCardGenres
import com.themovieguide.org.ui.theme.modifier.modifierGenres
import com.themovieguide.org.ui.theme.modifier.modifierIcon
import com.themovieguide.org.ui.theme.modifier.modifierOverView
import com.themovieguide.org.ui.theme.modifier.modifierStarTickets
import com.themovieguide.org.ui.theme.modifier.modifierTitle
import com.themovieguide.org.ui.theme.rating.RatingStar
import com.themovieguide.org.ui.theme.text.TextMovieGenres
import com.themovieguide.org.ui.theme.text.TextMovieOverView
import com.themovieguide.org.ui.theme.text.TextMovieOverViewTitle
import com.themovieguide.org.ui.theme.text.TextMovieTitle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import timber.log.Timber

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ShowMovieDetails() {
    val navController = rememberNavController()
    val details = readJsonFromAsset(LocalContext.current, "Details.json")
    val castDetails = readCastFromAsset(LocalContext.current, "Cast.json")
    val movieDetails = details.toMovieEntity()
    val cast = castDetails.toCastMovie()
    HeaderDetails(movie = movieDetails, cast = cast, navController = navController)
}

@Composable
fun MovieDetailsScreen(
    viewModel: MovieDetailsViewModel,
    state: StateDetails?,
    cast: StateCast?,
    visible: MutableState<Boolean>,
    navController: NavHostController,
) {
    val context = LocalContext.current
    val expandSpec = tween<IntSize>(durationMillis = 500)
    val dataMovie = remember { mutableStateOf(Movie()) }
    val dataCast = remember { mutableStateOf(MainCast()) }
    when (state) {
        is StateDetails.ShowLoader -> {}
        is StateDetails.HideLoader -> {}
        is StateDetails.OnSuccess -> {
            dataMovie.value = state.data
        }
        is StateDetails.OnFailed -> Timber.e("Error: ${state.error}")
        else -> Timber.e("Error: Fetch Movie unknown error")
    }
    when (cast) {
        is StateCast.ShowLoader -> {}
        is StateCast.HideLoader -> {}
        is StateCast.OnSuccess -> { dataCast.value = cast.data }
        is StateCast.OnFailed -> Timber.e("Error: ### ${cast.error}")
        else -> Timber.e("Error: fetch movie cast unknown error")
    }
    AnimatedVisibility(
        visible = visible.value,
        enter = fadeIn() + expandIn(expandSpec, Center),
        exit = shrinkOut() + fadeOut(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradientHome()),
        ) {
            HeaderDetails(movie = dataMovie.value, cast = dataCast.value, navController = navController)
        }
    }

    LaunchedEffect(Unit) {
        delay(1000)
        withContext(Dispatchers.IO) {
            viewModel.insertLocalMovie(dataMovie.value)
        }
        Toast.makeText(context, "Successfully added ${dataMovie.value.title}", Toast.LENGTH_SHORT).show()
    }
}

@Composable
private fun HeaderDetails(
    movie: Movie,
    cast: MainCast,
    navController: NavHostController,
) {
    val averageVote = movie.voteCount ?: 0
    val startRating = averageVote / 100
    val scrollState = rememberScrollState()
    val isTopIcon = remember { mutableStateOf(true) }
    val isBottomIcon = remember { mutableStateOf(false) }
    val isVideoPlaying = remember { mutableStateOf(false) }
    val isIcon = remember { mutableStateOf(true) }
    val view = LocalView.current // Obtain the View associated with the current Compose node
    //  val window = (LocalContext.current as Activity).window
    val imgPath = movie.posterPath?.imageUrl() ?: default_image
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
                            .align(CenterHorizontally)
                            .offset(y = 380.dp),
                    ) {
                        AnimatedText(
                            modifier = modifierTitle.align(CenterHorizontally),
                            text = movie.title ?: EMPTY,
                            useAnimation = false,
                        )
                        RatingStar(
                            modifier = modifierStarTickets
                                .align(CenterHorizontally),
                            rating = startRating.toFloat(),
                            spaceBetween = 2.dp,
                        )
                        LazyRow(modifier = Modifier.align(CenterHorizontally)) {
                            items(count = movie.genres?.size ?: 0) { index ->
                                Card(
                                    modifier = modifierCardGenres.align(CenterHorizontally),
                                    content = {
                                        Column(modifier = Modifier.width(85.dp)) {
                                            TextMovieGenres(
                                                title = movie.genres?.get(index)?.name ?: EMPTY,
                                                modifier = modifierGenres.align(CenterHorizontally),
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
                            title = movie.overview ?: EMPTY,
                            modifier = modifierOverView
                                .align(CenterHorizontally)
                                .offset(y = 0.dp),
                        )
                        Spacer(modifier = Modifier.padding(8.dp))
                        TextMovieOverViewTitle(title = "Cast", modifier = Modifier.padding(start = 4.dp))
                        /** cast **/
                        MovieCastPager(casts = cast)
                        Spacer(modifier = Modifier.padding(8.dp))
                        /****/
                        Spacer(modifier = Modifier.padding(8.dp))
                        TextMovieOverViewTitle(title = "Overview", modifier = Modifier.padding(start = 4.dp))
                        TextMovieOverView(
                            title = movie.overview ?: EMPTY,
                            modifier = modifierOverView
                                .align(CenterHorizontally)
                                .offset(y = 5.dp),
                        )
                    }
                }
                /** icon play video **/

                BoxWithConstraints(modifier = Modifier.offset(y = (-105).dp), contentAlignment = Alignment.TopCenter) {
                    PlayVideo(
                        isVideoPlaying = isVideoPlaying,
                        isIconVisible = isIcon,
                        modifier = modifierIcon
                            .align(Center)
                            .offset(x = 160.dp, y = 0.dp),
                    )
                }
                Box(
                    modifier = Modifier
                        .height(330.dp)
                        .width(400.dp)
                        .systemBarsPadding(),
                    /* .onGloballyPositioned {
                         if (window != null) {
                             WindowCompat.setDecorFitsSystemWindows(window, false)
                         }
                         val controller = WindowInsetsControllerCompat(window, view)
                         controller.hide(WindowInsetsCompat.Type.systemBars())
                     } */
                ) {
                    /** play youtube **/
                    if (isVideoPlaying.value) {
                        if (movie.video?.results?.isNotEmpty() == true) {
                            val videoId = movie.video?.results?.first()?.key ?: EMPTY
                            if (videoId.isNotEmpty()) {
                                YouTubePlayer(videoId = videoId, isTopIcon = isTopIcon, isBottomIcon = isBottomIcon)
                            }
                        }
                    }
                }
                AnimatedVisibility(visible = isBottomIcon.value) {
                    Column(
                        modifier = Modifier
                            .align(CenterHorizontally)
                            .offset(y = (-5).dp),
                    ) {
                        TextMovieTitle(
                            title = movie.title ?: EMPTY,
                            modifier = modifierTitle
                                .align(CenterHorizontally),
                        )
                        RatingStar(
                            modifier = modifierStarTickets
                                .align(CenterHorizontally),
                            rating = startRating.toFloat(),
                            spaceBetween = 1.dp,
                        )
                        LazyRow(
                            modifier = Modifier
                                .align(CenterHorizontally)
                                .background(color = Color.Transparent),
                        ) {
                            items(count = movie.genres?.size ?: 0) { index ->
                                Card(
                                    modifier = modifierCardGenres
                                        .background(color = Color.Black.copy(alpha = 0f)),
                                    content = {
                                        Column(
                                            modifier = Modifier
                                                .width(95.dp)
                                                .background(color = Color.Transparent),
                                        ) {
                                            TextMovieGenres(
                                                title = movie.genres?.get(index)?.name ?: EMPTY,
                                                modifier = modifierGenres.align(CenterHorizontally),
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
                            title = movie.overview ?: EMPTY,
                            modifier = modifierOverView
                                .align(CenterHorizontally),
                        )
                        /** story line ***/
                        Spacer(modifier = Modifier.padding(8.dp))

                        TextMovieOverViewTitle(title = "Cast", modifier = Modifier.padding(start = 4.dp))
                        /** cast **/
                        MovieCastPager(casts = cast)
                        Spacer(modifier = Modifier.padding(8.dp))
                        /****/

                        TextMovieOverView(
                            title = movie.overview ?: EMPTY,
                            modifier = modifierOverView
                                .align(CenterHorizontally)
                                .offset(y = 5.dp),
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ImgBackGround(url: String) {
    Image(
        painter = imagePainter(param = url),
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .height(380.dp)
            .width(400.dp),
    )
}

@Composable
fun YouTubePlayer(
    videoId: String,
    isTopIcon: MutableState<Boolean>,
    isBottomIcon: MutableState<Boolean>,
) {
    AndroidView(
        factory = {
            isTopIcon.value = false
            isBottomIcon.value = true
            val view = YouTubePlayerView(it)
            view.addYouTubePlayerListener(
                object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer) {
                        super.onReady(youTubePlayer)
                        youTubePlayer.loadVideo(videoId, 0f)
                    }
                },
            )
            view
        },
        modifier = Modifier
            .background(color = Color.Black)
            .padding(top = 0.dp, bottom = 0.dp)
            .height(430.dp)
            .width(400.dp),
    )
}
