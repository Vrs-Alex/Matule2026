package com.vrsalex.matuleapp.presentation.feature.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.vrsalex.matuleapp.R
import com.vrsalex.uikit.component.controller.AppToggle
import com.vrsalex.uikit.component.tabbar.appTabBarPadding
import com.vrsalex.uikit.theme.AppTheme

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val uriHandler = LocalUriHandler.current
    LaunchedEffect(Unit) {
        viewModel.channel
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .collect {
                when(it) {
                    ProfileContract.Effect.OnLogout -> {

                    }
                    is ProfileContract.Effect.OnPrivacyPolicy -> {
                        uriHandler.openUri(it.url)
                    }
                    is ProfileContract.Effect.OnTermsOfUse -> {
                        uriHandler.openUri(it.url)
                    }
                }
            }
    }

    ProfileContent(state, viewModel::onEvent)
}

@Composable
private fun ProfileContent(
    state: ProfileContract.State,
    event: (e: ProfileContract.Event) -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(horizontal = 20.dp)
            .appTabBarPadding()
            .safeContentPadding()
    ) {
        Spacer(Modifier.height(32.dp))
        Text(
            text = state.firstName,
            style = AppTheme.type.title1ExtraBold
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = state.email,
            style = AppTheme.type.headlineRegular,
            color = AppTheme.color.caption
        )
        Spacer(Modifier.height(24.dp))
        Row(
            Modifier.fillMaxWidth().height(64.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.order),
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
            Spacer(Modifier.width(20.dp))
            Text(
                text = "Мои заказы",
                style = AppTheme.type.title3Semibold
            )
        }
        Row(
            Modifier.fillMaxWidth().height(64.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.settings),
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
            Spacer(Modifier.width(20.dp))
            Text(
                text = "Уведомления",
                style = AppTheme.type.title3Semibold
            )
            Spacer(Modifier.weight(1f))
            AppToggle(
                checked = state.isShowNotification,
                onCheckedChange = { event(ProfileContract.Event.OnChangeNotification(it)) }
            )
            Spacer(Modifier.width(15.dp))
        }

        Spacer(Modifier.weight(1f))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = "Политика конфиденциальности",
                style = AppTheme.type.textMedium,
                color = AppTheme.color.caption,
                modifier = Modifier.clickable(
                    interactionSource = null,
                    indication = ripple(),
                    onClick = { event(ProfileContract.Event.OnPrivacyPolicy) }
                )
            )
            Text(
                text = "Пользовательское соглашение",
                style = AppTheme.type.textMedium,
                color = AppTheme.color.caption,
                modifier = Modifier.clickable(
                    interactionSource = null,
                    indication = ripple(),
                    onClick = { event(ProfileContract.Event.OnTermsOfUse) }
                )
            )
            Text(
                text = "Выход",
                style = AppTheme.type.textMedium,
                color = AppTheme.color.error,
                modifier = Modifier.clickable(
                    interactionSource = null,
                    indication = ripple(),
                    onClick = { event(ProfileContract.Event.OnLogout) }
                )
            )
        }
        Spacer(Modifier.weight(1f))
    }

}