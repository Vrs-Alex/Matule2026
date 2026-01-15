package com.vrsalex.uikit.component.header

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vrsalex.uikit.theme.AppTheme

@Composable
fun AppSmallHeader(
    title: String = "",
    startContent: @Composable () -> Unit = {},
    endContent: @Composable () -> Unit = {},
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier.height(48.dp)
            .fillMaxWidth()
            .padding(start = 20.dp, end = 26.dp),
    ){
        Box(Modifier.align(Alignment.TopStart)){
            startContent()
        }

        Box(Modifier.align(Alignment.TopCenter)){
            Text(
                text =  title,
                style = AppTheme.type.title2Semibold,
                color = AppTheme.color.black
            )
        }

        Box(Modifier.align(Alignment.TopEnd)){
            endContent()
        }
    }

}