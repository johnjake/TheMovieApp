package com.themovieguide.org.features.television

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.themovieguide.domain.model.television.LiveVision
import com.themovieguide.org.features.state.StateLiveTelevision
import com.themovieguide.org.ui.theme.gradientHome
import timber.log.Timber

@Composable
fun TelevisionScreen(ratedState: StateLiveTelevision<List<LiveVision>>?) {
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
                    Timber.e("@@@@@@@@@@@@@@@ ${it.name}")
                }
            }
            else -> Timber.e("RatedTelevision: No initial data")
        }
    }
}
