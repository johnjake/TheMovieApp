package com.themovieguide.org.features.television

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.themovieguide.domain.model.television.LiveVision
import com.themovieguide.domain.utils.EMPTY
import com.themovieguide.org.R
import com.themovieguide.org.features.home.DotsIndicator
import com.themovieguide.org.features.navigation.NavigationScreen
import com.themovieguide.org.features.searchtv.SearchTelevisionViewModel
import com.themovieguide.org.features.state.StateLiveTelevision
import com.themovieguide.org.features.utils.default_image
import com.themovieguide.org.features.utils.imageUrl
import com.themovieguide.org.features.utils.toSearchField
import com.themovieguide.org.ui.theme.LightDark220
import com.themovieguide.org.ui.theme.PinkColor700
import com.themovieguide.org.ui.theme.gradientHome
import com.themovieguide.org.ui.theme.images.AsyncImageLoad
import com.themovieguide.org.ui.theme.images.AsyncItemImg
import com.themovieguide.org.ui.theme.images.AsyncSearchImageLoad
import com.themovieguide.org.ui.theme.modifier.modifierCardView
import com.themovieguide.org.ui.theme.modifier.modifierPagingItemCardView
import com.themovieguide.org.ui.theme.modifier.modifierRowIndicator
import com.themovieguide.org.ui.theme.modifier.modifierSearch
import com.themovieguide.org.ui.theme.modifier.modifierSearchBox
import com.themovieguide.org.ui.theme.modifier.modifierSearchCardView
import com.themovieguide.org.ui.theme.modifier.modifierSearchResult
import com.themovieguide.org.ui.theme.modifier.modifierStarTop
import com.themovieguide.org.ui.theme.modifier.modifierTitleBox
import com.themovieguide.org.ui.theme.rating.RatingStar
import com.themovieguide.org.ui.theme.text.DateRelease
import com.themovieguide.org.ui.theme.text.DateReleaseSearch
import com.themovieguide.org.ui.theme.text.DisplayText
import com.themovieguide.org.ui.theme.text.MoviePagingTitle
import com.themovieguide.org.ui.theme.text.MovieRating
import com.themovieguide.org.ui.theme.text.MovieTitle
import com.themovieguide.org.ui.theme.text.ResultText
import com.themovieguide.org.ui.theme.text.TelevisionSelection
import kotlinx.coroutines.delay
import timber.log.Timber

