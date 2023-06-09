package com.themovieguide.org.features.home

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.draw.shadow
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
import com.themovieguide.domain.model.Movies
import com.themovieguide.domain.utils.EMPTY
import com.themovieguide.org.R
import com.themovieguide.org.features.navigation.NavigationScreen
import com.themovieguide.org.features.rated.TopRatedViewModel
import com.themovieguide.org.features.search.SearchViewModel
import com.themovieguide.org.features.state.StateMovie
import com.themovieguide.org.features.upcoming.UpcomingViewModel
import com.themovieguide.org.features.utils.boxIndicator
import com.themovieguide.org.features.utils.default_image
import com.themovieguide.org.features.utils.imageUrl
import com.themovieguide.org.features.utils.toSearchField
import com.themovieguide.org.ui.theme.Default800
import com.themovieguide.org.ui.theme.LightDark220
import com.themovieguide.org.ui.theme.PinkColor700
import com.themovieguide.org.ui.theme.Primary800
import com.themovieguide.org.ui.theme.gradientHome
import com.themovieguide.org.ui.theme.images.AsyncImageLoad
import com.themovieguide.org.ui.theme.images.AsyncItemImg
import com.themovieguide.org.ui.theme.images.AsyncSearchImageLoad
import com.themovieguide.org.ui.theme.modifier.modifierCardView
import com.themovieguide.org.ui.theme.modifier.modifierItemCardView
import com.themovieguide.org.ui.theme.modifier.modifierRowIndicator
import com.themovieguide.org.ui.theme.modifier.modifierRowItem
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
import com.themovieguide.org.ui.theme.text.MovieItemTitle
import com.themovieguide.org.ui.theme.text.MovieRating
import com.themovieguide.org.ui.theme.text.MovieTitle
import com.themovieguide.org.ui.theme.text.ResultText
import com.themovieguide.org.ui.theme.text.TextSelection
import com.themovieguide.org.ui.theme.text.TheaterEnableSelection
import kotlinx.coroutines.delay
import org.burnoutcrew.reorderable.NoDragCancelledAnimation
import org.burnoutcrew.reorderable.ReorderableItem
import org.burnoutcrew.reorderable.detectReorderAfterLongPress
import org.burnoutcrew.reorderable.rememberReorderableLazyGridState
import org.burnoutcrew.reorderable.reorderable
import timber.log.Timber

@Composable
fun HomeCinema(
    viewModel: ShowingViewModel,
    topModel: TopRatedViewModel,
    upModel: UpcomingViewModel,
    searchModel: SearchViewModel,
    navController: NavHostController,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                gradientHome(),
            ),
    ) {
        viewModel.getNowShowing()
        upModel.fetchUpcoming(1)
        Content(viewModel, upModel, topModel, searchModel, navController)
    }
}
val comeList: MutableList<Movies> = arrayListOf()

