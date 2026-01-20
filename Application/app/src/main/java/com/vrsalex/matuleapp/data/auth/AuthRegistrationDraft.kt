package com.vrsalex.matuleapp.data.auth

import com.vrsalex.matuleapp.domain.auth.model.SignUpData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRegistrationDraft @Inject constructor() {

    private val _draft = MutableStateFlow(SignUpData())
    val draft = _draft.asStateFlow()

    fun update(block: (SignUpData) -> SignUpData) {
        _draft.update(block)
    }

    fun clear() {
        _draft.value = SignUpData()
    }

}