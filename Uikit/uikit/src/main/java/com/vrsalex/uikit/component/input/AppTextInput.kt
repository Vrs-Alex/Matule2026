package com.vrsalex.uikit.component.input

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.vrsalex.uikit.R
import com.vrsalex.uikit.component.icon.AppIcon
import com.vrsalex.uikit.theme.AppTheme
import com.vrsalex.uikit.theme.Black

@Composable
fun AppTextInput(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    label: String? = null,
    error: String? = null,
    isPasswordField: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    imeOptions: KeyboardOptions = KeyboardOptions.Default,
    enabled: Boolean = true
) {

    val focusRequester = remember { FocusRequester() }
    var isVisiblePassword by remember { mutableStateOf(false) }
    var isHasFocus by remember { mutableStateOf(false) }

    val borderColor by animateColorAsState(
        targetValue = when {
            error != null -> AppTheme.color.error
            isHasFocus -> AppTheme.color.accent
            !isHasFocus && value.isNotEmpty() -> AppTheme.color.icons
            else -> AppTheme.color.inputStroke
        }
    )
    val backgroundColor by animateColorAsState(
        targetValue = if (error != null) AppTheme.color.error.copy(alpha = 0.1f)
        else AppTheme.color.inputBg
    )

    val visualT = if (isPasswordField && !isVisiblePassword)
        PasswordVisualTransformation(mask = '*')
    else visualTransformation

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
        label?.let {
            Text(
                text = it,
                style = AppTheme.type.captionRegular,
                color = AppTheme.color.description
            )
        }

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(backgroundColor)
                .border(BorderStroke(1.dp, borderColor), RoundedCornerShape(10.dp))
                .focusRequester(focusRequester)
                .onFocusChanged {
                    isHasFocus = it.hasFocus
                },
            enabled = enabled,
            textStyle = AppTheme.type.textRegular,
            keyboardOptions = imeOptions,
            visualTransformation = visualT,
            cursorBrush = SolidColor(AppTheme.color.accent),
            decorationBox = { innerTextField ->
                Box(Modifier.padding(vertical = 14.dp).padding(start = 14.dp, end = 15.dp)){
                    if (value.isEmpty()){
                        Text(
                            text = placeholder,
                            style = AppTheme.type.textRegular,
                            color = AppTheme.color.caption
                        )
                    }
                    if (isPasswordField && value.isNotEmpty()){
                        val icon =  if (isVisiblePassword) R.drawable.icon_eye_open
                        else R.drawable.icon_eye_close
                        AppIcon(
                            modifier = Modifier.align(Alignment.CenterEnd),
                            iconId = icon,
                            tint = Black,
                            onClick = { isVisiblePassword = !isVisiblePassword }
                        )
                    }
                    innerTextField()
                }
            }
        )

        error?.let {
            Text(
                text = it,
                style = AppTheme.type.captionRegular,
                color = AppTheme.color.error
            )
        }

    }

}