package com.vrsalex.matuleapp.presentation.feature.auth.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.vrsalex.matuleapp.presentation.common.BaseColumn
import com.vrsalex.uikit.component.button.AppButton
import com.vrsalex.uikit.component.button.AppButtonState
import com.vrsalex.uikit.component.input.AppTextInput
import com.vrsalex.uikit.component.select.AppSelect
import com.vrsalex.uikit.theme.AppTheme

@Composable
fun CreateProfileScreen(
    viewmodel: CreateProfileViewModel = hiltViewModel(),
    onNext: () -> Unit
 ) {
    val state by viewmodel.state.collectAsStateWithLifecycle()

    val lifecycle = LocalLifecycleOwner.current.lifecycle
    LaunchedEffect(Unit) {
        viewmodel.channel
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .collect {
                when(it) {
                    CreateProfileContract.Effect.OnNext -> onNext()
                }
            }
    }

    CreateProfileContent(state, viewmodel::onEvent)
}


@Composable
private fun CreateProfileContent(
    state: CreateProfileContract.State,
    event: (e: CreateProfileContract.Event) -> Unit
) {

    val scrollState = rememberScrollState()
    BaseColumn (
        modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp).verticalScroll(scrollState),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = "Создание Профиля",
            style = AppTheme.type.title1ExtraBold
        )
        Column() {
            Text(
                text = "Без профиля вы не сможете создавать проекты.",
                style = AppTheme.type.captionRegular,
                color = AppTheme.color.caption
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "В профиле будут храниться результаты проектов и ваши описания.",
                style = AppTheme.type.captionRegular,
                color = AppTheme.color.caption
            )
        }
        Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
            AppTextInput(
                value = state.firstName,
                onValueChange = { event(CreateProfileContract.Event.OnFirstNameChanged(it)) },
                placeholder = "Имя",
                imeOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )
            AppTextInput(
                value = state.patronymic,
                onValueChange = { event(CreateProfileContract.Event.OnPatronymicChanged(it)) },
                placeholder = "Отчество",
                imeOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )
            AppTextInput(
                value = state.lastName,
                onValueChange = { event(CreateProfileContract.Event.OnLastNameChanged(it)) },
                placeholder = "Фамилия",
                imeOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )
            AppTextInput(
                value = state.birthday,
                onValueChange = { event(CreateProfileContract.Event.OnBirthdayChanged(it)) },
                placeholder = "Дата рождения",
                imeOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )
            AppSelect(
                items = listOf("Мужской", "Женский"),
                selectedItem = state.gender,
                label = "Пол",
                onValueChange = { event(CreateProfileContract.Event.OnGenderChanged(it)) }
            )
            AppTextInput(
                value = state.email,
                onValueChange = { event(CreateProfileContract.Event.OnEmailChanged(it)) },
                error = state.emailError,
                placeholder = "Почта",
                imeOptions = KeyboardOptions(imeAction = ImeAction.Done)
            )
        }

        AppButton(
            state = AppButtonState.Big,
            onClick = { event(CreateProfileContract.Event.OnNextClick) },
            label = "Далее",
            enabled = state.isButtonEnabled
        )
    }

}