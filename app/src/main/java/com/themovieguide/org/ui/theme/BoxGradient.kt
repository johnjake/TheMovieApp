package com.themovieguide.org.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@JvmOverloads
@Composable
fun GradientButton(
    text: String,
    gradient: Brush,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        contentPadding = PaddingValues(),
        onClick = { onClick() },
    ) {
        Box(
            modifier = Modifier
                .background(gradient)
                .then(modifier),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = text)
        }
    }
}

@Composable
fun gradientColor(): Brush {
    return Brush.verticalGradient(
        colors = listOf(
            BlackOpaque900,
            LightDark220,
        ),
        startY = 0f,
        endY = 90f,
    )
}

@Composable
fun gradientHome(): Brush {
    return Brush.verticalGradient(
        colors = listOf(
            BlackOpaque900,
            LightDark220,
        ),
        startY = 0f,
        endY = 900f,
    )
}

@Composable
fun gradientPurple(): Brush {
    return Brush.verticalGradient(
        colors = listOf(
            Purple80,
            Purple80,
        ),
        startY = 0f,
        endY = 0f,
    )
}
