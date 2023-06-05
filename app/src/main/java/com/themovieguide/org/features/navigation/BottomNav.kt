package com.themovieguide.org.features.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.themovieguide.domain.utils.EMPTY
import com.themovieguide.org.R
import com.themovieguide.org.features.details.CastViewModel
import com.themovieguide.org.features.details.MovieDetailsScreen
import com.themovieguide.org.features.details.MovieDetailsViewModel
import com.themovieguide.org.features.discover.DiscoverScreen
import com.themovieguide.org.features.home.HomeCinema
import com.themovieguide.org.features.home.ShowingViewModel
import com.themovieguide.org.features.rated.TopRatedViewModel
import com.themovieguide.org.features.ratedtv.RatedTelevisionViewModel
import com.themovieguide.org.features.search.SearchViewModel
import com.themovieguide.org.features.television.TelevisionScreen
import com.themovieguide.org.features.upcoming.UpcomingViewModel
import com.themovieguide.org.ui.theme.Gray800
import com.themovieguide.org.ui.theme.PinkColor700
import com.themovieguide.org.ui.theme.gradientColor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest

typealias mainScreen = NavigationScreen.MainScreen
typealias visitedScreen = NavigationScreen.Visited
typealias detailScreen = NavigationScreen.DetailScreen
typealias televisionScreen = NavigationScreen.TelevisionScreen

@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    val navigationItems = listOf(
        NavigationScreen.MainScreen,
        NavigationScreen.TelevisionScreen,
        NavigationScreen.Visited,
    )
    val counterFlow = remember { MutableStateFlow(mainScreen.route) }
    val stateTop = remember { mutableStateOf(false) }
    val stateBottom = remember { mutableStateOf(false) }

    LaunchedEffect(counterFlow) {
        counterFlow.collectLatest { screen ->
            stateBottom.value = screen == NavigationScreen.Visited.route
        }
    }
    OnNavView(
        navigationItems = navigationItems,
        navController = navController,
        counterFlow = counterFlow,
        stateFlow = stateTop,
        stateBottom = stateBottom,
    )
}

@Composable
fun OnNavView(
    navigationItems: List<NavigationScreen>,
    navController: NavHostController,
    counterFlow: MutableStateFlow<String>,
    stateFlow: MutableState<Boolean>,
    stateBottom: MutableState<Boolean>,
) {
    Scaffold(
        topBar = {
            if (stateFlow.value) {
                TopAppBar(title = { Text(text = EMPTY) }, backgroundColor = Color.Transparent)
            } else {
                stateFlow.value = false
            }
        },
        bottomBar = {
            if (!stateBottom.value) {
                BottomNav(navigationItems, navController, counterFlow = counterFlow)
            }
        },
    ) {
        Navigation(
            navController,
            Modifier.padding(
                top = it.calculateTopPadding(),
                bottom = it.calculateBottomPadding(),
            ),
            stateBottom,
        )
    }
}

@Composable
private fun Navigation(
    navController: NavHostController,
    modifier: Modifier,
    stateBottom: MutableState<Boolean>,
) {
    NavHost(navController, startDestination = NavigationScreen.MainScreen.route, modifier = modifier) {
        val detailArguments = listOf(navArgument("id") { type = NavType.StringType })
        composable(mainScreen.route) { backStackEntry ->
            stateBottom.value = false
            LaunchHome(backStackEntry, navController)
        }

        composable(televisionScreen.route) { backStackEntry ->
            stateBottom.value = false
            LaunchedTelevision(backStackEntry = backStackEntry, navController = navController)
        }

        composable(visitedScreen.route) { backStackEntry ->
            stateBottom.value = false
            LaunchDiscover(backStackEntry, navController)
        }

        composable(detailScreen.route, arguments = detailArguments) { backStackEntry ->
            stateBottom.value = true
            val movieId = backStackEntry.arguments?.getString("id")
            LaunchDetailScreen(backStackEntry, navController, movieId)
        }
    }
}

