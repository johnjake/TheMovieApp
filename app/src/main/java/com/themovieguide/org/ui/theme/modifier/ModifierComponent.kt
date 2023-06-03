package com.themovieguide.org.ui.theme.modifier

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

val modifierIndicator = Modifier
    .height(14.dp)
    .fillMaxWidth()
val modifierRowSlider = Modifier
    .height(300.dp)
    .width(400.dp)

val modifierTitleBox = Modifier
    .height(220.dp)
    .width(400.dp)

val modifierSearchBox = Modifier
    .height(70.dp)
    .width(250.dp)

val modifierTitleTop = Modifier
    .padding(start = 65.dp, top = 7.dp, end = 15.dp, bottom = 0.dp)
    .offset(x = 65.dp, y = 7.dp)

val modifierPagingTitleTop = Modifier
    .padding(start = 5.dp, top = 7.dp, end = 0.dp, bottom = 15.dp)
    .offset(x = 5.dp, y = 17.dp)

val modifierBodyTop = Modifier
    .width(315.dp)
    .padding(start = 65.dp, top = 7.dp, end = 25.dp, bottom = 0.dp)
    .offset(x = 65.dp, y = 7.dp)

val modifierSearchResult = Modifier
    .width(315.dp)
    .padding(start = 35.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)
    .offset(x = 35.dp, y = 0.dp)

val modifierDateTop = Modifier
    .padding(start = 65.dp, top = 6.dp, end = 15.dp, bottom = 0.dp)
    .offset(x = 65.dp, y = 6.dp)

val modifierStarTop = Modifier
    .padding(start = 65.dp, top = 5.dp, end = 15.dp, bottom = 0.dp)
    .offset(x = 65.dp, y = 5.dp)

val modifierStarTickets = Modifier
    .padding(start = 15.dp, top = 10.dp, end = 15.dp, bottom = 15.dp)

val modifierTheaterTitle = Modifier
    .padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)
    .offset(x = 0.dp, y = 0.dp)

val modifierPagingTitle = Modifier
    .padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 25.dp)
    .offset(x = 0.dp, y = 0.dp)

val modifierUpcoming = Modifier
    .padding(start = 16.dp, top = 2.dp, end = 0.dp, bottom = 8.dp)
    .offset(x = 8.dp, y = 0.dp)

val modifierToday = Modifier
    .padding(start = 16.dp, top = 2.dp, end = 0.dp, bottom = 12.dp)
    .offset(x = 8.dp, y = 0.dp)

val modifierViewAll = Modifier
    .padding(start = 60.dp, top = 2.dp, end = 0.dp, bottom = 12.dp)
    .offset(x = 8.dp, y = 0.dp)

val modifierRowIndicator = Modifier
    .height(20.dp)
    .width(250.dp)
    .padding(start = 65.dp, top = 5.dp, end = 15.dp, bottom = 0.dp)
    .offset(x = 65.dp, y = 5.dp)

val modifierCardView = Modifier
    .height(190.dp)
    .width(120.dp)
    .border(BorderStroke(1.dp, Color.DarkGray), shape = RoundedCornerShape(12.dp))
    .background(color = Color.Transparent)
    .padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)
    .offset(x = 0.dp, y = 0.dp)

val modifierCardGenres = Modifier
    .height(25.dp)
    .wrapContentWidth()
    .padding(start = 2.dp, top = 0.dp, end = 2.dp, bottom = 0.dp)
    .offset(x = 0.dp, y = 0.dp)

val modifierSearchCardView = Modifier
    .height(65.dp)
    .width(65.dp)
    .border(BorderStroke(1.dp, Color.DarkGray), shape = RoundedCornerShape(12.dp))
    .background(color = Color.Transparent)
    .padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)
    .offset(x = 0.dp, y = 0.dp)

val modifierItemCardView = Modifier
    .height(155.dp)
    .width(110.dp)
    .border(BorderStroke(1.dp, Color.DarkGray), shape = RoundedCornerShape(12.dp))
    .background(color = Color.Transparent)

val modifierPagingItemCardView = Modifier
    .height(155.dp)
    .width(115.dp)
    .border(BorderStroke(1.dp, Color.DarkGray), shape = RoundedCornerShape(12.dp))
    .background(color = Color.Transparent)
    .padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)
    .offset(x = 0.dp, y = 0.dp)

val modifierTwoCardView = Modifier
    .fillMaxHeight()
    .width(250.dp)
    .border(BorderStroke(1.dp, Color.DarkGray), shape = RoundedCornerShape(12.dp))
    .background(color = Color.Transparent)
    .padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)
    .offset(x = 0.dp, y = 0.dp)

val modifierPagingVerticalGrid = Modifier
    .fillMaxHeight()
    .width(450.dp)
    .padding(start = 5.dp, end = 5.dp)

val modifierRowItem = Modifier
    .padding(start = 4.dp, top = 8.dp, end = 2.dp, bottom = 0.dp)
    .offset(x = 2.dp, y = 8.dp)

val modifierSearch = Modifier
    .fillMaxWidth()
    .border(
        BorderStroke(1.dp, Color.Gray),
        shape = RoundedCornerShape(20.dp),
    ).padding(
        start = 8.dp,
        end = 8.dp,
        bottom = 2.dp,
    )

val modifierTitle = Modifier
    .padding(start = 8.dp, end = 8.dp, top = 8.dp)

val modifierOverView = Modifier
    .padding(start = 8.dp, end = 8.dp, top = 8.dp)

val modifierBookNow = Modifier
    .padding(start = 8.dp, end = 8.dp, top = 0.dp)

val modifierGenres = Modifier
    .padding(start = 8.dp, end = 8.dp, top = 2.dp)

val modifierIcon = Modifier
    .size(80.dp)
    .padding(start = 8.dp, end = 8.dp)
    .offset(y = (-260).dp)
