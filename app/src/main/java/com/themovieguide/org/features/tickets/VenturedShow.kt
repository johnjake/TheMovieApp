package com.themovieguide.org.features.tickets

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.elevatedCardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.themovieguide.data.utils.EMPTY
import com.themovieguide.domain.model.Movies
import com.themovieguide.org.R
import com.themovieguide.org.features.home.ShowingViewModel
import com.themovieguide.org.features.mapper.toMovieList
import com.themovieguide.org.features.navigation.NavigationScreen
import com.themovieguide.org.features.state.StateMovie
import com.themovieguide.org.features.utils.default_image
import com.themovieguide.org.features.utils.imageUrl
import com.themovieguide.org.features.utils.originalUrl
import com.themovieguide.org.features.utils.readJsonFromAssets
import com.themovieguide.org.ui.theme.LightDark220
import com.themovieguide.org.ui.theme.PinkColor700
import com.themovieguide.org.ui.theme.PurpleGrey40
import com.themovieguide.org.ui.theme.PurpleGrey50
import com.themovieguide.org.ui.theme.gradientHome
import com.themovieguide.org.ui.theme.modifier.modifierStarTickets
import com.themovieguide.org.ui.theme.rating.RatingStar
import timber.log.Timber
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

@Composable
fun VenturedShow(viewModel: ShowingViewModel, navController: NavHostController) {
    viewModel.getNowShowing()
    val model = viewModel.showShared.collectAsStateWithLifecycle(initialValue = null)
    val homeValue = model.value
    val showList: MutableList<Movies> = arrayListOf()
    when (homeValue) {
        is StateMovie.HideLoader -> {}
        is StateMovie.ShowLoader -> {}
        is StateMovie.OnSuccess -> showList.addAll(homeValue.data)
        is StateMovie.OnFailure -> Timber.e(" ${homeValue.error}")
        else -> { Timber.e("Home: no response in getNowShowing") }
    }
    MainMovies(showList.toList(), navController)
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun MainMovies(showList: List<Movies>, navController: NavHostController) {
    val pagerState = rememberPagerState()
    val background = remember { mutableStateOf(EMPTY) }
    Box {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradientHome()),
        ) {
            Image(
                modifier = Modifier
                    .background(Color.LightGray)
                    .padding(0.dp)
                    .fillMaxSize(),
                painter = rememberAsyncImagePainter(
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .placeholder(R.drawable.ic_default_wallpaper)
                        .data(background.value.originalUrl())
                        .size(coil.size.Size.ORIGINAL)
                        .build(),
                ),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
            )
        }
        Scaffold(
            backgroundColor = Color.Transparent, // Make the background transparent
            topBar = {
                TopAppBar(
                    backgroundColor = Color.Transparent,
                    title = { Text(text = "") },
                    navigationIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            "back_home",
                            tint = PinkColor700,
                            modifier = Modifier
                                .size(26.dp)
                                .padding(start = 4.dp)
                                .clickable(onClick = {
                                    navController.popBackStack()
                                }),
                        )
                    },
                    actions = {
                        IconButton(
                            onClick = { },
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_heart),
                                "back_home",
                                tint = PinkColor700,
                                modifier = Modifier
                                    .size(26.dp),
                            )
                        }
                    },
                )
            },
            content = {
                it
                HorizontalPager(
                    pageCount = showList.size,
                    pageSpacing = 0.dp,
                    beyondBoundsPageCount = 2,
                    state = pagerState,
                    modifier = Modifier.fillMaxSize(),
                ) { page ->
                    val data = showList[page]
                    background.value = showList[pagerState.currentPage].backdropPath ?: ""
                    Box(modifier = Modifier.fillMaxSize()) {
                        MovieInformationCard(
                            modifier = Modifier
                                .padding(start = 26.dp, end = 26.dp)
                                .align(Center),
                            pagerState = pagerState,
                            page = page,
                            movies = data,
                            navController = navController,
                        )
                    }
                }
            },
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun TheaterTicketsPreview() {
    val navController = rememberNavController()
    val dataList = readJsonFromAssets(LocalContext.current, "Movie.json")
    val list = dataList.toMovieList()

    MainMovies(showList = list.toMutableList(), navController = navController)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieInformationCard(
    pagerState: PagerState,
    page: Int,
    modifier: Modifier = Modifier,
    movies: Movies,
    navController: NavHostController,
) {
    val title = movies.title ?: EMPTY
    val averageVote = movies.voteCount ?: 0
    val aveStar = averageVote / 100
    Card(
        modifier = modifier
            .border(1.dp, Color.White, RoundedCornerShape(156.dp))
            .width(296.dp)
            .wrapContentHeight(),
        shape = RoundedCornerShape(156.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp,
        ),
        colors = elevatedCardColors(containerColor = PurpleGrey40),
    ) {
        Column(
            modifier = Modifier
                .padding(start = 32.dp, top = 32.dp, end = 32.dp, bottom = 0.dp),
        ) {
            val pageOffset = (
                (pagerState.currentPage - page) + pagerState
                    .currentPageOffsetFraction
                ).absoluteValue
            Box(
                modifier = Modifier
                    .height(390.dp)
                    .border(1.dp, LightDark220, RoundedCornerShape(140.dp))
                    .width(260.dp)
                    .clip(RoundedCornerShape(140.dp))
                    .padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 0.dp),
            ) {
                val movieTitle = movies.title ?: EMPTY
                val path = movies.posterPath ?: default_image
                val url = path.imageUrl()
                val painter = rememberAsyncImagePainter(
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .placeholder(R.drawable.ic_wall_screamvi)
                        .data(url)
                        .size(coil.size.Size.ORIGINAL)
                        .build(),
                )
                Image(
                    modifier = Modifier
                        .background(Color.LightGray)
                        .clickable(onClick = {
                            val movieId = movies.id ?: 0
                            navController.navigate(NavigationScreen.DetailScreen.createRoute(id = movieId.toString()))
                        })
                        .padding(0.dp)
                        .fillMaxSize()
                        .graphicsLayer {
                            // get a scale value between 1 and 1.75f, 1.75 will be when its resting,
                            // 1f is the smallest it'll be when not the focused page
                            val scale = lerp(1f, 1.75f, pageOffset)
                            // apply the scale equally to both X and Y, to not distort the image
                            scaleX *= scale
                            scaleY *= scale
                        },
                    painter = painter,
                    contentDescription = movieTitle,
                    contentScale = ContentScale.Crop,
                )
            }
            Box(
                modifier = Modifier
                    .padding(start = 0.dp, bottom = 0.dp)
                    .align(Alignment.CenterHorizontally),
            ) {
                Row {
                    HeartImage(
                        modifier = Modifier
                            .padding(start = 0.dp, bottom = 0.dp)
                            .offset(x = 200.dp, y = (-30).dp),
                    )
                }
                Row {
                    MovieDetails(title)
                }
            }
            DragToListen(pageOffset, aveStar)
        }
    }
}

