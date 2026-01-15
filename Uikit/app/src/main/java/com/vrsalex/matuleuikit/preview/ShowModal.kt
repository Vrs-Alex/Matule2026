package com.vrsalex.matuleuikit.preview

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vrsalex.uikit.component.button.AppButton
import com.vrsalex.uikit.component.button.AppButtonState
import com.vrsalex.uikit.component.modal.AppModalBottomSheet
import com.vrsalex.uikit.component.modal.AppSnackbar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowModal() {

    val state = rememberModalBottomSheetState()
    var isVisibleBottomSheet by remember { mutableStateOf(false) }
    AppButton(
        state = AppButtonState.Big,
        onClick = { isVisibleBottomSheet = true },
        label = "Открыть bottomSheet"
    )

    AppSnackbar(
        title = "Произошла ошибка Ну вот опять",
        onClose = {  }
    )

    if (isVisibleBottomSheet){
        AppModalBottomSheet(
            title = "Рубашка Воскресенье для машинного вязания",
            onDismiss = { isVisibleBottomSheet = false },
            onClose = { isVisibleBottomSheet=  false }
        ){
            Spacer(Modifier.height(100.dp))
        }
    }

}