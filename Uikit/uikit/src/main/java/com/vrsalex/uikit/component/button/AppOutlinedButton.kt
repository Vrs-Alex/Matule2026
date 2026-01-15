package com.vrsalex.uikit.component.button

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.vrsalex.uikit.theme.AppTheme

@Composable
fun AppOutlinedButton(
    state: AppButtonState,
    onClick: () -> Unit,
    label: String,
    modifier: Modifier = Modifier
) {

    val modifierWidth = if (state.width != null)
        Modifier.width(state.width!!)
    else Modifier.fillMaxWidth()


    Button(
        onClick = onClick,
        modifier = modifier.height(state.height).then(modifierWidth),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = AppTheme.color.accent
        ),
        border = BorderStroke(1.dp, AppTheme.color.accent),
        contentPadding = PaddingValues(horizontal = 13.5.dp, vertical = 10.dp)
    ) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                label,
                style = state.textStyle(),
                overflow = TextOverflow.Ellipsis
            )
        }
    }

}