package com.themovieguide.org.ui.theme.text

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.themovieguide.org.ui.theme.modifier.modifierBodyTop
import com.themovieguide.org.ui.theme.modifier.modifierPagingTitleTop
import com.themovieguide.org.ui.theme.modifier.modifierSearchResult
import com.themovieguide.org.ui.theme.modifier.modifierStarTop
import com.themovieguide.org.ui.theme.modifier.modifierTheaterTitle
import com.themovieguide.org.ui.theme.modifier.modifierTitleTop
import com.themovieguide.org.ui.theme.modifier.modifierToday
import com.themovieguide.org.ui.theme.modifier.modifierViewAll
import com.themovieguide.org.features.utils.MovieSelection
import com.themovieguide.org.features.utils.nunitoFamily
import com.themovieguide.org.features.utils.toStandardDate
import com.themovieguide.org.ui.theme.Default800
import com.themovieguide.org.ui.theme.Gray800
import com.themovieguide.org.ui.theme.PinkColor700
import com.themovieguide.org.ui.theme.Primary800
import org.burnoutcrew.reorderable.ReorderableLazyGridState
import org.burnoutcrew.reorderable.detectReorderAfterLongPress

@Composable
fun MovieTitle(title: String) {
    Text(
        text = title,
        textAlign = TextAlign.Left,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 17.sp,
        color = Color.White,
        modifier = modifierTitleTop,
    )
}

@Composable
fun VisitedTitle(title: String) {
    Text(
        text = title,
        textAlign = TextAlign.Left,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        color = Color.Black,
        modifier = Modifier.padding(start = 5.dp),
    )
}

@Composable
fun MoviePagingTitle(title: String) {
    Text(
        text = title,
        textAlign = TextAlign.Left,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 17.sp,
        color = Color.White,
        modifier = modifierPagingTitleTop,
    )
}

@Composable
fun TextMovieTitle(title: String, modifier: Modifier) {
    androidx.compose.material.Text(
        text = title,
        fontFamily = nunitoFamily,
        textAlign = TextAlign.Center,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp,
        color = Color.White,
        lineHeight = 1.2.em,
        modifier = modifier,
    )
}

@Composable
fun TextMovieOverViewTitle(title: String, modifier: Modifier) {
    androidx.compose.material.Text(
        text = title,
        fontFamily = nunitoFamily,
        textAlign = TextAlign.Left,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        color = Gray800,
        lineHeight = 1.2.em,
        modifier = modifier,
    )
}

@Composable
fun TextMovieOverView(title: String, modifier: Modifier) {
    androidx.compose.material.Text(
        text = title,
        fontFamily = nunitoFamily,
        textAlign = TextAlign.Justify,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = Color.White,
        lineHeight = 1.2.em,
        modifier = modifier,
    )
}

@Composable
fun TextMovieGenres(title: String, modifier: Modifier) {
    androidx.compose.material.Text(
        fontFamily = nunitoFamily,
        textAlign = TextAlign.Justify,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.Normal,
        text = title,
        color = Color.Black.copy(alpha = 1f),
        fontSize = 14.sp,
        lineHeight = 1.2.em,
        modifier = modifier,
    )
}

@Composable
fun DateRelease(date: String) {
    Text(
        text = buildAnnotatedString {
            append("Release: ")
            withStyle(SpanStyle(color = PinkColor700)) {
                append(date.toStandardDate())
            }
        },
        textAlign = TextAlign.Justify,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 12.sp,
        color = Gray800,
        lineHeight = 1.2.em,
        modifier = modifierStarTop,
    )
}

@Composable
fun DateReleaseSearch(date: String) {
    Text(
        text = buildAnnotatedString {
            append("Release: ")
            withStyle(SpanStyle(color = PinkColor700)) {
                append(date.toStandardDate())
            }
        },
        textAlign = TextAlign.Justify,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 12.sp,
        color = Gray800,
        lineHeight = 1.2.em,
        modifier = modifierSearchResult,
    )
}

@Composable
fun MovieRating(genId: Int) {
    Text(
        text = buildAnnotatedString {
            append("Rating: ")
            withStyle(SpanStyle(color = PinkColor700)) {
                append("$genId")
            }
        },
        textAlign = TextAlign.Left,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = Color.White,
        lineHeight = 1.2.em,
        modifier = modifierStarTop,
    )
}

@Composable
fun MovieItemTitle(text: String, state: ReorderableLazyGridState) {
    val words = text.split(" ")
    val wordCount = words.size
    val maxWord = 3
    val indexWord = 2
    if (wordCount <= maxWord) {
        Text(
            text = text,
            textAlign = TextAlign.Left,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            color = Color.White,
            lineHeight = 1.2.em,
            modifier = modifierTheaterTitle.detectReorderAfterLongPress(state),
        )
    } else {
        Text(
            text = buildAnnotatedString {
                for (i in 0..indexWord) {
                    append(words[i])
                    if (i < indexWord) {
                        append(" ")
                    }
                }
                withStyle(
                    style = SpanStyle(
                        color = Gray800,
                        fontWeight = FontWeight.Bold,
                    ),
                ) {
                    append(" ..")
                    addStringAnnotation(tag = "url", annotation = "https://example.com", 15, 15)
                }
            },
            textAlign = TextAlign.Left,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = Color.White,
            lineHeight = 1.2.em,
            modifier = modifierTheaterTitle.detectReorderAfterLongPress(state),
        )
    }
}

