package com.vrsalex.matuleuikit.preview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.vrsalex.uikit.R
import com.vrsalex.uikit.component.button.AppBubble
import com.vrsalex.uikit.component.button.AppBubbleSize
import com.vrsalex.uikit.component.button.AppButton
import com.vrsalex.uikit.component.button.AppButtonState
import com.vrsalex.uikit.component.button.AppCartButton
import com.vrsalex.uikit.component.button.AppChip
import com.vrsalex.uikit.component.button.AppLoginButton
import com.vrsalex.uikit.component.button.AppOutlinedButton
import com.vrsalex.uikit.component.button.AppSecondaryButton

@Composable
fun ShowButton() {

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            AppBubble(
                size = AppBubbleSize.Big,
                iconId = R.drawable.icon_filter,
                onClick = {}
            )
            AppBubble(
                size = AppBubbleSize.Small,
                iconId = R.drawable.icon_chevron_left,
                onClick = {}
            )
        }

        AppButton(
            state = AppButtonState.Big,
            onClick = {},
            label = "Подтвердить"
        )

        AppButton(
            state = AppButtonState.Big,
            onClick = {},
            label = "Подтвердить",
            enabled = false
        )

        AppOutlinedButton(
            state = AppButtonState.Big,
            onClick = {},
            label = "Подтвердить"
        )

        AppSecondaryButton(
            state = AppButtonState.Big,
            onClick = {},
            label = "Подтвердить"
        )

        AppButton(
            state = AppButtonState.Small,
            onClick = {},
            label = "Добавить"
        )
        AppButton(
            state = AppButtonState.Small,
            onClick = {},
            label = "Добавить",
            enabled = false
        )
        AppOutlinedButton(
            state = AppButtonState.Small,
            onClick = {},
            label = "Убрать"
        )
        AppSecondaryButton(
            state = AppButtonState.Small,
            onClick = {},
            label = "Подтвердить"
        )


        var isActive by remember { mutableStateOf(false) }
        AppChip(
            active = isActive,
            label = "Популярные",
            onClick = { isActive = !isActive }
        )

        AppCartButton(
            price = 500,
            onClick = {}
        )

        AppLoginButton(
            iconId = R.drawable.yandex,
            label = "Войти с Yandex",
            onClick = {}
        )

        AppLoginButton(
            iconId = R.drawable.vk,
            label = "Войти с VK",
            onClick = {}
        )

    }
}