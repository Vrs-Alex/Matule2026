package com.vrsalex.uikit.component.modal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vrsalex.uikit.R
import com.vrsalex.uikit.component.icon.AppIcon
import com.vrsalex.uikit.theme.AppColor
import com.vrsalex.uikit.theme.AppTheme
import com.vrsalex.uikit.theme.Black

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppModalBottomSheet(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    title: String? = null,
    onClose: (() -> Unit)? = null,
    sheetState: SheetState = rememberModalBottomSheetState(),
    content: @Composable () -> Unit = {}
) {

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismiss,
        containerColor = AppTheme.color.white,
        contentColor = AppTheme.color.black,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        dragHandle = null
    ) {
        Row(
            Modifier.fillMaxWidth().padding(top = 24.dp).padding(horizontal = 20.dp),
            verticalAlignment = Alignment.Top
        ) {
            title?.let {
                Text(
                    modifier = Modifier.weight(1f),
                    text = title,
                    style = AppTheme.type.title2ExtraBold,
                    color = Black
                )
            }
            AppIcon(
                iconId = R.drawable.icon_dismiss,
                tint = AppTheme.color.description,
                onClick = onClose
            )
        }
        content()
    }


}