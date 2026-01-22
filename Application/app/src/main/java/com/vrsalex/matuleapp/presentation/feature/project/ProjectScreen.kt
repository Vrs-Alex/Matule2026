package com.vrsalex.matuleapp.presentation.feature.project

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vrsalex.matuleapp.presentation.common.BaseColumn
import com.vrsalex.uikit.R
import com.vrsalex.uikit.component.header.AppSmallHeader
import com.vrsalex.uikit.component.icon.AppIcon
import com.vrsalex.uikit.theme.AppTheme

@Composable
fun ProjectScreen(
    viewModel: ProjectViewModel = hiltViewModel(),
    onNavigateToCreateProject: () -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.channel.collect {
            when(it) {
                ProjectContract.Effect.OnAdd -> onNavigateToCreateProject()
            }
        }
    }

    ProjectContent(state, viewModel::onEvent)
}


@Composable
private fun ProjectContent(
    state: ProjectContract.State,
    event: (e: ProjectContract.Event) -> Unit
) {
    BaseColumn(
        withTabBarPadding = true,
    ) {
        AppSmallHeader(
            title = "Проекты",
            endContent = {
                AppIcon(
                    iconId = R.drawable.icon_plus,
                    tint = AppTheme.color.icons,
                    onClick = { event(ProjectContract.Event.OnAdd) }
                )
            },
            modifier = Modifier.padding(top = 28.dp)
        )
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 18.dp)
        ) {

        }
    }
}