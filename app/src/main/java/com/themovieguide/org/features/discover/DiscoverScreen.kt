package com.themovieguide.org.features.discover

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.themovieguide.data.utils.castToLongDate
import com.themovieguide.domain.model.Movies
import com.themovieguide.domain.states.showing.StateMovies
import com.themovieguide.domain.utils.EMPTY
import com.themovieguide.org.features.details.MovieDetailsViewModel
import com.themovieguide.org.features.home.onClickMovie
import com.themovieguide.org.features.utils.default_image
import com.themovieguide.org.features.utils.imageUrl
import com.themovieguide.org.features.utils.toast
import com.themovieguide.org.ui.theme.AnimatedText
import com.themovieguide.org.ui.theme.gradientHome
import com.themovieguide.org.ui.theme.images.AsyncItemImgVisited
import com.themovieguide.org.ui.theme.modifier.modifierTwoCardView
import com.themovieguide.org.ui.theme.text.VisitedTitle
import timber.log.Timber

@Composable
fun DiscoverScreen(navController: NavHostController, viewModel: MovieDetailsViewModel) {
    val context = LocalContext.current
    val isLoading = remember { mutableStateOf(false) }
    val mainList: MutableList<Movies> = arrayListOf()
    LaunchedEffect(key1 = viewModel) {
        viewModel.fetchVisitedMovies()
    }
    val model = viewModel.mediaShared.collectAsStateWithLifecycle(initialValue = null)
    when (val response = model.value) {
        is StateMovies.ShowLoader -> {
            isLoading.value = true
        }
        is StateMovies.HideLoader -> {
            isLoading.value = false
        }
        is StateMovies.OnSuccess -> {
            mainList.clear()
            mainList.addAll(response.data)
        }
        is StateMovies.OnFailure -> context.toast(msg = response.error, duration = 1)
        else -> Timber.e("DiscoverScreen, initial call with empty returns")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                gradientHome(),
            ),
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState()),
        ) {
            Row(modifier = Modifier.padding(start = 20.dp, top = 20.dp)) {
                AnimatedText(text = "Visited Movie")
            }
            Row {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .height(680.dp)
                        .width(450.dp),
                ) {
                    items(count = mainList.count()) { index ->
                        MovieInTheater(navController, mainList.toList(), index)
                    }
                }
            }
        }
    }
}

@Composable
fun CenteredProgressBar(isLoading: MutableState<Boolean>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
    ) {
        AnimatedVisibility(visible = isLoading.value) {
            CircularProgressIndicator()
        }
    }
}

@Composable
private fun MovieInTheater(
    navController: NavHostController,
    movie: List<Movies>,
    index: Int,
) {
    Box(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, start = 10.dp, end = 5.dp)) {
        val title = movie[index].title ?: EMPTY
        Row {
            Card(modifier = modifierTwoCardView) {
                val path = movie[index].posterPath ?: default_image
                val url = path.imageUrl()
                val movieId = movie[index].id ?: 0
                AsyncItemImgVisited(url = url, description = title, onClick = { onClickMovie(movieId = movieId, navController = navController) })
                VisitedTitle(title = "${movie[index].releaseDate?.castToLongDate()}")
            }
        }
    }
}
