package com.vrsalex.matuleapi

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vrsalex.network.api.dto.request.LoginRequest
import com.vrsalex.network.api.service.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    authService: AuthService
): ViewModel() {

    private val _test = MutableStateFlow("")
    val test = _test.asStateFlow()

    init {
        viewModelScope.launch {
            val res = authService.logIn(
                LoginRequest(
                    "alexgm0508@gmail.com",
                    "Alex2007"
                )
            )
            Log.e("MYAPP", res.toString())
            _test.update {
                res.toString()
            }
        }
    }

}