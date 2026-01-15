package com.vrsalex.uikit.component.modal

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.vrsalex.uikit.R
import com.vrsalex.uikit.component.icon.AppIcon
import com.vrsalex.uikit.theme.AppTheme

@Composable
fun AppSnackbar(
    title: String,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier.fillMaxWidth()) {
        Surface(
            modifier = Modifier
                .height(120.dp)
                .fillMaxWidth()
                .shadow(
                    elevation = 16.dp,
                    shape = RoundedCornerShape(8.dp),
                    ambientColor = Color.White.copy(alpha = 0.5f),
                    spotColor = Color.White.copy(alpha = 0.5f)
                ),
            color = AppTheme.color.white,
            shape = RoundedCornerShape(8.dp)
        ) {
            Box(Modifier.fillMaxSize()) {
                Text(
                    text = title,
                    style = AppTheme.type.title2ExtraBold,
                    color = AppTheme.color.black,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 24.dp),
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        Box(
            Modifier.align(Alignment.TopEnd).offset(x = (4).dp, y = (-4).dp)
                .size(20.dp)
                .clip(CircleShape)
                .background(AppTheme.color.accent.copy(alpha = 0.5f))
                .clickable(
                    interactionSource = null,
                    indication = ripple(),
                    onClick = onClose
                ),
            contentAlignment = Alignment.Center
        ) {
            AppIcon(
                R.drawable.icon_close,
                tint = AppTheme.color.description,
                modifier = Modifier.size(10.dp)
            )
        }

    }

}