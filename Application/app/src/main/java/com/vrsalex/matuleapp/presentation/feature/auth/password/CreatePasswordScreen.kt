package com.vrsalex.matuleapp.presentation.feature.auth.password

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.ImeOptions
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.vrsalex.matuleapp.R
import com.vrsalex.uikit.component.button.AppButton
import com.vrsalex.uikit.component.button.AppButtonState
import com.vrsalex.uikit.component.input.AppTextInput
import com.vrsalex.uikit.theme.AppTheme

@Composable
fun CreatePasswordScreen(
    viewmodel: CreatePasswordViewModel = hiltViewModel(),
    onNext: () -> Unit
) {

    val state by viewmodel.state.collectAsStateWithLifecycle()

    val lifecycle = LocalLifecycleOwner.current.lifecycle
    LaunchedEffect(Unit) {
        viewmodel.channel
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .collect {
                when(it) {
                    CreatePasswordContract.Effect.OnNext -> onNext()
                }
            }
    }

    CreatePasswordContent(state, viewmodel::onEvent)
}


@Composable
private fun CreatePasswordContent(
    state: CreatePasswordContract.State,
    event: (e: CreatePasswordContract.Event) -> Unit
) {
    Column(
        Modifier.fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Spacer(Modifier.height(59.dp))
        Row() {
            Image(
                painter = painterResource(R.drawable.hello),
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
            Spacer(Modifier.width(16.dp))
            Text(
                text = "Создание пароля",
                style = AppTheme.type.title1ExtraBold
            )
        }
        Spacer(Modifier.height(25.dp))
        Text(
            text = "Введите новый пароль",
            style = AppTheme.type.textRegular
        )
        Spacer(Modifier.height(90.dp))

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            AppTextInput(
                value = state.password,
                onValueChange = { event(CreatePasswordContract.Event.OnPasswordChanged(it)) },
                placeholder = "",
                label = "Новый Пароль",
                error = state.passwordError,
                isPasswordField = true,
                imeOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )
            AppTextInput(
                value = state.confirmPassword,
                onValueChange = { event(CreatePasswordContract.Event.OnConfirmPasswordChanged(it)) },
                placeholder = "",
                label = "Повторите пароль",
                isPasswordField = true,
                imeOptions = KeyboardOptions(imeAction = ImeAction.Done)
            )
            AppButton(
                state = AppButtonState.Big,
                onClick = { event(CreatePasswordContract.Event.OnNextClick) },
                label = "Сохранить",
                enabled = state.isButtonActive
            )
        }
        Spacer(Modifier.weight(1f))
    }
}