@Composable
fun DisplayText(text: String) {
    val words = text.split(" ")
    val wordCount = words.size
    val maxWord = 28
    val indexWord = 27
    if (wordCount <= maxWord) {
        Text(
            text = text,
            textAlign = TextAlign.Left,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Normal,
            fontSize = 13.sp,
            color = Color.White,
            lineHeight = 1.1.em,
            modifier = modifierBodyTop,
        )
    } else {
        Text(
            text = buildAnnotatedString {
                for (i in 0..indexWord) {
                    append(words[i])
                    if (i < indexWord) {
                        append(" ")
                    }
                }
                withStyle(
                    style = SpanStyle(
                        color = PinkColor700,
                        fontWeight = FontWeight.Bold,
                    ),
                ) {
                    append(" Read More")
                    addStringAnnotation(tag = "url", annotation = "https://example.com", 15, 15)
                }
            },
            textAlign = TextAlign.Left,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Normal,
            fontSize = 13.sp,
            color = Color.White,
            lineHeight = 1.1.em,
            modifier = modifierBodyTop,
        )
    }
}

@Composable
fun ResultText(text: String) {
    val words = text.split(" ")
    val wordCount = words.size
    val maxWord = 4
    val indexWord = 3
    if (wordCount <= maxWord) {
        Text(
            text = text,
            textAlign = TextAlign.Left,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp,
            color = Color.White,
            lineHeight = 1.1.em,
            modifier = modifierSearchResult,
        )
    } else {
        Text(
            text = buildAnnotatedString {
                for (i in 0..indexWord) {
                    append(words[i])
                    if (i < indexWord) {
                        append(" ")
                    }
                }
                withStyle(
                    style = SpanStyle(
                        color = PinkColor700,
                        fontWeight = FontWeight.Bold,
                    ),
                ) {
                    append(" ..")
                    addStringAnnotation(tag = "url", annotation = "https://example.com", 15, 15)
                }
            },
            textAlign = TextAlign.Left,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp,
            color = Color.White,
            lineHeight = 1.1.em,
            modifier = modifierSearchResult,
        )
    }
}

@Preview
@Composable
fun PreviewTextSelection() {
    var selectionSlide = remember {
        mutableStateOf(false)
    }
    TextSelection(selectionSlide)
}

@Composable
fun TextSelection(selectionSlide: MutableState<Boolean>) {
    var selectedText by remember { mutableStateOf(MovieSelection.TODAY_MOVIE) }
    Row(
        modifier = Modifier.width(400.dp),
        horizontalArrangement = Arrangement.Start,
    ) {
        Text(
            text = "Now Showing",
            textAlign = TextAlign.Justify,
            fontStyle = FontStyle.Normal,
            fontWeight = if (selectedText == MovieSelection.TODAY_MOVIE) FontWeight.ExtraBold else FontWeight.Normal,
            color = if (selectedText == MovieSelection.TODAY_MOVIE) Primary800 else Default800,
            fontSize = 17.sp,
            lineHeight = 1.2.em,
            modifier = modifierToday.clickable {
                selectionSlide.value = false
                selectedText = MovieSelection.TODAY_MOVIE
            },
        )

        Text(
            text = "Upcoming",
            textAlign = TextAlign.Justify,
            fontStyle = FontStyle.Normal,
            fontWeight = if (selectedText == MovieSelection.TODAY_MOVIE) FontWeight.Normal else FontWeight.ExtraBold,
            color = if (selectedText == MovieSelection.TODAY_MOVIE) Default800 else Primary800,
            fontSize = 17.sp,
            lineHeight = 1.2.em,
            modifier = modifierToday.clickable {
                selectionSlide.value = true
                selectedText = MovieSelection.UPCOMING_MOVIE
            },
        )
    }
}

@Composable
fun TelevisionSelection(selectionSlide: MutableState<Boolean>) {
    var selectedText by remember { mutableStateOf(MovieSelection.TODAY_MOVIE) }
    Row(
        modifier = Modifier.width(400.dp),
        horizontalArrangement = Arrangement.Start,
    ) {
        Text(
            text = "Today's Series",
            textAlign = TextAlign.Justify,
            fontStyle = FontStyle.Normal,
            fontWeight = if (selectedText == MovieSelection.TODAY_MOVIE) FontWeight.ExtraBold else FontWeight.Normal,
            color = if (selectedText == MovieSelection.TODAY_MOVIE) Primary800 else Default800,
            fontSize = 17.sp,
            lineHeight = 1.2.em,
            modifier = modifierToday.clickable {
                selectionSlide.value = false
                selectedText = MovieSelection.TODAY_MOVIE
            },
        )

        Text(
            text = "Top Series",
            textAlign = TextAlign.Justify,
            fontStyle = FontStyle.Normal,
            fontWeight = if (selectedText == MovieSelection.TODAY_MOVIE) FontWeight.Normal else FontWeight.ExtraBold,
            color = if (selectedText == MovieSelection.TODAY_MOVIE) Default800 else Primary800,
            fontSize = 17.sp,
            lineHeight = 1.2.em,
            modifier = modifierToday.clickable {
                selectionSlide.value = true
                selectedText = MovieSelection.UPCOMING_MOVIE
            },
        )
    }
}

