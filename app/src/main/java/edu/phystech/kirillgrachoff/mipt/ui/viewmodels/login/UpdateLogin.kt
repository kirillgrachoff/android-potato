package edu.phystech.kirillgrachoff.mipt.ui.viewmodels.login

data class UpdateLogin(val login: String) : LoginAction {
    override val update: (LoginUiState) -> LoginUiState
        get() = { it.copy(login = login) }
}