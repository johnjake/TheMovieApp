package com.themovieguide.org.ui.theme.images

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.themovieguide.org.R
import com.themovieguide.org.features.utils.default_image
import com.themovieguide.org.features.utils.default_profile
import com.themovieguide.org.features.utils.imageUrl
import com.themovieguide.org.ui.theme.PinkColor700

@Composable
fun AsyncImageLoad(url: String, description: String, onClick: () -> Unit) {
    AsyncImage(
        model = url,
        contentDescription = description,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .height(190.dp)
            .width(120.dp)
            .border(BorderStroke(0.dp, Color.Transparent), shape = RoundedCornerShape(18.dp))
            .padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)
            .offset(x = 0.dp, y = 0.dp)
            .clickable {
                onClick()
            }
            .focusable(true),
    )
}

@Composable
fun AsyncSearchImageLoad(url: String, description: String, onClick: () -> Unit) {
    AsyncImage(
        model = url,
        contentDescription = description,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .height(65.dp)
            .width(65.dp)
            .border(BorderStroke(0.dp, Color.Transparent), shape = RoundedCornerShape(5.dp))
            .padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)
            .offset(x = 0.dp, y = 0.dp)
            .clickable {
                onClick()
            }
            .focusable(true),
    )
}

@Composable
fun AsyncItemImg(url: String, description: String, onClick: () -> Unit) {
    AsyncImage(
        model = url,
        contentDescription = description,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .height(155.dp)
            .width(110.dp)
            .border(BorderStroke(0.dp, Color.Transparent), shape = RoundedCornerShape(18.dp))
            .clickable {
                onClick()
            }
            .focusable(true),
    )
}

@Composable
fun AsyncItemImgVisited(url: String, description: String, onClick: () -> Unit) {
    AsyncImage(
        model = url,
        contentDescription = description,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .height(225.dp)
            .width(200.dp)
            .border(BorderStroke(0.dp, Color.Transparent), shape = RoundedCornerShape(18.dp))
            .clickable {
                onClick()
            }
            .focusable(true),
    )
}

@Composable
fun imagePainter(param: String?): AsyncImagePainter {
    val path = param ?: default_image
    val url = path.imageUrl()
    return rememberAsyncImagePainter(
        model = ImageRequest
            .Builder(LocalContext.current)
            .placeholder(R.drawable.ic_default_wallpaper)
            .data(url)
            .size(Size.ORIGINAL)
            .build(),
    )
}

@Composable
fun imageProfile(param: String?): AsyncImagePainter {
    val path = param ?: default_profile
    val url = path.imageUrl()
    return rememberAsyncImagePainter(
        model = ImageRequest
            .Builder(LocalContext.current)
            .placeholder(R.drawable.ic_default_profile)
            .data(url)
            .size(Size.ORIGINAL)
            .build(),
    )
}

@Composable
fun ArrowBackPainter(nav: NavController) {
    Icon(
        painter = painterResource(id = R.drawable.ic_arrow_back),
        "back_home",
        tint = PinkColor700,
        modifier = Modifier
            .size(26.dp)
            .padding(start = 4.dp)
            .clickable(onClick = {
                nav.popBackStack()
            }),
    )
}

@Composable
fun HeartPainter() {
    IconButton(onClick = { }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_heart),
            "back_home",
            tint = PinkColor700,
            modifier = Modifier
                .size(26.dp),
        )
    }
}

@Composable
fun PlayVideo(isVideoPlaying: MutableState<Boolean>, isIconVisible: MutableState<Boolean>, modifier: Modifier) {
    AnimatedVisibility(visible = isIconVisible.value) {
        androidx.compose.material.Icon(
            Icons.Default.PlayArrow,
            contentDescription = null,
            tint = Color.White,
            modifier = modifier.clickable(onClick = {
                isIconVisible.value = false
                isVideoPlaying.value = true
            }),
        )
    }
}

fun getBitmapFromImage(context: Context, drawable: Int): Bitmap {
    val db = ContextCompat.getDrawable(context, drawable)
    val bit = Bitmap.createBitmap(
        db!!.intrinsicWidth,
        db.intrinsicHeight,
        Bitmap.Config.ARGB_8888,
    )
    val canvas = Canvas(bit)
    db.setBounds(0, 0, canvas.width, canvas.height)
    db.draw(canvas)
    return bit
}
