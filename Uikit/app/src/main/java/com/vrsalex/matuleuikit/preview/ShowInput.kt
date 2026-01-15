package com.vrsalex.matuleuikit.preview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.vrsalex.uikit.component.input.AppTextInput
import com.vrsalex.uikit.component.input.DateVisualTransformation

@Composable
fun ShowInput() {

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        var text1 by remember { mutableStateOf("") }
        AppTextInput(
            value = text1,
            onValueChange = { text1 = it },
            placeholder = "Введите имя"
        )

        var text2 by remember { mutableStateOf("Иван") }
        AppTextInput(
            value = text2,
            onValueChange = { text2 = it },
            placeholder = "Введите имя",
            label = "Имя"
        )

        var text3 by remember { mutableStateOf("") }
        AppTextInput(
            value = text3,
            onValueChange = { text3 = it },
            placeholder = "Имя",
            error = "Введите ваше имя"
        )

        var text4 by remember { mutableStateOf("") }
        AppTextInput(
            value = text4,
            onValueChange = { text4 = it },
            placeholder = "Имя",
            isPasswordField = true
        )

        var text5 by remember { mutableStateOf("") }
        AppTextInput(
            value = text5,
            onValueChange = { text5 = it },
            placeholder = "--.--.----",
            visualTransformation = DateVisualTransformation()
        )
    }

}