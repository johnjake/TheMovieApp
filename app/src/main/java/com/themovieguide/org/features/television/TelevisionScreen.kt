package com.themovieguide.org.features.television

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.text.KeyboardOptions
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
import com.themovieguide.org.features.navigation.NavigationScreen
import com.themovieguide.org.features.searchtv.SearchTelevisionViewModel
import com.themovieguide.org.features.state.StateLiveTelevision
import com.themovieguide.org.features.utils.default_image
import com.themovieguide.org.features.utils.imageUrl
import com.themovieguide.org.features.utils.toSearchField
import com.themovieguide.org.ui.theme.PinkColor700
import com.themovieguide.org.ui.theme.gradientHome
import com.themovieguide.org.ui.theme.images.AsyncSearchImageLoad
import com.themovieguide.org.ui.theme.modifier.modifierSearch
import com.themovieguide.org.ui.theme.modifier.modifierSearchBox
import com.themovieguide.org.ui.theme.modifier.modifierSearchCardView
import com.themovieguide.org.ui.theme.modifier.modifierSearchResult
import com.themovieguide.org.ui.theme.rating.RatingStar
import com.themovieguide.org.ui.theme.text.DateReleaseSearch
import com.themovieguide.org.ui.theme.text.ResultText
import kotlinx.coroutines.delay
import timber.log.Timber

@Composable
fun TelevisionScreen(
    ratedState: StateLiveTelevision<List<LiveVision>>?,
    todayState: StateLiveTelevision<List<LiveVision>>?,
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
        /** extract data from state **/
        when (ratedState) {
            is StateLiveTelevision.ShowLoader -> { }
            is StateLiveTelevision.HideLoader -> { }
            is StateLiveTelevision.OnFailure -> Timber.e("RatedTelevision Error: ${ratedState.error}")
            is StateLiveTelevision.OnSuccess -> {
                ratedState.data.forEach {
                    // Timber.e("@@@@@@@@@@@@@@@ ${it.name}")
                }
            }
            else -> Timber.e("RatedTelevision: No initial data")
        }

        when (todayState) {
            is StateLiveTelevision.ShowLoader -> { }
            is StateLiveTelevision.HideLoader -> { }
            is StateLiveTelevision.OnFailure -> Timber.e("RatedTelevision Error: ${todayState.error}")
            is StateLiveTelevision.OnSuccess -> {
                todayState.data.forEach {
                    Timber.e("@@@@@@@@@@@@@@@ ${it.name}")
                }
            }
            else -> Timber.e("TodayAirShow: No initial data")
        }
        TelevisionUI(searchModel, navController)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TelevisionUI(
    searchModel: SearchTelevisionViewModel,
    navController: NavHostController,
) {
    val state = rememberPagerState()
    val isSearch = remember { mutableStateOf(false) }
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
                SearchVisionField(searchModel, isSearch)
            }
            /** search list **/
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
        }
    }
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
                    /** title **/
                    ResultText(text = tv.name ?: EMPTY)
                    /** rating star **/
                    /** rating star **/
                    RatingStar(modifier = modifierSearchResult, rating = aveStar.toFloat(), spaceBetween = 1.dp)
                    /** date **/
                    /** date **/
                    DateReleaseSearch(date = tv.firstAirDate ?: EMPTY)
                }
            }
        }
    }
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
fun onClickMovie(movieId: Int, navController: NavHostController) {
    navController.navigate(NavigationScreen.NowShowing.createRoute(id = movieId.toString()))
}