@Composable
fun Content(
    viewModel: ShowingViewModel,
    upModel: UpcomingViewModel,
    topModel: TopRatedViewModel,
    searchModel: SearchViewModel,
    navController: NavHostController,
) {
    val model = viewModel.showShared.collectAsStateWithLifecycle(initialValue = null)
    val upComingModel = upModel.showShared.collectAsStateWithLifecycle(initialValue = null)
    val isSearch = remember { mutableStateOf(false) }
    val homeValue = model.value
    val upcomingValue = upComingModel.value
    val showList: MutableList<Movies> = arrayListOf()

    /** observe theater and top rated **/
    val onClickTop = remember { mutableStateOf(false) }
    /** observe now showing **/
    when (homeValue) {
        is StateMovie.HideLoader -> { /** hide progressbar **/ }
        is StateMovie.ShowLoader -> { /** show progressbar **/ }
        is StateMovie.OnSuccess -> showList.addAll(homeValue.data)
        is StateMovie.OnFailure -> Timber.e(" ${homeValue.error}")
        else -> { Timber.e("Home: no response in getNowShowing") }
    }
    /** observe upcoming **/
    when (upcomingValue) {
        is StateMovie.HideLoader -> { /** hide progressbar **/ }
        is StateMovie.ShowLoader -> { /** show progressbar **/ }
        is StateMovie.OnSuccess -> comeList.addAll(upcomingValue.data)
        is StateMovie.OnFailure -> Timber.e(" ${upcomingValue.error}")
        else -> { Timber.e("Home: no response in getUpComing") }
    }

    /** Home UI **/
    homeUI(
        navController,
        searchModel,
        isSearch,
        showList,
        comeList,
        topModel,
        onClickTop,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun homeUI(
    navController: NavHostController,
    searchModel: SearchViewModel,
    isSearch: MutableState<Boolean>,
    showList: MutableList<Movies>,
    comeList: MutableList<Movies>,
    topModel: TopRatedViewModel,
    onClickTop: MutableState<Boolean>,
) {
    val state = rememberPagerState()
    val selectionSlide = remember { mutableStateOf(false) }
    val theaterSlide = remember { mutableStateOf(false) }
    Box {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                SearchInputField(searchModel, isSearch)
            }
            /** search list **/
            Row(verticalAlignment = Alignment.CenterVertically) {
                val searchVM =
                    searchModel.searchShared.collectAsStateWithLifecycle(initialValue = null)
                AnimatedVisibility(visible = isSearch.value) {
                    when (val search = searchVM.value) {
                        is StateMovie.HideLoader -> {}
                        is StateMovie.ShowLoader -> {}
                        is StateMovie.OnSuccess -> {
                            SearchResult(navController = navController, list = search.data)
                        }

                        is StateMovie.OnFailure -> Timber.e(" ${search.error}")
                        else -> {
                            Timber.e("No response from search")
                        }
                    }
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                /** header today's & upcoming movie **/
                TextSelection(selectionSlide)
            }
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                val showing = remember { mutableStateOf(emptyList<Movies>()) }
                showing.value = showList.take(8)
                /** Loader Row **/
                Row {
                    LoadSlider(
                        navController = navController,
                        state = state,
                        selected = selectionSlide,
                        showing = showing,
                    )
                }
                /** Dot Indicator Row **/
                Row(modifier = modifierRowIndicator) {
                    DotsIndicator(
                        totalDots = if (!selectionSlide.value) {
                            showList.take(8).size
                        } else {
                            comeList.take(
                                8,
                            ).size
                        },
                        selectedIndex = state.currentPage,
                        selectedColor = PinkColor700,
                        unSelectedColor = LightDark220,
                    )
                }
                Row(modifier = modifierRowItem) {
                    val nowColor = remember { mutableStateOf(Primary800) }
                    val nowWeight = remember { mutableStateOf(FontWeight.Normal) }
                    val theaColor = remember { mutableStateOf(Default800) }
                    val theaWeight = remember { mutableStateOf(FontWeight.Normal) }

                    /** In Theater & This Week **/
                    TheaterEnableSelection(
                        selected = theaterSlide,
                        enableColor = nowColor,
                        enableWeight = nowWeight,
                        disableColor = theaColor,
                        disableWeight = theaWeight,
                        titleEnable = "In Theater",
                        disabledTitle = "Top Rated",
                        onClickTop = { top ->
                            when {
                                top -> topModel.fetchTopRated(1)
                                else -> Timber.e("fetch data from local")
                            }
                            onClickTop.value = top
                        },
                    )
                }
                Row {
                    if (onClickTop.value) {
                        TheaterShow(
                            navController = navController,
                            showList = showList,
                            topViewModel = topModel,
                            movieList = comeList,
                            onClickTop,
                        )
                    } else {
                        TheaterShow(
                            navController = navController,
                            showList = showList,
                            topViewModel = topModel,
                            movieList = comeList,
                            onClickTop,
                        )
                    }
                }
            }
        }
    }
}

fun convertToMutableState(movieList: List<Movies>): MutableState<List<Movies>> {
    return mutableStateOf(movieList)
}

@Composable
fun SearchInputField(searchModel: SearchViewModel, isSearch: MutableState<Boolean>) {
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
            searchModel.fetchUpcoming(query = inputSearch, page = 1)
            manager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}

