package com.vrsalex.uikit.component.search

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.vrsalex.uikit.R
import com.vrsalex.uikit.component.icon.AppIcon
import com.vrsalex.uikit.theme.AppTheme
import com.vrsalex.uikit.theme.Black


@Composable
fun AppSearch(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    imeOptions: KeyboardOptions = KeyboardOptions.Default,
    endAction: (@Composable () -> Unit)? = null
) {
    Row(
        modifier =modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.height(48.dp)
                .weight(1f)
                .clip(RoundedCornerShape(10.dp))
                .background(AppTheme.color.inputBg)
                .border(BorderStroke(1.dp, AppTheme.color.inputStroke), RoundedCornerShape(10.dp))

        ) {
            Row(
                Modifier.fillMaxSize().padding(horizontal = 14.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AppIcon(R.drawable.icon_search, tint = AppTheme.color.description)
                BasicTextField(
                    modifier = Modifier.weight(1f),
                    value = value,
                    singleLine = true,
                    onValueChange = onValueChange,
                    textStyle = AppTheme.type.textRegular,
                    keyboardOptions = imeOptions,
                    cursorBrush = SolidColor(AppTheme.color.accent),
                    decorationBox = { innerTextField ->
                        Box() {
                            if (value.isEmpty()) {
                                Text(
                                    text = placeholder,
                                    style = AppTheme.type.textRegular,
                                    color = AppTheme.color.caption
                                )
                            }
                            innerTextField()
                        }
                    }
                )
                if (value.isNotEmpty()) {
                    AppIcon(
                        R.drawable.icon_close, tint = AppTheme.color.description,
                        onClick = { onValueChange("") }
                    )
                }
            }
        }
        endAction?.invoke()
    }

}