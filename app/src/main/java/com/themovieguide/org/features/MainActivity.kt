package com.themovieguide.org.features

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.themovieguide.org.di.bases.BaseActivity
import com.themovieguide.org.features.navigation.HomeScreen
import com.themovieguide.org.ui.theme.CinemaGuideTheme

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CinemaGuideTheme {
                HomeScreen()
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    HomeScreen()
}
