package com.vrsalex.matuleuikit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vrsalex.matuleuikit.preview.ShowButton
import com.vrsalex.matuleuikit.preview.ShowCard
import com.vrsalex.matuleuikit.preview.ShowController
import com.vrsalex.matuleuikit.preview.ShowHeader
import com.vrsalex.matuleuikit.preview.ShowIcon
import com.vrsalex.matuleuikit.preview.ShowInput
import com.vrsalex.matuleuikit.preview.ShowModal
import com.vrsalex.matuleuikit.preview.ShowSearch
import com.vrsalex.matuleuikit.preview.ShowSelect
import com.vrsalex.matuleuikit.preview.ShowTabBar
import com.vrsalex.uikit.theme.AppMatuleTheme
import com.vrsalex.uikit.theme.Black

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppMatuleTheme() {
                val scrollState = rememberScrollState()
                Column(
                    modifier = Modifier.statusBarsPadding().fillMaxSize().verticalScroll(scrollState).padding(horizontal = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(32.dp)
                ) {
                    ShowIcon()
                    ShowController()
                    ShowButton()
                    ShowInput()
                    ShowModal()
                    ShowSelect()
                    ShowSearch()
                    ShowHeader()
                    ShowCard()
                    ShowTabBar()

                    Spacer(Modifier.height(60.dp))
                }
            }
        }
    }
}
