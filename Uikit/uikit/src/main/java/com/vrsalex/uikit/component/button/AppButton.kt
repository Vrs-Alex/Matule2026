package com.vrsalex.uikit.component.button

import androidx.compose.animation.animateColorAsState
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.vrsalex.uikit.theme.AppTheme

@Composable
fun AppButton(
    state: AppButtonState,
    onClick: () -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {

    val backgroundColor by animateColorAsState(
        targetValue = if (enabled) AppTheme.color.accent
        else AppTheme.color.accentInactive
    )

    val modifierWidth = if (state.width != null)
        Modifier.width(state.width!!)
    else Modifier.fillMaxWidth()


    Button(
        onClick = onClick,
        modifier = modifier.height(state.height).then(modifierWidth),
        enabled = enabled,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            disabledContainerColor = backgroundColor,
            contentColor = AppTheme.color.white,
            disabledContentColor = AppTheme.color.white
        ),
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