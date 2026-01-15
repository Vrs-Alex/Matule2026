package com.vrsalex.matuleuikit.preview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vrsalex.uikit.component.button.AppButton
import com.vrsalex.uikit.component.button.AppButtonState
import com.vrsalex.uikit.component.button.AppOutlinedButton
import com.vrsalex.uikit.component.card.AppCardBackground
import com.vrsalex.uikit.component.card.AppCartCard
import com.vrsalex.uikit.component.card.AppPrimaryCard
import com.vrsalex.uikit.component.card.AppProjectCard

@Composable
fun ShowCard() {


    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

        AppCardBackground(onClick = {}){
            Spacer(Modifier.height(110.dp))
        }

        var state by remember { mutableStateOf(false) }
        AppPrimaryCard(
            title = "Рубашка Воскресенье для машинного вязания",
            description = "Мужская одежда",
            cost = "300 ₽",
            endContent = {
                if (!state){
                    AppButton(
                        state = AppButtonState.Small,
                        onClick = { state = true },
                        label = "Добавить"
                    )
                } else {
                    AppOutlinedButton(
                        state = AppButtonState.Small,
                        onClick = { state = false },
                        label = "Убрать"
                    )
                }
            }
        )

        var count by remember { mutableStateOf(1) }
        AppCartCard(
            title = "Рубашка воскресенье для машинного вязания",
            price = "300 ₽",
            quantity = count,
            onIncrement = { count += 1 },
            onDecrement = { count -= 1 },
            onCancel = { }
        )


        AppProjectCard(
            title = "Мой первый проект",
            bottomInfo = "Прошло 2 дня",
            bottomContent = {
                AppButton(
                    state = AppButtonState.Small,
                    onClick = {},
                    label = "Открыть"
                )
            }
        )

    }

}