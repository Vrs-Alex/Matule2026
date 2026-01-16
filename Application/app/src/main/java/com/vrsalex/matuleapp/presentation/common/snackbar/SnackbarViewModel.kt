package com.vrsalex.matuleapp.presentation.common.snackbar

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SnackbarViewModel @Inject constructor(
    val snackbarController: SnackbarController
): ViewModel()