@Composable
fun TextEnableSelection(
    selected: MutableState<Boolean>,
    enableColor: MutableState<Color>,
    enableWeight: MutableState<FontWeight>,
    disableColor: MutableState<Color>,
    disableWeight: MutableState<FontWeight>,
    titleEnable: String,
    disabledTitle: String,
) {
    when (selected.value) {
        true -> {
            enableColor.value = Default800
            enableWeight.value = FontWeight.Normal
            disableColor.value = Primary800
            disableWeight.value = FontWeight.ExtraBold
        }
        false -> {
            enableColor.value = Primary800
            enableWeight.value = FontWeight.ExtraBold
            disableColor.value = Default800
            disableWeight.value = FontWeight.Normal
        }
    }
    Text(
        text = titleEnable,
        textAlign = TextAlign.Justify,
        fontStyle = FontStyle.Normal,
        fontWeight = enableWeight.value,
        fontSize = 17.sp,
        color = enableColor.value,
        lineHeight = 1.2.em,
        modifier = modifierToday.clickable {
            enableColor.value = if (!selected.value && enableColor.value == Primary800) Default800 else Primary800
            enableWeight.value = if (!selected.value && enableWeight.value == FontWeight.ExtraBold) FontWeight.Normal else FontWeight.ExtraBold
            selected.value = false
        },
    )

    Text(
        text = disabledTitle,
        textAlign = TextAlign.Justify,
        fontStyle = FontStyle.Normal,
        fontWeight = disableWeight.value,
        fontSize = 17.sp,
        color = disableColor.value,
        lineHeight = 1.2.em,
        modifier = modifierToday.clickable {
            disableColor.value = if (!selected.value && disableColor.value == Primary800) Default800 else Primary800
            disableWeight.value = if (!selected.value && disableWeight.value == FontWeight.ExtraBold) FontWeight.Normal else FontWeight.ExtraBold
            selected.value = true
        },
    )
}

@Composable
fun TheaterEnableSelection(
    selected: MutableState<Boolean>,
    enableColor: MutableState<Color>,
    enableWeight: MutableState<FontWeight>,
    disableColor: MutableState<Color>,
    disableWeight: MutableState<FontWeight>,
    titleEnable: String,
    disabledTitle: String,
    onClickTop: (isTop: Boolean) -> Unit,
) {
    val visible = remember { mutableStateOf(false) }
    when (selected.value) {
        true -> {
            enableColor.value = Default800
            enableWeight.value = FontWeight.Normal
            disableColor.value = Primary800
            disableWeight.value = FontWeight.ExtraBold
        }
        false -> {
            enableColor.value = Primary800
            enableWeight.value = FontWeight.ExtraBold
            disableColor.value = Default800
            disableWeight.value = FontWeight.Normal
        }
    }
    Text(
        text = titleEnable,
        textAlign = TextAlign.Justify,
        fontStyle = FontStyle.Normal,
        fontWeight = enableWeight.value,
        fontSize = 17.sp,
        color = enableColor.value,
        lineHeight = 1.2.em,
        modifier = modifierToday.clickable {
            onClickTop(false)
            enableColor.value = if (!selected.value && enableColor.value == Primary800) Default800 else Primary800
            enableWeight.value = if (!selected.value && enableWeight.value == FontWeight.ExtraBold) FontWeight.Normal else FontWeight.ExtraBold
            selected.value = false
            visible.value = true
        },
    )

    Text(
        text = disabledTitle,
        textAlign = TextAlign.Justify,
        fontStyle = FontStyle.Normal,
        fontWeight = disableWeight.value,
        fontSize = 17.sp,
        color = disableColor.value,
        lineHeight = 1.2.em,
        modifier = modifierToday.clickable {
            onClickTop(true)
            disableColor.value = if (!selected.value && disableColor.value == Primary800) Default800 else Primary800
            disableWeight.value = if (!selected.value && disableWeight.value == FontWeight.ExtraBold) FontWeight.Normal else FontWeight.ExtraBold
            selected.value = true
            visible.value = true
        },
    )
    Column {
        AnimatedVisibility(visible = visible.value) {
            Text(
                text = "View All",
                color = Default800,
                fontSize = 15.sp,
                textAlign = TextAlign.End,
                fontStyle = FontStyle.Normal,
                modifier = modifierViewAll
                    .clickable {
                        visible.value = false
                    },
            )
        }
    }
}
