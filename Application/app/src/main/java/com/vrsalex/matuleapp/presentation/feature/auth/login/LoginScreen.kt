package com.vrsalex.matuleapp.presentation.feature.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.vrsalex.matuleapp.R
import com.vrsalex.uikit.component.button.AppButton
import com.vrsalex.uikit.component.button.AppButtonState
import com.vrsalex.uikit.component.button.AppLoginButton
import com.vrsalex.uikit.component.button.AppSecondaryButton
import com.vrsalex.uikit.component.input.AppTextInput
import com.vrsalex.uikit.theme.AppTheme

@Composable
fun LoginScreen(
    viewmodel: LoginViewModel = hiltViewModel(),
    onNavigateToSignUp: () -> Unit,
    onNavigateToCreatePinCode: () -> Unit
) {
    val state by viewmodel.state.collectAsStateWithLifecycle()

    val lifecycle = LocalLifecycleOwner.current.lifecycle
    LaunchedEffect(Unit) {
        viewmodel.channel
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .collect {
                when(it) {
                    LoginContract.Effect.SignIn -> onNavigateToCreatePinCode()
                    LoginContract.Effect.SingUp -> onNavigateToSignUp()
                }
            }
    }
    LoginContent(state, viewmodel::onEvent)
}

@Composable
private fun LoginContent(
    state: LoginContract.State,
    event: (e: LoginContract.Event) -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        Modifier.fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column() {
            Row() {
                Image(
                    painter = painterResource(R.drawable.hello),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(Modifier.width(16.dp))
                Text(
                    text = "Добро пожаловать!",
                    style = AppTheme.type.title1ExtraBold
                )
            }
            Spacer(Modifier.height(24.dp))
            Text(
                text = "Войдите, чтобы пользоваться функциями приложения",
                style = AppTheme.type.textRegular
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppTextInput(
                value = state.email,
                onValueChange = { event(LoginContract.Event.OnEmailChanged(it)) },
                placeholder = "example@mail.com",
                error = state.emailError,
                label = "Вход по E-mail"
            )
            AppTextInput(
                value = state.password,
                onValueChange = { event(LoginContract.Event.OnPasswordChanged(it)) },
                placeholder = "",
                label = "Пароль",
                error = state.passwordError,
                isPasswordField = true
            )
            AppButton(
                state = AppButtonState.Big,
                onClick = { event(LoginContract.Event.OnSignInClick) },
                label = "Далее",
                enabled = state.isButtonActive
            )
            Text(
                text = "Зарегистрироваться",
                style = AppTheme.type.textRegular,
                color = AppTheme.color.accent,
                modifier = Modifier.clickable(
                    interactionSource = null,
                    indication = ripple(),
                    onClick = { event(LoginContract.Event.SingUpClick) }
                )
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Или войдите с помощью",
                style = AppTheme.type.textRegular,
                color = AppTheme.color.caption
            )
            AppLoginButton(
                iconId = com.vrsalex.uikit.R.drawable.vk,
                label = "Войти с VK",
                onClick = {}
            )
            AppLoginButton(
                iconId = com.vrsalex.uikit.R.drawable.yandex,
                label = "Войти с Yandex",
                onClick = {}
            )
        }
    }


}