@Composable
fun LaunchDiscover(
    backStackEntry: NavBackStackEntry,
    navController: NavHostController,
) {
    val movieEntry = remember(backStackEntry) {
        navController.getBackStackEntry(mainScreen.route)
    }
    val viewModel = hiltViewModel<MovieDetailsViewModel>(movieEntry)
    DiscoverScreen(
        navController = navController,
        viewModel = viewModel,
    )
}

@Composable
private fun LaunchHome(
    backStackEntry: NavBackStackEntry,
    navController: NavHostController,
) {
    val movieEntry = remember(backStackEntry) {
        navController.getBackStackEntry(mainScreen.route)
    }
    val searchModel = hiltViewModel<SearchViewModel>(movieEntry)
    val showModel = hiltViewModel<ShowingViewModel>(movieEntry)
    val topModel = hiltViewModel<TopRatedViewModel>(movieEntry)
    val upModel = hiltViewModel<UpcomingViewModel>(movieEntry)
    HomeCinema(
        showModel,
        topModel,
        upModel,
        searchModel,
        navController,
    )
}

@Composable
private fun LaunchedTelevision(
    backStackEntry: NavBackStackEntry,
    navController: NavHostController,
) {
    val tvEntry = remember(backStackEntry) {
        navController.getBackStackEntry(mainScreen.route)
    }

    /** instance a viewmodel **/
    val ratedModel = hiltViewModel<RatedTelevisionViewModel>(tvEntry)

    /** fetch data from room **/
    ratedModel.fetchTopRated()

    /** observe data and get the data state **/
    val ratedState = ratedModel.ratedShared.collectAsStateWithLifecycle(initialValue = null)

    /** passing state or view model depends on the use case **/
    TelevisionScreen(ratedState = ratedState.value)
}

@Composable
private fun LaunchDetailScreen(
    backStackEntry: NavBackStackEntry,
    navController: NavHostController,
    movieId: String?,
) {
    val isVisible = remember { mutableStateOf(true) }
    val theaterEntry = remember(backStackEntry) {
        navController.getBackStackEntry(detailScreen.route)
    }
    val viewModel = hiltViewModel<MovieDetailsViewModel>(theaterEntry)
    val castModel = hiltViewModel<CastViewModel>(theaterEntry)
    viewModel.fetchMovieDetails(movieId?.toInt() ?: 0)
    castModel.fetchMovieCast(movieId?.toInt() ?: 0)
    val state = viewModel.showShared.collectAsStateWithLifecycle(initialValue = null)
    val cast = castModel.sharedFlow.collectAsStateWithLifecycle(initialValue = null)
    MovieDetailsScreen(
        viewModel = viewModel,
        state = state.value,
        cast = cast.value,
        visible = isVisible,
        navController = navController,
    )
}

@Composable
fun BottomNav(
    navScreen: List<NavigationScreen>,
    navController: NavController,
    counterFlow: MutableStateFlow<String>,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(gradientColor()),
    ) {
        BottomNavigation(
            backgroundColor = Color.Transparent,
            elevation = 8.dp,
            contentColor = MaterialTheme.colors.onSurface,
            modifier = Modifier.fillMaxSize(),
        ) {
            val navBackStackEntry = navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry.value?.destination?.route
            navScreen.forEach { item ->
                BottomNavigationItem(
                    selected = currentRoute == item.route,
                    icon = { BottomIcon(item.title, currentRoute, item.route) },
                    onClick = {
                        counterFlow.value = item.route
                        navController.navigate(item.route) {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                )
            }
        }
    }
}

@Composable
fun imagePainter(title: String): Painter {
    return when (title) {
        NavigationScreen.MainScreen.title -> painterResource(id = R.drawable.ic_now_showing)
        NavigationScreen.TelevisionScreen.title -> painterResource(id = R.drawable.ic_television)
        NavigationScreen.Visited.title -> painterResource(id = R.drawable.ic_people_choice)
        else -> painterResource(id = R.drawable.ic_now_showing)
    }
}

@Composable
fun BottomIcon(title: String, currentRoute: String?, screenRoute: String) {
    return Icon(
        painter = imagePainter(title = title),
        title,
        tint = if (currentRoute == screenRoute) PinkColor700 else Gray800,
    )
}
