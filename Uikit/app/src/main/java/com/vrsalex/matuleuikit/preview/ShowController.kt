package com.vrsalex.matuleuikit.preview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.vrsalex.uikit.component.controller.AppCounter
import com.vrsalex.uikit.component.controller.AppToggle

@Composable
fun ShowController() {

    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        var checked by remember { mutableStateOf(true) }
        AppToggle(
            checked = checked,
            onCheckedChange = { checked = it }
        )

        var count by remember { mutableStateOf(1) }
        AppCounter(
            count = count,
            onIncrement = { count += 1 },
            onDecrement = { count -= 1 }
        )
    }

}