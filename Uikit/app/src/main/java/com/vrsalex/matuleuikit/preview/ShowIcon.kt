package com.vrsalex.matuleuikit.preview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.vrsalex.uikit.R
import com.vrsalex.uikit.component.icon.AppIcon

private val previewIcons = listOf(
    R.drawable.icon_chevron_left,
    R.drawable.icon_chevron_down,
    R.drawable.icon_search,
    R.drawable.icon_plus,
    R.drawable.icon_minus,
    R.drawable.icon_message_circle,
    R.drawable.icon_filter,
    R.drawable.icon_download,
    R.drawable.icon_map,
    R.drawable.icon_more_horizontal,
    R.drawable.icon_close,
    R.drawable.icon_dismiss,
    R.drawable.icon_delete,
    R.drawable.icon_shopping_cart,
    R.drawable.icon_check,
    R.drawable.icon_file_text,
    R.drawable.icon_send,
    R.drawable.icon_mic,
    R.drawable.icon_paperclip,
    R.drawable.icon_eye_close,
    R.drawable.icon_eye_open
)

@Composable
fun ShowIcon() {

    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        previewIcons.forEach { id ->
            AppIcon(
                iconId = id
            )
        }
    }

}