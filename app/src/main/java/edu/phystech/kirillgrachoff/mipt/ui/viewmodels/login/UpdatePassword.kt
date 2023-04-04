package edu.phystech.kirillgrachoff.mipt.ui.viewmodels.login

data class UpdatePassword(val password: String) : LoginAction {
    override val update: (LoginUiState) -> LoginUiState
        get() = { it.copy(password = password) }
}