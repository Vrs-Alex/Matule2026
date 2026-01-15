package com.vrsalex.matuleuikit.preview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.vrsalex.uikit.R
import com.vrsalex.uikit.component.select.AppSelect

private val previewData = listOf(
    "Мужчина",
    "Женщина"
)

@Composable
fun ShowSelect() {

    var selectedItem by remember { mutableStateOf<String?>(null) }
    AppSelect(
        items = previewData,
        selectedItem = selectedItem,
        label = "Пол",
        onValueChange = { selectedItem = it }
    )

    var selectedItem2 by remember { mutableStateOf<String?>(null) }
    AppSelect(
        items = previewData,
        selectedItem = selectedItem2,
        label = "Пол",
        onValueChange = { selectedItem2 = it },
        iconId = R.drawable.male
    )

}