@Composable
fun TelevisionScreen(
    ratedState: StateLiveTelevision<List<LiveVision>>?,
    todayState: StateLiveTelevision<List<LiveVision>>?,
    discoverState: StateLiveTelevision<List<LiveVision>>?,
    trendingState: StateLiveTelevision<List<LiveVision>>?,
    searchModel: SearchTelevisionViewModel,
    navController: NavHostController,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                gradientHome(),
            ),
    ) {
        val mutableRated: MutableList<LiveVision> = arrayListOf()
        val mutableToday: MutableList<LiveVision> = arrayListOf()
        val mutableCover: MutableList<LiveVision> = arrayListOf()
        val mutableTrend: MutableList<LiveVision> = arrayListOf()

        /** extract data from state **/
        when (ratedState) {
            is StateLiveTelevision.ShowLoader -> { }
            is StateLiveTelevision.HideLoader -> { }
            is StateLiveTelevision.OnFailure -> Timber.e("RatedTelevision Error: ${ratedState.error}")
            is StateLiveTelevision.OnSuccess -> {
                mutableRated.clear()
                mutableRated.addAll(ratedState.data)
            }
            else -> Timber.e("RatedTelevision: No initial data")
        }

        when (todayState) {
            is StateLiveTelevision.ShowLoader -> { }
            is StateLiveTelevision.HideLoader -> { }
            is StateLiveTelevision.OnFailure -> Timber.e("TodayAirShow Error: ${todayState.error}")
            is StateLiveTelevision.OnSuccess -> {
                mutableToday.clear()
                mutableToday.addAll(todayState.data)
            }
            else -> Timber.e("TodayAirShow: No initial data")
        }

        when (discoverState) {
            is StateLiveTelevision.ShowLoader -> { }
            is StateLiveTelevision.HideLoader -> { }
            is StateLiveTelevision.OnFailure -> Timber.e("DiscoverState Error: ${discoverState.error}")
            is StateLiveTelevision.OnSuccess -> {
                mutableCover.clear()
                mutableCover.addAll(discoverState.data)
            }
            else -> Timber.e("DiscoverState: No initial data")
        }

        when (trendingState) {
            is StateLiveTelevision.ShowLoader -> { }
            is StateLiveTelevision.HideLoader -> { }
            is StateLiveTelevision.OnFailure -> Timber.e("TrendingState Error: ${trendingState.error}")
            is StateLiveTelevision.OnSuccess -> {
                mutableTrend.clear()
                mutableTrend.addAll(trendingState.data)
            }
            else -> Timber.e("TrendingState: No initial data")
        }

        TelevisionUI(
            searchModel = searchModel,
            mutableRated = mutableRated,
            mutableToday = mutableToday,
            mutableCover = mutableCover,
            mutableTrend = mutableTrend,
            navController = navController,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TelevisionUI(
    searchModel: SearchTelevisionViewModel,
    mutableRated: MutableList<LiveVision>,
    mutableToday: MutableList<LiveVision>,
    mutableCover: MutableList<LiveVision>,
    mutableTrend: MutableList<LiveVision>,
    navController: NavHostController,
) {
    val state = rememberPagerState()
    val isSearch = remember { mutableStateOf(false) }
    val selectionSlide = remember { mutableStateOf(false) }
    val mainList: MutableList<LiveVision> = arrayListOf()

    Box {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                SearchVisionField(searchModel, isSearch)
            }
            /** search list **/
            Row(verticalAlignment = Alignment.CenterVertically) {
                val searchVM =
                    searchModel.searchShared.collectAsStateWithLifecycle(initialValue = null)
                AnimatedVisibility(visible = isSearch.value) {
                    when (val search = searchVM.value) {
                        is StateLiveTelevision.HideLoader -> {}
                        is StateLiveTelevision.ShowLoader -> {}
                        is StateLiveTelevision.OnSuccess -> {
                            SearchResult(navController = navController, list = search.data)
                        }

                        is StateLiveTelevision.OnFailure -> Timber.e(" ${search.error}")
                        else -> {
                            Timber.e("No response from search")
                        }
                    }
                }
            }
            /** Header Text Pager **/
            Row(verticalAlignment = Alignment.CenterVertically) {
                mainList.clear()
                mainList.addAll(
                    stateList(
                        todayList = mutableToday,
                        ratedList = mutableRated,
                        select = selectionSlide,
                    ),
                )
                /** header today's & upcoming movie **/
                TelevisionSelection(selectionSlide)
            }

            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                /** Loader Row **/
                Row {
                    LoadSlider(
                        navController = navController,
                        state = state,
                        dataList = if (!selectionSlide.value) mainList.take(10) else mainList.take(10),
                    )
                }
                /** Dot Indicator Row **/
                Row(modifier = modifierRowIndicator) {
                    DotsIndicator(
                        totalDots = if (!selectionSlide.value) {
                            mainList.take(10).size
                        } else {
                            mainList.take(
                                10,
                            ).size
                        },
                        selectedIndex = state.currentPage,
                        selectedColor = PinkColor700,
                        unSelectedColor = LightDark220,
                    )
                }
                Row {
                    /** Discover Television **/
                    MoviePagingTitle(title = "Discover TV")
                }
                /** Discover Television Horizontal grid **/
                Row {
                    LazyRow {
                        items(count = mutableCover.count()) { index ->
                            DiscoverTelevision(
                                navController = navController,
                                tv = mutableCover,
                                index = index,
                            )
                        }
                    }
                }
                /** Trending title **/
                Row {
                    MoviePagingTitle(title = "This Week TV")
                }
                /** Trending this week Television vertical grid **/
                Row {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        modifier = Modifier
                            .size(420.dp),
                    ) {
                        items(count = mutableTrend.count()) { index ->
                            TrendingThisWeek(navController, mutableTrend, index)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DiscoverTelevision(
    navController: NavHostController,
    tv: List<LiveVision>,
    index: Int,
) {
    Box {
        Column(modifier = Modifier.padding(top = 16.dp, start = 5.dp)) {
            Card(modifier = modifierCardView) {
                val title = tv[index].name ?: EMPTY
                val path = tv[index].posterPath ?: default_image
                val url = path.imageUrl()
                val seriesId = tv[index].id ?: 0
                AsyncImageLoad(
                    url = url,
                    description = title,
                    onClick = { onClickTvShow(seriesId = seriesId, navController = navController) },
                )
            }
        }
    }
}

@Composable
private fun TrendingThisWeek(
    navController: NavHostController,
    tv: List<LiveVision>,
    index: Int,
) {
    Box(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, start = 10.dp, end = 5.dp)) {
        val title = tv[index]?.name ?: EMPTY
        Row {
            Card(modifier = modifierPagingItemCardView) {
                val path = tv[index].posterPath ?: default_image
                val url = path.imageUrl()
                val seriesId = tv[index].id ?: 0
                AsyncItemImg(url = url, description = title, onClick = { onClickTvShow(seriesId = seriesId, navController = navController) })
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LoadSlider(navController: NavHostController, state: PagerState, dataList: List<LiveVision>) {
    HorizontalPager(pageCount = dataList.size, state = state) { scope ->
        val averageVote = dataList[scope].voteCount ?: 0
        val aveStar = averageVote / 100
        Row {
            Box(modifier = modifierTitleBox) {
                Column(modifier = Modifier.padding(top = 16.dp, start = 5.dp)) {
                    Card(modifier = modifierCardView) {
                        val poster = dataList[scope].posterPath ?: default_image
                        val url = poster.imageUrl()
                        val description = dataList[scope].posterPath ?: EMPTY
                        val movieId = dataList[scope].id ?: 0
                        AsyncImageLoad(
                            url = url,
                            description = description,
                            onClick = {
                                clickMovie(
                                    movieId = movieId,
                                    navController = navController,
                                )
                            },
                        )
                    }
                }
                Column {
                    /** title **/
                    MovieTitle(title = dataList[scope].name ?: EMPTY)
                    /** date **/
                    DateRelease(date = dataList[scope].firstAirDate ?: EMPTY)
                    /** rating star **/
                    RatingStar(modifier = modifierStarTop, rating = aveStar.toFloat(), spaceBetween = 1.dp)
                    /** rating number **/
                    MovieRating(dataList[scope].voteCount ?: 0)
                    /** body **/
                    DisplayText(text = dataList[scope].overview ?: EMPTY)
                }
            }
        }
    }
    Spacer(modifier = Modifier.padding(4.dp))
}

/** search UI field **/
@Composable
private fun SearchVisionField(searchModel: SearchTelevisionViewModel, isSearch: MutableState<Boolean>) {
    var inputSearch by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current
    val manager = inputManager(context = context)
    val view = LocalView.current
    val clearIcon = @Composable {
        IconButton(
            onClick = {
                isSearch.value = false
                inputSearch = ""
            },
        ) { IconClear() }
    }

    val searchIcon = @Composable {
        IconButton(
            onClick = {
                manager.hideSoftInputFromWindow(view.windowToken, 0)
            },
        ) { IconSearch() }
    }

    TextField(
        value = inputSearch,
        onValueChange = {
            inputSearch = it
            if (inputSearch.isEmpty()) {
                manager.hideSoftInputFromWindow(view.windowToken, 0)
                isSearch.value = false
            }
        },
        placeholder = { Text("Search") },
        keyboardOptions = KeyboardOptions
            .Default.copy(
                imeAction = ImeAction.Done,
            ),
        colors = toSearchField(),
        leadingIcon = if (inputSearch.isBlank()) searchIcon else null,
        trailingIcon = if (inputSearch.isNotBlank()) clearIcon else null,
        textStyle = searchTextStyle,
        modifier = modifierSearch,
    )

    LaunchedEffect(inputSearch) {
        delay(1000)
        if (inputSearch.isNotEmpty()) {
            isSearch.value = true
            searchModel.searchTelevision(query = inputSearch)
            manager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}

/** search result **/
@Composable
fun SearchResult(navController: NavHostController, list: List<LiveVision>) {
    LazyColumn(
        modifier = Modifier.padding(top = 5.dp),
    ) {
        items(
            items = list.sortedByDescending { it.firstAirDate },
        ) { tv ->
            val averageVote = tv.voteCount ?: 0
            val aveStar = averageVote / 100
            Box(modifier = modifierSearchBox) {
                Column(modifier = Modifier.padding(start = 2.dp)) {
                    Card(modifier = modifierSearchCardView) {
                        val poster = tv.posterPath ?: default_image
                        val url = poster.imageUrl()
                        val description = tv.posterPath ?: EMPTY
                        val movieId = tv.id ?: 0
                        AsyncSearchImageLoad(
                            onClick = { clickMovie(movieId, navController) },
                            url = url,
                            description = description,
                        )
                    }
                }
                Column {
                    /** title **/
                    ResultText(text = tv.name ?: EMPTY)
                    /** rating star **/
                    RatingStar(modifier = modifierSearchResult, rating = aveStar.toFloat(), spaceBetween = 1.dp)
                    /** date **/
                    DateReleaseSearch(date = tv.firstAirDate ?: EMPTY)
                }
            }
        }
    }
}

/** private entities **/

private fun stateList(
    todayList: MutableList<LiveVision>,
    ratedList: MutableList<LiveVision>,
    select: MutableState<Boolean>,
): MutableList<LiveVision> {
    val mainList: MutableList<LiveVision> = arrayListOf()
    if (!select.value) {
        mainList.clear()
        mainList.addAll(todayList)
    } else {
        mainList.clear()
        mainList.addAll(ratedList)
    }
    return mainList
}

@Composable
private fun inputManager(context: Context): InputMethodManager {
    return context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
}

@Composable
private fun IconClear() {
    return Icon(
        Icons.Default.Clear,
        contentDescription = "",
        tint = PinkColor700,
    )
}

@Composable
private fun IconSearch() {
    return Icon(
        painter = painterResource(id = R.drawable.ic_discover),
        contentDescription = "",
        tint = PinkColor700,
    )
}

private val searchTextStyle = TextStyle(
    color = Color.White,
    fontSize = 18.sp,
    fontWeight = FontWeight.Bold,
    textAlign = TextAlign.Left,
)

private fun clickMovie(movieId: Int, navController: NavHostController) {
    navController.navigate(NavigationScreen.MainScreen.createRoute(id = movieId.toString()))
}
fun onClickTvShow(seriesId: Int, navController: NavHostController) {
    navController.navigate(NavigationScreen.TelevisionScreen.createRoute(id = seriesId.toString()))
}
