package com.vrsalex.matuleapp.presentation.feature.project.create

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.vrsalex.matuleapp.presentation.common.BaseColumn
import com.vrsalex.uikit.R
import com.vrsalex.uikit.component.header.AppSmallHeader
import com.vrsalex.uikit.component.icon.AppIcon
import com.vrsalex.uikit.component.input.AppTextInput
import com.vrsalex.uikit.component.modal.AppModalBottomSheet
import com.vrsalex.uikit.component.select.AppSelect
import com.vrsalex.uikit.theme.AppTheme
import org.jetbrains.annotations.Async

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateProjectScreen(
    viewModel: CreateProjectViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current

    val pickMedia = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            viewModel.onEvent(CreateProjectContract.Event.OnUriChange(uri))
        }
    }


    CreateProjectContent(state, viewModel::onEvent)

    if (state.isVisibleBottomSheet){
        AppModalBottomSheet(
            onDismiss = { viewModel.onEvent(CreateProjectContract.Event.OnChangeVisibleBottomSheet) },
            title = "Выбрать фото",
            onClose = { viewModel.onEvent(CreateProjectContract.Event.OnChangeVisibleBottomSheet) },
            content = {
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(Modifier.height(32.dp))
                    Text(
                        text = "Выбрать из галееии",
                        style = AppTheme.type.title2Regular,
                        modifier = Modifier.clickable {
                            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                        }
                    )
                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = "Сделать фото",
                        style = AppTheme.type.title2Regular,
                        modifier = Modifier.clickable {  }
                    )
                    Spacer(Modifier.height(32.dp))
                }
            }
        )
    }
}


@Composable
private fun CreateProjectContent(
    state: CreateProjectContract.State,
    event: (e: CreateProjectContract.Event) -> Unit
) {
    BaseColumn(
        withTabBarPadding = true,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        AppSmallHeader("Создать проект", modifier = Modifier.padding(horizontal = 20.dp).padding(top = 28.dp))
        Spacer(Modifier.height(13.dp))
        Column(
            modifier = Modifier.padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AppTextInput(
                value = state.name,
                onValueChange = { event(CreateProjectContract.Event.OnNameChange(it)) },
                placeholder = "Введите имя",
                label = "Название"
            )
            AppTextInput(
                value = state.startDate,
                onValueChange = { event(CreateProjectContract.Event.OnStartDateChange(it)) },
                placeholder = "--.--.----",
                label = "Дата начала"
            )
            AppTextInput(
                value = state.endDate,
                onValueChange = { event(CreateProjectContract.Event.OnEndDateChange(it)) },
                placeholder = "--.--.----",
                label = "Дата начала"
            )
            AppTextInput(
                value = state.siteUrl,
                onValueChange = { event(CreateProjectContract.Event.OnSiteUrlChange(it)) },
                placeholder = "example.com",
                label = "Источник описания"
            )
            AppSelect(
                items = listOf("Женская одежда", "Мужская одежда"),
                selectedItem = state.category,
                label = "Выберите  категорию",
                onValueChange = {},
            )
        }

        Box(
            Modifier
                .padding(vertical = 37.dp)
                .fillMaxWidth()
                .height(192.dp),
            contentAlignment = Alignment.Center
        ){
            Box(
                modifier = Modifier.height(192.dp).width(202.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(AppTheme.color.inputBg)
                    .clickable(
                        interactionSource = null,
                        indication = ripple(),
                        onClick = { event(CreateProjectContract.Event.OnChangeVisibleBottomSheet) }
                    ),
                contentAlignment = Alignment.Center
            ){
                if (state.imageUri == null) {
                    AppIcon(
                        iconId = R.drawable.icon_plus,
                        modifier = Modifier.size(47.dp),
                        tint = AppTheme.color.description
                    )
                } else {
                    AsyncImage(
                        model = state.imageUri,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }

    }
}