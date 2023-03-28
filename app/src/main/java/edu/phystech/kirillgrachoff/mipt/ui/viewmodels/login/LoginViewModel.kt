package edu.phystech.kirillgrachoff.mipt.ui.viewmodels.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class LoginUiState(
    val login: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false,
)

interface LoginAction {
    val update: (LoginUiState) -> LoginUiState
}

class LoginViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun confirm(action: LoginAction) {
        _uiState.update(action.update)
    }
}