@Composable
private fun DragToListen(pageOffset: Float, startRating: Int) {
    Box(
        modifier = Modifier
            .height(150.dp * (1 - pageOffset))
            .fillMaxWidth()
            .graphicsLayer {
                alpha = 1 - pageOffset
            },
    ) {
        Column(
            modifier = Modifier.align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            RatingStar(modifier = modifierStarTickets, rating = startRating.toFloat(), spaceBetween = 1.dp)
            Text("drag to book")
            Spacer(modifier = Modifier.size(4.dp))
            DragArea()
        }
    }
}

@Composable
private fun DragArea() {
    Box {
        Canvas(
            modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth()
                .height(60.dp)
                .clip(RoundedCornerShape(bottomEnd = 32.dp, bottomStart = 32.dp)),
        ) {
            val sizeGap = 16.dp.toPx()
            val numberDotsHorizontal = size.width / sizeGap + 1
            val numberDotsVertical = size.height / sizeGap + 1
            repeat(numberDotsHorizontal.roundToInt()) { horizontal ->
                repeat(numberDotsVertical.roundToInt()) { vertical ->
                    drawCircle(
                        Color.LightGray.copy(alpha = 0.5f),
                        radius = 2.dp.toPx
                            (),
                        center =
                        Offset(horizontal * sizeGap + sizeGap, vertical * sizeGap + sizeGap),
                    )
                }
            }
        }
        Icon(
            Icons.Rounded.ExpandMore,
            "down",
            modifier = Modifier
                .size(height = 24.dp, width = 48.dp)
                .align(Center),
        )
    }
}

@Composable
private fun TopBackHome() {
    Box(
        Modifier
            .padding(start = 8.dp, top = 6.dp)
            .size(32.dp)
            .background(color = Color.Transparent, shape = CircleShape),
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_back),
            "back_home",
            tint = PinkColor700,
            modifier = Modifier
                .size(26.dp),
        )
    }
}

@Composable
private fun MovieDetails(title: String) {
    Text(
        title,
        fontSize = 18.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp),
        textAlign = TextAlign.Center,
        color = Color.White,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
fun HeartImage(modifier: Modifier) {
    Box(modifier) {
        Box(
            Modifier
                .size(32.dp)
                .align(Center)
                .background(color = PurpleGrey50, shape = CircleShape),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_heart),
                "",
                tint = Color.Black,
                modifier = Modifier
                    .size(22.dp)
                    .align(Center),
            )
        }
    }
}
