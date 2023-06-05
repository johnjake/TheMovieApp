package com.themovieguide.org.features.television

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.themovieguide.domain.model.television.LiveVision
import com.themovieguide.org.features.searchtv.SearchTelevisionViewModel
import com.themovieguide.org.features.state.StateLiveTelevision
import com.themovieguide.org.ui.theme.gradientHome
import timber.log.Timber

@Composable
fun TelevisionScreen(
    ratedState: StateLiveTelevision<List<LiveVision>>?,
    todayState: StateLiveTelevision<List<LiveVision>>?,
    searchModel: SearchTelevisionViewModel,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                gradientHome(),
            ),
    ) {
        searchModel.searchTelevision(query = "Batman")
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

        val searchState = searchModel.searchShared.collectAsStateWithLifecycle(initialValue = null)
        when (val searchValue = searchState.value) {
            is StateLiveTelevision.ShowLoader -> { }
            is StateLiveTelevision.HideLoader -> { }
            is StateLiveTelevision.OnFailure -> Timber.e("SearchTelevision Error: ${searchValue.error}")
            is StateLiveTelevision.OnSuccess -> {
            }
            else -> Timber.e("SearchTelevision: No initial data")
        }
    }
}
