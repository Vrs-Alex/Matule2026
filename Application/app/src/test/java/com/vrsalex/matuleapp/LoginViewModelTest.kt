package com.vrsalex.matuleapp

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.vrsalex.matuleapp.domain.auth.AuthRepository
import com.vrsalex.matuleapp.domain.auth.AuthResult
import com.vrsalex.matuleapp.presentation.common.snackbar.SnackbarController
import com.vrsalex.matuleapp.presentation.feature.auth.login.LoginContract
import com.vrsalex.matuleapp.presentation.feature.auth.login.LoginViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    private val authRepository = mockk<AuthRepository>(relaxed = true)
    private val snackbarController = mockk<SnackbarController>(relaxed = true)
    private lateinit var viewModel: LoginViewModel

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
        viewModel = LoginViewModel(authRepository, snackbarController)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun `OnSignInClick with invalid email should show error and not call repository`() = runTest {
        viewModel.state.test {
            awaitItem()

            // Act
            viewModel.onEvent(LoginContract.Event.OnEmailChanged("invalid-email"))
            awaitItem()

            viewModel.onEvent(LoginContract.Event.OnSignInClick)

            // Assert
            val state = awaitItem()
            assertThat(state.emailError).isNotNull()
            coVerify(exactly = 0) { authRepository.signIn(any(), any()) }
        }
    }

    @Test
    fun `successful signIn should send SignIn effect and hide loading`() = runTest {
        val email = "test@test.com"
        val password = "password123"


        coEvery { authRepository.signIn(email, password) } returns AuthResult.Success(mockk())

        viewModel.channel.test {
            viewModel.onEvent(LoginContract.Event.OnEmailChanged(email))
            viewModel.onEvent(LoginContract.Event.OnPasswordChanged(password))

            viewModel.onEvent(LoginContract.Event.OnSignInClick)


            assertThat(awaitItem()).isEqualTo(LoginContract.Effect.SignIn)
            assertThat(viewModel.state.value.isLoading).isFalse()
        }
    }

    @Test
    fun `network error should show snackbar message`() = runTest {
        coEvery { authRepository.signIn(any(), any()) } returns AuthResult.Error.NetworkError

        viewModel.onEvent(LoginContract.Event.OnEmailChanged("test@test.com"))
        viewModel.onEvent(LoginContract.Event.OnSignInClick)

        advanceUntilIdle()

        verify { snackbarController.showMessage("Нет интернета") }
        assertThat(viewModel.state.value.isLoading).isFalse()
    }


    @Test
    fun `SingUpClick should send SingUp effect`() = runTest {
        viewModel.channel.test {
            viewModel.onEvent(LoginContract.Event.SingUpClick)

            assertThat(awaitItem()).isEqualTo(LoginContract.Effect.SingUp)
        }
    }



}