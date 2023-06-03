package com.themovieguide.org.features.utils

import androidx.compose.material.MaterialTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.themovieguide.domain.model.Movies
import com.themovieguide.domain.utils.EMPTY
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun HideScreen() {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = MaterialTheme.colors.isLight

    SideEffect {
        systemUiController.isStatusBarVisible = false
        systemUiController.isNavigationBarVisible = false
        systemUiController.setNavigationBarColor(Color.Transparent, !useDarkIcons)
    }
}

@Composable
fun Dp.toPx(): Float {
    val density = LocalDensity.current
    return with(density) { this@toPx.toPx() }
}

@Composable
fun Dp.toFloatPx(): Float {
    val density = LocalDensity.current
    return with(density) { this@toFloatPx.toPx() }
}

@Composable
fun Float.toDp(): Dp {
    val density = LocalDensity.current
    return with(density) { this@toDp.toDp() }
}
fun hideSystemUI(windowInsetsController: WindowInsetsControllerCompat) {
    windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
}

fun showSystemUI(windowInsetsController: WindowInsetsControllerCompat) {
    windowInsetsController.show(WindowInsetsCompat.Type.systemBars())
}

fun String.toStandardDate(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    return if (this.isNotEmpty()) {
        val dateParam = inputFormat.parse(this)
        outputFormat.format(dateParam ?: EMPTY)
    } else {
        "-"
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun toSearchField() = TextFieldDefaults.textFieldColors(
    containerColor = Color.Transparent,
    focusedTextColor = Color.White,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    disabledIndicatorColor = Color.Gray,
)

@Composable
fun movieUrl(movies: Movies): String {
    val path = movies.posterPath ?: default_image
    return remember { path.imageUrl() }
}
