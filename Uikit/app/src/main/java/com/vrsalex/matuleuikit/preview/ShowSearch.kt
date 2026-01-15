package com.vrsalex.matuleuikit.preview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.vrsalex.uikit.component.search.AppSearch

@Composable
fun ShowSearch() {

    var value by remember { mutableStateOf("") }
    AppSearch(
        value = value,
        onValueChange = { value = it },
        placeholder = "Искать описание"
    )

}