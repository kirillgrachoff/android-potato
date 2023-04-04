package edu.phystech.kirillgrachoff.mipt.ui.viewmodels.login

class ChangePasswordVisibility : LoginAction {
    override val update: (LoginUiState) -> LoginUiState
        get() = { it.copy(passwordVisible = !it.passwordVisible) }
}