@Composable
fun SearchResult(navController: NavHostController, list: List<Movies>) {
    LazyColumn(
        modifier = Modifier.padding(top = 5.dp),
    ) {
        items(
            items = list.sortedByDescending { it.releaseDate },
        ) { movie ->
            val averageVote = movie.voteCount ?: 0
            val aveStar = averageVote / 100
            Box(modifier = modifierSearchBox) {
                Column(modifier = Modifier.padding(start = 2.dp)) {
                    Card(modifier = modifierSearchCardView) {
                        val poster = movie.posterPath ?: default_image
                        val url = poster.imageUrl()
                        val description = movie.posterPath ?: EMPTY
                        val movieId = movie.id ?: 0
                        AsyncSearchImageLoad(
                            onClick = { clickMovie(movieId, navController) },
                            url = url,
                            description = description,
                        )
                    }
                }
                Column {
                    /** title **/
                    ResultText(text = movie.title ?: EMPTY)
                    /** rating star **/
                    RatingStar(modifier = modifierSearchResult, rating = aveStar.toFloat(), spaceBetween = 1.dp)
                    /** date **/
                    DateReleaseSearch(date = movie.releaseDate ?: EMPTY)
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoadSlider(navController: NavHostController, state: PagerState, selected: MutableState<Boolean>, showing: MutableState<List<Movies>>) {
    AnimatedVisibility(visible = !selected.value) {
        val showingList = showing.value
        HorizontalPager(pageCount = showingList.size, state = state) { scope ->
            val averageVote = showingList[scope].voteCount ?: 0
            val aveStar = averageVote / 100
            Row {
                Box(modifier = modifierTitleBox) {
                    Column(modifier = Modifier.padding(top = 16.dp, start = 5.dp)) {
                        Card(modifier = modifierCardView) {
                            val poster = showingList[scope].posterPath ?: default_image
                            val url = poster.imageUrl()
                            val description = showingList[scope].posterPath ?: EMPTY
                            val movieId = showingList[scope].id ?: 0
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
                        /** title **/
                        MovieTitle(title = showingList[scope].title ?: EMPTY)
                        /** date **/
                        /** date **/
                        DateRelease(date = showingList[scope].releaseDate ?: EMPTY)
                        /** rating star **/
                        /** rating star **/
                        RatingStar(modifier = modifierStarTop, rating = aveStar.toFloat(), spaceBetween = 1.dp)
                        /** rating number **/
                        /** rating number **/
                        MovieRating(showingList[scope].voteCount ?: 0)
                        /** body **/
                        /** body **/
                        DisplayText(text = showingList[scope].overview ?: EMPTY)
                    }
                }
            }
        }
    }
    AnimatedVisibility(visible = selected.value) {
        val mutableList: MutableList<Movies> = arrayListOf()
        mutableList.clear()
        mutableList.addAll(comeList.take(8))
        HorizontalPager(pageCount = mutableList.size, state = state) { scope ->
            val averageVote = mutableList[scope].voteCount ?: 0
            val aveStar = averageVote / 100
            Row {
                Box(modifier = modifierTitleBox) {
                    Column(modifier = Modifier.padding(top = 16.dp, start = 5.dp)) {
                        Card(modifier = modifierCardView) {
                            val poster = mutableList[scope].posterPath ?: default_image
                            val url = poster.imageUrl()
                            val description = mutableList[scope].posterPath ?: EMPTY
                            val movieId = mutableList[scope].id ?: 0
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
                        /** title **/
                        MovieTitle(title = mutableList[scope].title ?: EMPTY)
                        /** date **/
                        /** date **/
                        DateRelease(date = mutableList[scope].releaseDate ?: EMPTY)
                        /** rating star **/
                        /** rating star **/
                        RatingStar(modifier = modifierStarTop, rating = aveStar.toFloat(), spaceBetween = 1.dp)
                        /** rating number **/
                        /** rating number **/
                        MovieRating(mutableList[scope].voteCount ?: 0)
                        /** body **/
                        /** body **/
                        DisplayText(text = mutableList[scope].overview ?: EMPTY)
                    }
                }
            }
        }
    }
    Spacer(modifier = Modifier.padding(4.dp))
}

@Composable
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color,
    unSelectedColor: Color,
) {
    LazyRow(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Center,
    ) {
        items(totalDots) { index ->
            when (index) {
                selectedIndex -> Box(modifier = Modifier.boxIndicator(selectedColor))
                else -> Box(modifier = Modifier.boxIndicator(unSelectedColor))
            }
            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}

@Composable
private fun TheaterShow(
    navController: NavHostController,
    showList: MutableList<Movies>,
    topViewModel: TopRatedViewModel,
    movieList: MutableList<Movies>,
    theater: MutableState<Boolean>,
) {
    val ratedModel = topViewModel.showShared.collectAsStateWithLifecycle(initialValue = null)
    /** observe in theater **/
    when {
        theater.value -> {
            when (val result = ratedModel.value) {
                is StateMovie.HideLoader -> {}
                is StateMovie.ShowLoader -> {}
                is StateMovie.OnSuccess -> HandleMapIndex(navController = navController, ratedList = result.data, commonList = movieList)
                is StateMovie.OnFailure -> Timber.e(" ${result.error}")
                else -> { Timber.e("Home no response in Theater") }
            }
        }
        else -> {
            HandleMapIndex(navController = navController, ratedList = showList.toList(), commonList = movieList)
        }
    }
}

@Composable
private fun HandleMapIndex(
    navController: NavHostController,
    ratedList: List<Movies>,
    commonList: MutableList<Movies>,
) {
    commonList.clear()
    commonList.addAll(ratedList)
    val size = commonList.size - 1
    for (i in 0..size) {
        commonList[i].key = i
    }
    if (commonList.isNotEmpty()) {
        DisplayMovieInTheater(navController = navController, list = commonList.toList())
    }
}

@Composable
fun DisplayMovieInTheater(navController: NavHostController, list: List<Movies>) {
    val data = remember { mutableStateOf(list) }
    val state = rememberReorderableLazyGridState(
        dragCancelledAnimation = NoDragCancelledAnimation(),
        onMove = { from, to ->
            data.value = data.value.toMutableList().apply {
                add(to.index, removeAt(from.index))
            }
        },
    )
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        state = state.gridState,
        modifier = Modifier
            .height(400.dp)
            .width(390.dp)
            .padding(start = 5.dp, end = 5.dp)
            .reorderable(state),
    ) {
        items(data.value, key = { user -> user.key }) { item ->
            ReorderableItem(state, key = item, defaultDraggingModifier = Modifier) { isDragging ->
                val elevation = animateDpAsState(if (isDragging) 16.dp else 0.dp)
                Column(
                    modifier = modifierRowItem
                        .shadow(elevation.value),
                ) {
                    val title = item.title ?: EMPTY
                    Card(
                        modifier = modifierItemCardView.detectReorderAfterLongPress(state),
                        content = {
                            val path = item.posterPath ?: default_image
                            val url = path.imageUrl()
                            val movieId = item.id ?: 0
                            AsyncItemImg(
                                url = url,
                                description = title,
                                onClick = { onClickMovie(movieId = movieId, navController = navController) },
                            )
                        },
                    )
                    MovieItemTitle(title, state)
                }
            }
        }
    }
}

private val searchTextStyle = TextStyle(
    color = Color.White,
    fontSize = 18.sp,
    fontWeight = FontWeight.Bold,
    textAlign = TextAlign.Left,
)

@Composable
private fun IconSearch() {
    return Icon(
        painter = painterResource(id = R.drawable.ic_discover),
        contentDescription = "",
        tint = PinkColor700,
    )
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
private fun inputManager(context: Context): InputMethodManager {
    return context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
}

private fun clickMovie(movieId: Int, navController: NavHostController) {
    navController.navigate(NavigationScreen.MainScreen.createRoute(id = movieId.toString()))
}
fun onClickMovie(movieId: Int, navController: NavHostController) {
    navController.navigate(NavigationScreen.NowShowing.createRoute(id = movieId.toString()))
}
