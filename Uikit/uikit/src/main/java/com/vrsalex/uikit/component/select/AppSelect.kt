package com.vrsalex.uikit.component.select

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vrsalex.uikit.R
import com.vrsalex.uikit.component.icon.AppIcon
import com.vrsalex.uikit.component.modal.AppModalBottomSheet
import com.vrsalex.uikit.theme.AppTheme
import com.vrsalex.uikit.theme.Black
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppSelect(
    items: List<String>,
    selectedItem: String?,
    label: String,
    onValueChange: (String?) -> Unit,
    modifier: Modifier = Modifier,
    iconId: Int? = null
) {
    val scope = rememberCoroutineScope()
    var visibleBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

    val textColor by animateColorAsState(
        if (selectedItem != null) Black
        else AppTheme.color.caption
    )

    Surface(
        modifier = modifier.height(48.dp).fillMaxWidth(),
        color = AppTheme.color.inputBg,
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, AppTheme.color.inputStroke),
        onClick = { visibleBottomSheet = true }
    ) {
        Row(Modifier.padding(14.dp)) {
            iconId?.let {
                Image(
                    painter = painterResource(iconId),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(Modifier.width(12.dp))
            }
            Text(
                text = selectedItem?: label,
                style = AppTheme.type.headlineRegular,
                color = textColor
            )
            Spacer(Modifier.weight(1f))
            AppIcon(R.drawable.icon_chevron_down, tint = AppTheme.color.description)
        }
    }

    if (visibleBottomSheet){
        AppModalBottomSheet(
            title = "Выберите",
            onDismiss = {
                scope.launch {
                    sheetState.hide()
                }.invokeOnCompletion {
                    visibleBottomSheet = false
                }
            },
            onClose = {
                onValueChange(null)
                scope.launch {
                    sheetState.hide()
                }.invokeOnCompletion {
                    visibleBottomSheet = false
                }
            },
            sheetState = sheetState,
            content = {
                items.forEach { item ->
                    Surface(
                        modifier = Modifier.height(40.dp).fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            onValueChange(item)
                            scope.launch {
                                sheetState.hide()
                            }.invokeOnCompletion {
                                visibleBottomSheet = false
                            }
                        }
                    ) {
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.CenterStart) {
                            Text(
                                text = item,
                                style = AppTheme.type.textRegular
                            )
                        }
                    }
                }
            }
        )
    }

}