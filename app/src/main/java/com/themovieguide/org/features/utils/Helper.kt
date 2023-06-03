package com.themovieguide.org.features.utils

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import com.themovieguide.data.model.dto.ParcelizeMovies
import com.themovieguide.data.model.meta.CastMeta
import com.themovieguide.data.model.meta.DetailsMeta
import com.themovieguide.domain.model.Movies
import com.themovieguide.org.BuildConfig
import com.themovieguide.org.R
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.IOException

fun String.imageUrl(): String {
    return BuildConfig.BASE_URL_POSTER + this
}

fun String.originalUrl(): String {
    return BuildConfig.BASE_URL_ORIGINAL + this
}

fun String.profileUrl(): String {
    return when {
        this.isEmpty() -> default_profile
        else -> BuildConfig.BASE_URL_ORIGINAL + this
    }
}

fun String.playVideoUrl(): String {
    return BuildConfig.BASE_VIDEO_URL + this
}

fun Modifier.boxIndicator(takeColor: Color) =
    this
        .size(9.dp)
        .clip(CircleShape)
        .background(takeColor)
        .border(
            width = 1.dp,
            shape = CircleShape,
            color = Color.White,
        )

const val default_image = "https://media.gettyimages.com/id/1176493172/photo/old-paper-on-black-background.jpg?s=2048x2048&w=gi&k=20&c=FU74II9g5uar59MSotV_9KgeXSpw_FlzqSgbYppWPLk="
const val default_profile = "https://media.gettyimages.com/id/1301638898/photo/silhouette-of-a-girl-in-a-hat-in-a-backlight.jpg?s=2048x2048&w=gi&k=20&c=ku-p4Ma127NN3OplnP2-n0RNZn1ar40uZbFbDqraj7Y="

@Composable
fun readJsonFromAssets(fileName: String): List<Movies> {
    var dataList: List<Movies> = emptyList()
    val context = LocalContext.current
    try {
        val jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        dataList = Json.decodeFromString(jsonString)
    } catch (ioException: IOException) {
        ioException.printStackTrace()
    }

    return dataList
}

fun readJsonFromAssets(context: Context, fileName: String): List<ParcelizeMovies> {
    val json = try {
        context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ex: IOException) {
        ex.printStackTrace()
        return emptyList()
    }
    return Gson().fromJson(json, Array<ParcelizeMovies>::class.java).toList()
}

fun readJsonFromAsset(context: Context, fileName: String): DetailsMeta {
    val json = try {
        context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ex: IOException) {
        ex.printStackTrace()
        return DetailsMeta()
    }
    return Gson().fromJson(json, DetailsMeta::class.java)
}

fun readCastFromAsset(context: Context, fileName: String): CastMeta {
    val json = try {
        context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ex: IOException) {
        ex.printStackTrace()
        return CastMeta()
    }
    return Gson().fromJson(json, CastMeta::class.java)
}

@OptIn(ExperimentalFoundationApi::class)
fun PagerState.calculateCurrentOffsetForPage(page: Int): Float {
    return (currentPage - page) + currentPageOffsetFraction
}

// Text sections
val nunitoFamily = FontFamily(
    Font(R.font.nunito_light, FontWeight.Light),
    Font(R.font.nunito_regular, FontWeight.Normal),
    Font(R.font.nunito_bold, FontWeight.Bold),
)

fun Context.toastLong(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

fun Context.toast(msg: String, duration: Int = 0) {
    when (duration) {
        0 -> Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        1 -> Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}
