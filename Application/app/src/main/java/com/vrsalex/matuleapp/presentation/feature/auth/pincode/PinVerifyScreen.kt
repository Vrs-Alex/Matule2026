package com.vrsalex.matuleapp.presentation.feature.auth.pincode

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.vrsalex.matuleapp.presentation.common.pincode.PinCodeContent
import com.vrsalex.matuleapp.presentation.common.pincode.PinCodeContract

@Composable
fun PinVerifyScreen(
    viewModel: PinVerifyViewModel = hiltViewModel(),
    onNavigateToMain: () -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    val lifecycle = LocalLifecycleOwner.current.lifecycle
    LaunchedEffect(Unit) {
        viewModel.channel
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .collect {
                when(it) {
                    PinCodeContract.Effect.NavigateToMain -> onNavigateToMain()
                }
            }
    }

    PinCodeContent(
        state = state,
        event = viewModel::onEvent
    )
}